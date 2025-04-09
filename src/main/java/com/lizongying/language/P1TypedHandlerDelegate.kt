package com.lizongying.language

import com.intellij.codeInsight.AutoPopupController
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class P1TypedHandlerDelegate : TypedHandlerDelegate() {
    override fun checkAutoPopup(typeChar: Char, project: Project, editor: Editor, file: PsiFile): Result {
        if (typeChar == '-') {
            AutoPopupController.getInstance(project).scheduleAutoPopup(editor)
            return Result.STOP
        }
        return Result.CONTINUE
    }
}