package cz.daku.intellij.extraActions.selectionSplit;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;


public class SplitSelectionAction extends AnAction {

	public void actionPerformed(AnActionEvent e) {
		Editor editor = e.getData(CommonDataKeys.EDITOR);
		if (editor == null) {
			return;
		}

		// not using Messages.showInputDialog() as it trims whitespaces
		String separator = Messages.showEditableChooseDialog(
			"Enter separator:", "Split Selection", Messages.getQuestionIcon(), new String[0], " ", null
		);

		if (separator != null) {
			SelectionSplitter.split(editor, separator);
		}
	}

}
