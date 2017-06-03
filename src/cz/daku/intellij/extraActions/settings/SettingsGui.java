package cz.daku.intellij.extraActions.settings;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SettingsGui {

	private SettingsStorage config;
	private JCheckBox phpSimpleQuotesReplacementCheckBox;
	private JPanel rootPanel;

	SettingsGui(@NotNull SettingsStorage config) {
		// this.$$$setupUI$$$(); - this is automatically added
		this.config = config;
		phpSimpleQuotesReplacementCheckBox.setSelected(config.getPhpSimpleQuotesReplacement());
	}

	public boolean isModified() {
		return config.getPhpSimpleQuotesReplacement() != phpSimpleQuotesReplacementCheckBox.isSelected();
	}

	public void apply() {
		config.setPhpSimpleQuotesReplacement(phpSimpleQuotesReplacementCheckBox.isSelected());
	}

	public JPanel getRootPanel() {
		return rootPanel;
	}


	private void createUIComponents() {
		// TODO: place custom component creation code here
	}

	{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		rootPanel = new JPanel();
		rootPanel.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
		final Spacer spacer1 = new Spacer();
		rootPanel.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		final JLabel label1 = new JLabel();
		label1.setText("Toggle Quotes:");
		rootPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		phpSimpleQuotesReplacementCheckBox = new JCheckBox();
		phpSimpleQuotesReplacementCheckBox.setText("In PHP, use simple quotes replacement");
		phpSimpleQuotesReplacementCheckBox.setMnemonic('P');
		phpSimpleQuotesReplacementCheckBox.setDisplayedMnemonicIndex(3);
		rootPanel.add(phpSimpleQuotesReplacementCheckBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
		final JLabel label2 = new JLabel();
		label2.setText("If checked, it will not (un)escape special characters like $, \\n and others");
		rootPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 2, false));
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return rootPanel;
	}
}
