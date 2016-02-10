package cz.daku.intellij.extraActions.paragraph;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.EditorModificationUtil;


public class CaretUtil {

	public static void move(Caret caret, int line, int column) {
		caret.removeSelection();
		caret.moveToOffset(caret.getEditor().getDocument().getLineStartOffset(line) + column);
	}

	public static void moveWithSelection(Caret caret, int line, int column) {
		int currentOffset = caret.getOffset();
		int targetOffset = caret.getEditor().getDocument().getLineStartOffset(line) + column;

		int selStart = caret.getSelectionStart();
		int selEnd = caret.getSelectionEnd();
		int targetSelEndOffset = currentOffset == selEnd ? selStart : selEnd;

		caret.setSelection(targetOffset, targetSelEndOffset);
		caret.moveToOffset(targetOffset);
	}

	public static void moveAndScroll(Caret caret, int line, int column) {
		move(caret, line, column);
		EditorModificationUtil.scrollToCaret(caret.getEditor());
	}

	public static void moveWithSelectionAndScroll(Caret caret, int line, int column) {
		moveWithSelection(caret, line, column);
		EditorModificationUtil.scrollToCaret(caret.getEditor());
	}

}
