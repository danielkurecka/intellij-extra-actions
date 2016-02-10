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
import com.intellij.psi.css.actions.CssReplaceQuotesIntention;
import com.jetbrains.php.lang.intentions.PhpReplaceQuotesIntention;
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
					toggleJavaScript(project, editor, element);
					break;
			}
		}

		private void togglePhp(Project project, Editor editor, PsiElement element) {
			PhpReplaceQuotesIntention intention = new PhpReplaceQuotesIntention();
			if (intention.isAvailable(project, editor, element)) {
				intention.invoke(project, editor, element);
			}
		}

		private void toggleCss(Project project, Editor editor, PsiElement element) {
			CssReplaceQuotesIntention intention = new CssReplaceQuotesIntention();
			if (intention.isAvailable(project, editor, element)) {
				intention.invoke(project, editor, element);
			}
		}

		private void toggleJavaScript(Project project, Editor editor, PsiElement element) {
			try {
				Class.forName("org.intellij.idea.lang.javascript.intention.string.JSDoubleToSingleQuotedStringIntention");
			} catch (ClassNotFoundException e) { // means that JSIntentionPowerPack plugin is not loaded
				return;
			}

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

	}
}
