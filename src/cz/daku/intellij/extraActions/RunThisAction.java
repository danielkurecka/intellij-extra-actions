package cz.daku.intellij.extraActions;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class RunThisAction extends AnAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent event) {
		AnAction action = ActionManager.getInstance().getAction("RunClass");
		action.actionPerformed(event);
	}

}
