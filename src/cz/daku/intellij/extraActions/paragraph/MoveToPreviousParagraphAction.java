package cz.daku.intellij.extraActions.paragraph;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.Nullable;


public class MoveToPreviousParagraphAction extends EditorAction {
	public MoveToPreviousParagraphAction() {
		super(new MyHandler());
	}

	private static class MyHandler extends EditorActionHandler {
		private MyHandler() {
			super(true);
		}

		@Override
		protected void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
			if (caret != null) {
				CaretUtil.moveAndScroll(caret, ParagraphFinder.findPrevious(caret), 0);
			}
		}

	}
}
