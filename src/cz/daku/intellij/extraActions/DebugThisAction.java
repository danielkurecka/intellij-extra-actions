package cz.daku.intellij.extraActions;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class DebugThisAction extends AnAction {

	@Override
	public void actionPerformed(@NotNull AnActionEvent event) {
		AnAction action = ActionManager.getInstance().getAction("DebugClass");
		action.actionPerformed(event);
	}

}
