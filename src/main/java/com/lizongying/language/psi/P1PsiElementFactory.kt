package com.lizongying.language.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement

object P1PsiElementFactory {
    fun createElement(node: ASTNode): PsiElement {
        return P1PsiElement(node)
    }
}