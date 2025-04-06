package com.lizongying.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.lizongying.language.P1FileType
import com.lizongying.language.P1Language


class P1File(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, P1Language) {
    override fun getFileType(): FileType {
        return P1FileType
    }

    override fun toString(): String {
        return "Page1 File"
    }
}