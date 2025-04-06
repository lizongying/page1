package com.lizongying.language

import com.intellij.lang.Language

object P1Language : Language("Page1") {
    private fun readResolve(): Any = P1Language
}