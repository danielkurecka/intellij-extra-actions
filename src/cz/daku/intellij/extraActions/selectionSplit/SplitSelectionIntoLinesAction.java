package cz.daku.intellij.extraActions.selectionSplit;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;

import java.util.List;


public class SplitSelectionIntoLinesAction extends AnAction {

	public void actionPerformed(AnActionEvent e) {
		Editor editor = e.getData(CommonDataKeys.EDITOR);
		if (editor == null) {
			return;
		}

		SelectionSplitter.split(editor, "\n");
		List<Caret> allCarets = editor.getCaretModel().getAllCarets();

		if (allCarets.size() > 1) {
			// remove last caret if it's at first column - just like Sublime Text does
			Caret lastCaret = allCarets.get(allCarets.size() - 1);
			if (lastCaret.getLogicalPosition().column == 0) {
				editor.getCaretModel().removeCaret(lastCaret);
			}
		}
	}

}
