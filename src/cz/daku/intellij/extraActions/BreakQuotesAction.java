package cz.daku.intellij.extraActions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;


public class BreakQuotesAction extends EditorAction {

	public BreakQuotesAction() {
		super(new MyHandler());
	}

	private static class MyHandler extends EditorWriteActionHandler {

		private static final String DEFAULT_OPERATOR = "+";
		private static final Map<String, String> langOperators = new HashMap<>();

		static {
			langOperators.put("PHP", ".");
			langOperators.put("Perl5", ".");
			langOperators.put("Lua", "..");
		}

		public MyHandler() {
			super(true);
		}

		@Override
		public void executeWriteAction(Editor editor, @Nullable Caret caret, DataContext dataContext) {
			PsiElement element = findQuotedElementAtCaret(editor, caret);
			if (element == null){
				return;
			}

			char quote = element.getText().charAt(0);
			String lang = element.getLanguage().getID();
			String operator = langOperators.containsKey(lang) ? langOperators.get(lang) : DEFAULT_OPERATOR;
			String prefix = quote + " " + operator + " ";
			String sufix = " " + operator + " " + quote;

			int selStart = caret.getSelectionStart();
			int selEnd = caret.getSelectionEnd();
			String selText = caret.getSelectedText() != null ? caret.getSelectedText() : "";
			int currentOffset = caret.getOffset();

			editor.getDocument().replaceString(selStart, selEnd, prefix + selText + sufix);
			caret.moveToOffset(currentOffset + prefix.length());
			caret.setSelection(selStart + prefix.length(), selEnd + prefix.length());
		}

		@Nullable
		private PsiElement findQuotedElementAtCaret(Editor editor, @Nullable Caret caret) {
			if (caret == null) {
				return null;
			}

			Project project = editor.getProject();
			if (project == null) {
				return null;
			}

			PsiDocumentManager psiDocManager = PsiDocumentManager.getInstance(project);
			psiDocManager.commitDocument(editor.getDocument()); // needed when runned for multiple carets, otherwise we do not get updated PsiFile

			PsiFile psiFile = psiDocManager.getPsiFile(editor.getDocument());
			if (psiFile == null) {
				return null;
			}

			PsiElement element = psiFile.findElementAt(caret.getOffset());
			if (element == null) {
				return null;
			}

			PsiElement quotedElement = QuotesUtil.findNearbyQuotedElement(element);
			if (quotedElement == null) {
				return null;
			}

			return quotedElement;
		}

	}
}
