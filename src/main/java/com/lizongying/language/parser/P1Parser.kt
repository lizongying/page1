package com.lizongying.language.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import org.jetbrains.yaml.parser.YAMLParser


class P1Parser : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        return YAMLParser().parse(root, builder)
    }
}