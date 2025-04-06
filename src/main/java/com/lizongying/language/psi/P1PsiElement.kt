package com.lizongying.language.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.tree.IElementType

class P1PsiElement(node: ASTNode) : ASTWrapperPsiElement(node) {

    // 获取文本值
    fun getTextValue(): String? {
        return this.text
    }

    // 根据类型获取键或值
    fun getKeyOrValue(): String? {
        return this.text
    }

    // 获取节点的类型
    fun getElementType(): IElementType {
        return this.node.elementType
    }

//    // 获取图标
//    fun getIcon(): Icon {
//        return IconLoader.getIcon("/icons/p1.png", Page1PsiElement::class.java)
//    }
}