package com.lizongying.language.tags

data class Tag(
    val label: String,
    val stage: Stage = Stage.NORMAL,
    val type: Type,
    val desc: String = "",
    val descCN: String = "",
    val descTW: String = "",
)