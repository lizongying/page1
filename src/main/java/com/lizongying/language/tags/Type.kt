package com.lizongying.language.tags

import com.lizongying.language.I18nUtils

enum class Type(val label: String) {
    ATTRIBUTE("Attribute") {
        override fun toString(): String {
            return when (I18nUtils.country) {
                "CN" -> "属性"
                "TW" -> "屬性"
                else -> "Attribute"
            }
        }
    },
    ELEMENT("Element") {
        override fun toString(): String {
            return when (I18nUtils.country) {
                "CN" -> "元素"
                "TW" -> "元素"
                else -> "Element"
            }
        }
    },
}