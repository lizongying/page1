package com.lizongying.language

import com.intellij.openapi.fileTypes.LanguageFileType

object P1FileType : LanguageFileType(P1Language) {
    override fun getName() = "Page1 File"

    override fun getDescription() = "Page1 language file"

    override fun getDefaultExtension() = "p1"

    override fun getIcon() = P1Icons.FILE
}