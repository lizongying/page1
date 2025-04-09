package com.lizongying.language.actions

import com.intellij.ide.IdeBundle
import com.intellij.ide.browsers.WebBrowserManager
import com.intellij.ide.browsers.WebBrowserService
import com.intellij.ide.browsers.WebBrowserUrlProvider.BrowserException
import com.intellij.ide.browsers.actions.WebPreviewVirtualFile
import com.intellij.ide.browsers.createOpenInBrowserRequest
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.fileEditor.impl.EditorWindow
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.ui.loadSmallApplicationIcon
import com.intellij.ui.scale.ScaleContext
import com.intellij.util.BitUtil
import com.intellij.util.Url
import com.intellij.util.concurrency.SynchronizedClearableLazy
import com.lizongying.language.Utils.processYamlFileToHtml
import com.lizongying.language.psi.P1File
import java.awt.event.ActionEvent

class OpenHtmlInEmbeddedBrowserAction : DumbAwareAction(
    IdeBundle.messagePointer("action.open.web.preview.text"),
    null, SynchronizedClearableLazy { loadSmallApplicationIcon(ScaleContext.create(), 16) }) {

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(CommonDataKeys.PROJECT) ?: return
        var psiFile = event.getData(CommonDataKeys.PSI_FILE) ?: return
        if (psiFile !is P1File) return
        val virtualFile = psiFile.virtualFile
        val preferLocalFileUrl = BitUtil.isSet(event.modifiers, ActionEvent.SHIFT_MASK)
        try {
            if (virtualFile != null) {
                val vFile = getOrCreateVirtualFile(virtualFile)
                if (vFile != null) {
                    ApplicationManager.getApplication().runWriteAction {
                        processYamlFileToHtml(project, psiFile as P1File, vFile)
                    }
                    PsiManager.getInstance(project).findFile(vFile)?.let {
                        psiFile = it
                    }
                }
            }

            val browserRequest = createOpenInBrowserRequest(psiFile, false) ?: return
            browserRequest.reloadMode = WebBrowserManager.getInstance().webPreviewReloadMode

            val urls = WebBrowserService.getInstance().getUrlsToOpen(browserRequest, preferLocalFileUrl)

            if (!urls.isEmpty()) {
                chooseUrl(urls).onSuccess { url: Url? ->
                    val file = WebPreviewVirtualFile(virtualFile, url)
                    if (!FileEditorManager.getInstance(project).isFileOpen(file)) {
                        openInRightSplit(
                            project,
                            file,
                            null,
                            false
                        )
                    }
                }
            }
        } catch (e: BrowserException) {
            Messages.showErrorDialog(e.message, IdeBundle.message("browser.error"))
        }
    }

    private fun openInRightSplit(
        project: Project,
        file: VirtualFile,
        element: Navigatable? = null,
        requestFocus: Boolean = true
    ): EditorWindow? {
        val fileEditorManager = FileEditorManagerEx.getInstanceEx(project)
        if (!fileEditorManager.canOpenFile(file)) {
            element?.navigate(requestFocus)
            return null
        }

        val editorWindow = fileEditorManager.splitters.openInRightSplit(file, requestFocus) ?: return null

        if (element != null && element !is PsiFile) {
            ApplicationManager.getApplication().invokeLater({ element.navigate(requestFocus) }, project.disposed)
        }
        return editorWindow
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        val enabled = project != null && psiFile != null && psiFile.virtualFile != null && psiFile is P1File
        e.presentation.isEnabledAndVisible = enabled
        if (!enabled) return

        if (ActionPlaces.CONTEXT_TOOLBAR == e.place
        ) {
            var text = templateText
            text += " (" + IdeBundle.message("browser.shortcut") + ")"
            e.presentation.text = text
        }
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }
}