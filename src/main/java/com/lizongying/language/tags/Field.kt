package com.lizongying.language.tags

enum class Field(val description: String) {
    STRING("A sequence of characters"),
    INTEGER("A whole number"),
    BOOLEAN("A true/false value"),
    FLOAT("A number with a decimal point"),
    DOUBLE("A double-precision floating-point number"),
    DATE("A date (e.g., YYYY-MM-DD)"),
    TIMESTAMP("A specific point in time"),
    ENUM("A predefined set of values"),
    OBJECT("A generic object"),
    MAP("")
}