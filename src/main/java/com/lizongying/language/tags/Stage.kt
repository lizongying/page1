package com.lizongying.language.tags

import com.lizongying.language.I18nUtils

enum class Stage(val label: String) {
    NORMAL("Normal") {
        override fun toString(): String {
            return when (I18nUtils.country) {
                "CN" -> "正常"
                "TW" -> "正常"
                else -> "Normal"
            }
        }
    },
    DEPRECATED("Deprecated") {
        override fun toString(): String {
            return when (I18nUtils.country) {
                "CN" -> "废弃的"
                "TW" -> "廢棄的"
                else -> "Deprecated"
            }
        }
    },
    NON_STANDARD("Non-standard") {
        override fun toString(): String {
            return when (I18nUtils.country) {
                "CN" -> "非标准的"
                "TW" -> "非標準的"
                else -> "Non-standard"
            }
        }
    },
    EXPERIMENTAL("Experimental") {
        override fun toString(): String {
            return when (I18nUtils.country) {
                "CN" -> "实验性的"
                "TW" -> "实验性的"
                else -> "Experimental"
            }
        }
    };
}