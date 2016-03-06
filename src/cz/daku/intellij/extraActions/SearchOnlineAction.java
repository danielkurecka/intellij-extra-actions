package cz.daku.intellij.extraActions;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchOnlineAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent event) {
		Editor editor = event.getData(CommonDataKeys.EDITOR);
		if (editor == null) {
			return;
		}

		Caret caret = editor.getCaretModel().getCurrentCaret();
		String text;

		if (!caret.hasSelection()) {
			caret.selectWordAtCaret(false);
			text = caret.getSelectedText();
			caret.setSelection(caret.getOffset(), caret.getOffset());
		} else {
			text = caret.getSelectedText();
		}

		try {
			String url = "https://www.google.com/search?q=" + URLEncoder.encode(text, "UTF-8");
			BrowserUtil.browse(url);
		} catch (UnsupportedEncodingException e) {
			return;
		}
	}

}
