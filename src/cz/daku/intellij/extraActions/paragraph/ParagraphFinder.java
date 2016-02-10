package cz.daku.intellij.extraActions.paragraph;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.text.StringUtil;

public class ParagraphFinder {

	/**
	 * Returns a line number of next paragraph for the given caret.
	 * If there is no next paragraph, last line is returned.
	 * The implementation is taken from {@link com.intellij.openapi.editor.actions.ForwardParagraphAction}
	 */
	public static int findNext(Caret caret) {
		Document document = caret.getEditor().getDocument();

		int currentLine = caret.getLogicalPosition().line;
		int lineCount = document.getLineCount();

		if (isLineEmpty(document, currentLine)) {
			while (++currentLine < lineCount) {
				if (!isLineEmpty(document, currentLine)) {
					break;
				}
			}
		}

		while (++currentLine < lineCount) {
			if (isLineEmpty(document, currentLine)) {
				break;
			}
		}

		return currentLine > lineCount-1 ? lineCount-1 : currentLine;
	}

	/**
	 * Returns a line number of previous paragraph for the given caret.
	 * If there is no previous paragraph, first line is returned.
	 * The implementation is taken from {@link com.intellij.openapi.editor.actions.BackwardParagraphAction}
	 */
	public static int findPrevious(Caret caret) {
		Document document = caret.getEditor().getDocument();
		int currentLine = caret.getLogicalPosition().line;
		boolean atLineStart = caret.getLogicalPosition().column == 0;

		if (isLineEmpty(document, currentLine) || atLineStart) {
			while (--currentLine >= 0) {
				if (!isLineEmpty(document, currentLine)) {
					break;
				}
			}
		}

		while (--currentLine >= 0) {
			if (isLineEmpty(document, currentLine)) {
				break;
			}
		}

		return currentLine < 0 ? 0 : currentLine;
	}

	private static boolean isLineEmpty(Document document, int line) {
		return StringUtil.equalsIgnoreWhitespaces(
			document.getImmutableCharSequence().subSequence(document.getLineStartOffset(line), document.getLineEndOffset(line)), ""
		);
	}

}
