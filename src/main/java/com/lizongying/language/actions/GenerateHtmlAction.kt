package com.lizongying.language.actions

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VfsUtilCore
import com.lizongying.language.Utils.processYamlFileToHtml
import com.lizongying.language.psi.P1File

class GenerateHtmlAction : AnAction() {

    override fun update(e: AnActionEvent) {
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabledAndVisible = e.project != null && virtualFile != null && virtualFile.extension == "p1"
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE) ?: return
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        if (psiFile is P1File) {
            // 查找项目根目录
            val projectRoot = ProjectRootManager.getInstance(project).contentRoots.first()

            val newFileName = file.nameWithoutExtension + ".html"

            var relativePath = VfsUtilCore.getRelativePath(file.parent, projectRoot, '/')
            if (relativePath != null && relativePath.startsWith("src")) {
                relativePath = relativePath.substring(3)
            }

            ApplicationManager.getApplication().invokeLater({
                ApplicationManager.getApplication().runWriteAction {
                    val distDirectory =
                        projectRoot.findChild("dist") ?: projectRoot.createChildDirectory(this, "dist")

                    val targetDirectory = VfsUtil.createDirectoryIfMissing(distDirectory.path + "/" + relativePath)

                    targetDirectory?.let {
                        val virtualFile = it.createChildData(this, newFileName)
                        processYamlFileToHtml(project, psiFile, virtualFile)

                        Notifications.Bus.notify(
                            Notification(
                                "Page1",
                                "HTML generated",
                                newFileName,
                                NotificationType.INFORMATION
                            )
                        )
                    }
                }
            }, ModalityState.defaultModalityState())
        }
    }
}