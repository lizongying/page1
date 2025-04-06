package com.lizongying.language

import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.lizongying.language.psi.P1File
import org.jetbrains.yaml.YAMLParserDefinition


class P1ParserDefinition : YAMLParserDefinition() {
    override fun getFileNodeType() = IFileElementType(P1Language)

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return P1File(viewProvider)
    }
}
