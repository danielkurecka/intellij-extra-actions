package cz.daku.intellij.extraActions;

import com.intellij.ide.SelectInContext;
import com.intellij.ide.actions.SelectInContextImpl;
import com.intellij.ide.impl.ProjectViewSelectInGroupTarget;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;

public class SelectInProjectAction extends AnAction implements DumbAware {

	@Override
	public void actionPerformed(AnActionEvent e) {
		SelectInContext context = SelectInContextImpl.createContext(e);
		if (context == null) {
			return;
		}
		ProjectViewSelectInGroupTarget target = new ProjectViewSelectInGroupTarget();
		target.selectIn(context, true);
	}

	@Override
	public void update(AnActionEvent e) {
		e.getPresentation().setEnabled(SelectInContextImpl.createContext(e) != null);
	}
}
