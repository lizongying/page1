package com.lizongying.language.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.SettingsCategory
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

@State(
    name = "P1PluginSettings",
    storages = [Storage("P1PluginSettings.xml")],
    category = SettingsCategory.TOOLS
)
class P1PluginSettings : PersistentStateComponent<P1PluginSettings.State> {

    data class State(
        var myOption: Boolean = false,
        var myText: String = "",
        var language: String = "漢字"
    )

    private var state = State()

    override fun getState(): State = state

    override fun loadState(state: State) {
        this.state = state
    }

    companion object {
        @JvmStatic
        val instance: P1PluginSettings
            get() = ApplicationManager.getApplication().getService(P1PluginSettings::class.java)
    }
}