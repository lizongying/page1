package com.lizongying.language

import com.lizongying.language.settings.P1PluginSettings
import java.util.*

object I18nUtils {
    private var resourceBundle = ResourceBundle.getBundle("messages", Locale.getDefault())

    init {
        when (P1PluginSettings.instance.state.language) {
            "简体字" -> setLocale(
                Locale.Builder()
                    .setLanguage("zh")
                    .setRegion("CN")
                    .build()
            )

            "漢字" -> setLocale(
                Locale.Builder()
                    .setLanguage("zh")
                    .setRegion("TW")
                    .build()
            )

            "English" -> setLocale(
                Locale.Builder()
                    .setLanguage("en")
                    .setRegion("US")
                    .build()
            )
        }
    }

    val country: String
        get() = resourceBundle.locale.country

    fun setLocale(locale: Locale) {
        resourceBundle = ResourceBundle.getBundle("messages", locale)
    }

    fun getMessage(key: String): String {
        return try {
            resourceBundle.getString(key)
        } catch (e: MissingResourceException) {
            key
        }
    }
}