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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;


public class BreakQuotesAction extends EditorAction {

	public BreakQuotesAction() {
		super(new MyHandler());
	}

	private static class MyHandler extends EditorWriteActionHandler {

		public static final String SINGLE_QUOTE = "'";
		public static final String DOUBLE_QUOTE = "\"";
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
			PsiElement element = getPsiElement(editor, caret);
			if (element == null){
				return;
			}

			String quote = detectQuote(element);
			if (quote == null) {
				return;
			}

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
		private PsiElement getPsiElement(Editor editor, @Nullable Caret caret) {
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

			return element;
		}

		@Nullable
		private String detectQuote(@NotNull PsiElement element) {
			for (int i = 0; i < 4; i++) {
				String text = element.getText();
				if (text.startsWith(DOUBLE_QUOTE) && text.endsWith(DOUBLE_QUOTE)) {
					return DOUBLE_QUOTE;
				}

				if (text.startsWith(SINGLE_QUOTE) && text.endsWith(SINGLE_QUOTE)) {
					return SINGLE_QUOTE;
				}

				element = element.getParent();
				//noinspection ConstantConditions
				if (element == null) {
					break;
				}
			}

			return null;
		}

	}
}
