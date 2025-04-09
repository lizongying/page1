package com.lizongying.language.tags

object Tags {
    val id = Tag("id", Stage.NORMAL, Type.ATTRIBUTE)
    val clas = Tag("class", Stage.NORMAL, Type.ATTRIBUTE)
    val style = Tag("style", Stage.NORMAL, Type.ATTRIBUTE)

    val href = Tag("href", Stage.NORMAL, Type.ATTRIBUTE)
    val target = Tag("target", Stage.NORMAL, Type.ATTRIBUTE)

    const val COMPOSES = "composes"
}