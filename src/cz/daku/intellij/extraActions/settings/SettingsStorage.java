package cz.daku.intellij.extraActions.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;

@State(
	name = "SettingsStorage",
	storages = {
		@Storage("extraActions.xml")
	}
)
public class SettingsStorage implements PersistentStateComponent<SettingsStorage> {

	boolean phpSimpleQuotesReplacement = false;

	@Nullable
	@Override
	public SettingsStorage getState() {
		return this;
	}

	@Override
	public void loadState(SettingsStorage state) {
		XmlSerializerUtil.copyBean(state, this);
	}

	public static SettingsStorage getInstance() {
		return ServiceManager.getService(SettingsStorage.class);
	}

	public boolean getPhpSimpleQuotesReplacement() {
		return phpSimpleQuotesReplacement;
	}

	public void setPhpSimpleQuotesReplacement(boolean phpSimpleQuotesReplacement) {
		this.phpSimpleQuotesReplacement = phpSimpleQuotesReplacement;
	}

}
