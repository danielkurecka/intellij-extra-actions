package cz.daku.intellij.extraActions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.css.actions.CssReplaceQuotesIntention;
import com.jetbrains.php.lang.intentions.PhpReplaceQuotesIntention;
import com.jetbrains.python.codeInsight.intentions.PyQuotedStringIntention;
import cz.daku.intellij.extraActions.settings.SettingsStorage;
import org.coffeescript.codeinsight.intentions.CoffeeScriptDoubleToSingleQuotedStringIntention;
import org.coffeescript.codeinsight.intentions.CoffeeScriptSingleToDoubleQuotedStringIntention;
import org.intellij.idea.lang.javascript.intention.string.JSDoubleToSingleQuotedStringIntention;
import org.intellij.idea.lang.javascript.intention.string.JSSingleToDoubleQuotedStringIntention;
import org.jetbrains.annotations.Nullable;

public class ToggleQuotesAction extends EditorAction {

	protected ToggleQuotesAction() {
		super(new MyHandler());
	}

	private static class MyHandler extends EditorWriteActionHandler {
		protected MyHandler() {
			super(true);
		}

		@Override
		public void executeWriteAction(Editor editor, @Nullable Caret caret, DataContext dataContext) {
			if (caret == null) {
				return;
			}

			Project project = editor.getProject();
			if (project == null){
				return;
			}

			PsiDocumentManager psiDocManager = PsiDocumentManager.getInstance(project);
			psiDocManager.commitDocument(editor.getDocument()); // needed when runned for multiple carets, otherwise we do not get updated PsiFile
			PsiFile psiFile = psiDocManager.getPsiFile(editor.getDocument());

			if (psiFile == null) {
				return;
			}

			PsiElement element = psiFile.findElementAt(caret.getOffset());
			if (element == null) {
				return;
			}

			switch (element.getLanguage().getID()) {
				case "PHP":
					togglePhp(project, editor, element);
					break;

				case "CSS":
					toggleCss(project, editor, element);
					break;

				case "JavaScript":
					try {
						Class.forName("org.intellij.idea.lang.javascript.intention.string.JSDoubleToSingleQuotedStringIntention");
					} catch (ClassNotFoundException e) { // means that JSIntentionPowerPack plugin is not loaded
						break;
					}

					if (element.getParent().getLanguage().getID().equals("CoffeeScript")) {
						toggleCoffeeScript(project, editor, element);
					} else {
						toggleJavaScript(project, editor, element);
					}
					break;

				case "Python":
					togglePython(project, editor, psiFile);
					break;

				case "HTML":
				case "XML":
					toggleHtmlXml(editor, element);
					break;

				default:
					toggleOther(editor, element);
			}
		}

		private void togglePhp(Project project, Editor editor, PsiElement element) {
			if (SettingsStorage.getInstance().getPhpSimpleQuotesReplacement()) {
				toggleOther(editor, element);
			} else {
				PhpReplaceQuotesIntention intention = new PhpReplaceQuotesIntention();
				if (intention.isAvailable(project, editor, element)) {
					intention.invoke(project, editor, element);
				}
			}
		}

		private void toggleCss(Project project, Editor editor, PsiElement element) {
			CssReplaceQuotesIntention intention = new CssReplaceQuotesIntention();
			if (intention.isAvailable(project, editor, element)) {
				intention.invoke(project, editor, element);
			}
		}

		private void toggleJavaScript(Project project, Editor editor, PsiElement element) {
			JSDoubleToSingleQuotedStringIntention intentionDouble = new JSDoubleToSingleQuotedStringIntention();
			if (intentionDouble.isAvailable(project, editor, element)) {
				intentionDouble.invoke(project, editor, element);
				return;
			}

			JSSingleToDoubleQuotedStringIntention intentionSingle = new JSSingleToDoubleQuotedStringIntention();
			if (intentionSingle.isAvailable(project, editor, element)) {
				intentionSingle.invoke(project, editor, element);
			}
		}

		private void toggleCoffeeScript(Project project, Editor editor, PsiElement element) {
			CoffeeScriptDoubleToSingleQuotedStringIntention intentionDouble = new CoffeeScriptDoubleToSingleQuotedStringIntention();
			if (intentionDouble.isAvailable(project, editor, element)) {
				intentionDouble.invoke(project, editor, element);
				return;
			}

			CoffeeScriptSingleToDoubleQuotedStringIntention intentionSingle = new CoffeeScriptSingleToDoubleQuotedStringIntention();
			if (intentionSingle.isAvailable(project, editor, element)) {
				intentionSingle.invoke(project, editor, element);
			}
		}

		private void togglePython(Project project, Editor editor, PsiFile psiFile) {
			PyQuotedStringIntention intention = new PyQuotedStringIntention();
			if (intention.isAvailable(project, editor, psiFile)) {
				intention.invoke(project, editor, psiFile);
			}
		}

		private void toggleHtmlXml(Editor editor, PsiElement element) {
			PsiElement quotedElement = QuotesUtil.findNearbyQuotedElement(element);
			if (quotedElement == null) {
				return;
			}

			String unquoted = StringUtil.unquoteString(quotedElement.getText());
			StringBuilder newText = new StringBuilder();

			if (QuotesUtil.isSingelQuoted(quotedElement.getText())){
				newText.append("\"");
				newText.append(unquoted.replaceAll("\"", "&quot;").replaceAll("&#039;", "'"));
				newText.append("\"");
			} else {
				newText.append("'");
				newText.append(unquoted.replaceAll("&quot;", "\"").replaceAll("'", "&#039;"));
				newText.append("'");
			}

			TextRange textRange = quotedElement.getTextRange();
			editor.getDocument().replaceString(textRange.getStartOffset(), textRange.getEndOffset(), newText);
		}

		private void toggleOther(Editor editor, PsiElement element) {
			PsiElement quotedElement = QuotesUtil.findNearbyQuotedElement(element);
			if (quotedElement == null) {
				return;
			}

			String newText = QuotesUtil.isSingelQuoted(quotedElement.getText())
				? QuotesUtil.convertSingleToDoubleQuoted(quotedElement.getText())
				: QuotesUtil.convertDoubleToSingleQuoted(quotedElement.getText());

			TextRange textRange = quotedElement.getTextRange();
			editor.getDocument().replaceString(textRange.getStartOffset(), textRange.getEndOffset(), newText);
		}

	}
}
