package com.lizongying.language.actions

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.lizongying.language.P1Icons

class CreateFileFromTemplateAction : CreateFileFromTemplateAction() {
    override fun update(e: AnActionEvent) {
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)
        e.presentation.isEnabledAndVisible = e.project != null && virtualFile != null && virtualFile.isDirectory
    }

    override fun buildDialog(p0: Project, p1: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle("New Page1 Template File")
            .setDefaultText("template.p1")
            .addKind("Template", P1Icons.FILE, "Page1 Template")
    }

    override fun getActionName(
        directory: PsiDirectory?,
        newName: String, templateName: String?
    ): String {
        return "New Page1 Template File: $newName"
    }
}