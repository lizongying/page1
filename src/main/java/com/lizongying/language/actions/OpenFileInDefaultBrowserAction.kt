package com.lizongying.language.actions

import com.intellij.ide.GeneralSettings
import com.intellij.ide.IdeBundle
import com.intellij.ide.browsers.BrowserLauncherAppless
import com.intellij.ide.browsers.DefaultBrowserPolicy
import com.intellij.ide.browsers.WebBrowser
import com.intellij.ide.browsers.WebBrowserManager
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAwareAction

class OpenFileInDefaultBrowserAction : DumbAwareAction() {

    override fun update(e: AnActionEvent) {
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
        val project = e.project

        e.presentation.isEnabledAndVisible = project != null && virtualFile != null && virtualFile.extension == "p1"

        var description = templatePresentation.description
        description += " (" + IdeBundle.message("browser.shortcut") + ")"

        val presentation = e.presentation
        presentation.text = templatePresentation.text
        presentation.description = description

        findUsingBrowser()?.let {
            presentation.icon = it.icon
        }

        presentation.isVisible = presentation.isEnabled
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        BaseOpenInBrowserAction.Handler.openInBrowser(e, findUsingBrowser())
    }
}

fun findUsingBrowser(): WebBrowser? {
    val browserManager = WebBrowserManager.getInstance()
    val defaultBrowserPolicy = browserManager.defaultBrowserPolicy
    if (defaultBrowserPolicy == DefaultBrowserPolicy.FIRST || defaultBrowserPolicy == DefaultBrowserPolicy.SYSTEM && !BrowserLauncherAppless.canUseSystemDefaultBrowserPolicy()) {
        return browserManager.firstActiveBrowser
    } else if (defaultBrowserPolicy == DefaultBrowserPolicy.ALTERNATIVE) {
        val path = GeneralSettings.getInstance().browserPath
        if (!path.isNullOrBlank()) {
            val browser = browserManager.findBrowserById(path)
            if (browser == null) {
                for (item in browserManager.activeBrowsers) {
                    if (path == item.path) {
                        return item
                    }
                }
            } else {
                return browser
            }
        }
    }
    return null
}