package com.lizongying.language.actions

import com.intellij.icons.AllIcons
import com.intellij.ide.IdeBundle
import com.intellij.ide.browsers.WebBrowserManager
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.registry.Registry
import com.intellij.psi.util.CachedValue
import com.intellij.psi.util.CachedValueProvider
import com.intellij.ui.jcef.JBCefApp
import com.intellij.util.CachedValueImpl
import java.util.function.Supplier

open class OpenInBrowserBaseGroupAction(popup: Boolean) : ActionGroup(), DumbAware {
    private var myChildren: CachedValue<Array<AnAction?>>? = null

    init {
        val p = templatePresentation
        p.isPopupGroup = popup
        p.isHideGroupIfEmpty = true
        p.setText(IdeBundle.messagePointer("open.in.browser"))
        p.setDescription(IdeBundle.messagePointer("open.selected.file.in.browser"))
        p.iconSupplier = Supplier { AllIcons.Nodes.PpWeb }
    }

    override fun getChildren(e: AnActionEvent?): Array<AnAction?> {
        var children = myChildren

        if (children == null) {
            children = CachedValueImpl {
                val actions = computeChildren()
                CachedValueProvider.Result.create(actions, WebBrowserManager.getInstance())
            }
            myChildren = children
        }

        return children.value
    }

    private fun computeChildren(): Array<AnAction?> {
        val browsers = WebBrowserManager.getInstance().browsers
        val addDefaultBrowser = isPopup
        val hasLocalBrowser = hasLocalBrowser()
        var offset = 0
        if (addDefaultBrowser) offset++
        if (hasLocalBrowser) offset++
        val actions = arrayOfNulls<AnAction>(browsers.size + offset)

        if (hasLocalBrowser) {
            actions[0] = OpenHtmlInEmbeddedBrowserAction()
        }

        if (addDefaultBrowser) {
            val defaultBrowserAction = OpenFileInDefaultBrowserAction()
            defaultBrowserAction.templatePresentation.setText(IdeBundle.messagePointer("default"))
            defaultBrowserAction.templatePresentation.iconSupplier = Supplier { AllIcons.Nodes.PpWeb }
            actions[if (hasLocalBrowser) 1 else 0] = defaultBrowserAction
        }

        var i = 0
        val size = browsers.size
        while (i < size) {
            actions[i + offset] = BaseOpenInBrowserAction(browsers[i])
            i++
        }
        return actions
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    private fun hasLocalBrowser(): Boolean {
        return JBCefApp.isSupported() && Registry.`is`("ide.web.preview.enabled", true)
    }

    class OpenInBrowserGroupAction : OpenInBrowserBaseGroupAction(true)

    class OpenInBrowserEditorContextBarGroupAction : OpenInBrowserBaseGroupAction(false)
}