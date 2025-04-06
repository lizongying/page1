package com.lizongying.language.settings

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.ui.ComboBox
import com.lizongying.language.I18nUtils
import java.awt.Dimension
import java.awt.FlowLayout
import java.util.*
import javax.swing.*

class P1PluginConfigurable : Configurable {

    private lateinit var p: JPanel
    private lateinit var panel: JPanel
    private lateinit var myOptionCheckBox: JCheckBox
    private lateinit var myTextField: JTextField
    private lateinit var languageLabel: JLabel
    private lateinit var languageComboBox: ComboBox<String>

    private val settings = P1PluginSettings.instance

    override fun getDisplayName(): String = "Page1 Settings"

    override fun createComponent(): JComponent {
        p = JPanel()
        p.layout = FlowLayout(FlowLayout.LEFT)

        panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

//        val optionPanel = JPanel()
//        optionPanel.layout = FlowLayout(FlowLayout.LEFT)
//        optionPanel.preferredSize = Dimension(500, 40)
//
//        val optionLabel = JLabel("Enable Option")
//        optionLabel.preferredSize = Dimension(100, 30)
//
        myOptionCheckBox = JCheckBox("Enable Option")
        myOptionCheckBox.isSelected = settings.state.myOption
//
//        optionPanel.add(optionLabel)
//        optionPanel.add(myOptionCheckBox)
//        panel.add(optionPanel)
//
//        val textFieldPanel = JPanel()
//        textFieldPanel.layout = FlowLayout(FlowLayout.LEFT)
//        textFieldPanel.preferredSize = Dimension(500, 40)
//
//        val textFieldLabel = JLabel("Enter Text")
//        textFieldLabel.preferredSize = Dimension(100, 30)
//
        myTextField = JTextField(20)
        myTextField.text = settings.state.myText
//
//        textFieldPanel.add(textFieldLabel)
//        textFieldPanel.add(myTextField)
//        panel.add(textFieldPanel)

        val languagePanel = JPanel()
        languagePanel.layout = FlowLayout(FlowLayout.LEFT)
        languagePanel.preferredSize = Dimension(500, 40)

        languageLabel = JLabel()
        languageLabel.preferredSize = Dimension(100, 30)

        languageComboBox = ComboBox<String>()
        languageComboBox.addItem("English")
        languageComboBox.addItem("漢字")
        languageComboBox.addItem("简体字")

        languageComboBox.selectedItem = settings.state.language

        languagePanel.add(languageLabel)
        languagePanel.add(languageComboBox)
        panel.add(languagePanel)

        p.add(panel)
        languageChanged()
        return p
    }

    override fun isModified(): Boolean {
        return myOptionCheckBox.isSelected != settings.state.myOption ||
                myTextField.text != settings.state.myText ||
                languageComboBox.selectedItem?.toString() != settings.state.language
    }

    override fun apply() {
        settings.state.myOption = myOptionCheckBox.isSelected
        settings.state.myText = myTextField.text

        languageComboBox.selectedItem?.let {
            settings.state.language = it.toString()
        }

        setLanguage()
        languageChanged()
    }

    private fun languageChanged() {
        languageLabel.text = I18nUtils.getMessage("settings.language.title")
    }

    private fun setLanguage() {
        when (settings.state.language) {
            "简体字" ->
                I18nUtils.setLocale(
                    Locale.Builder()
                        .setLanguage("zh")
                        .setRegion("CN")
                        .build()
                )

            "漢字" -> I18nUtils.setLocale(
                Locale.Builder()
                    .setLanguage("zh")
                    .setRegion("TW")
                    .build()
            )

            "English" -> I18nUtils.setLocale(
                Locale.Builder()
                    .setLanguage("en")
                    .setRegion("US")
                    .build()
            )
        }
    }

    override fun reset() {
        myOptionCheckBox.isSelected = settings.state.myOption
        myTextField.text = settings.state.myText
        languageComboBox.selectedItem = settings.state.language
    }

    override fun getHelpTopic(): String? {
        return null
    }
}