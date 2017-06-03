package cz.daku.intellij.extraActions.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SettingsConfigurable implements SearchableConfigurable {

	SettingsGui gui;

	@NotNull
	@Override
	public String getId() {
		return "settings";
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "Extra actions";
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		gui = new SettingsGui(SettingsStorage.getInstance());
		return gui.getRootPanel();
	}

	@Override
	public boolean isModified() {
		return gui.isModified();
	}

	@Override
	public void apply() throws ConfigurationException {
		gui.apply();
	}

	@Override
	public void disposeUIResources() {
		gui = null;
	}
}
