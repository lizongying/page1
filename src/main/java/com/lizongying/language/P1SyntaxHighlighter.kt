package com.lizongying.language

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.jetbrains.yaml.YAMLHighlighter
import org.jetbrains.yaml.YAMLTokenTypes
import org.jetbrains.yaml.lexer.YAMLFlexLexer


class P1SyntaxHighlighter : SyntaxHighlighterBase() {
    private val map: MutableMap<IElementType, TextAttributesKey> = mutableMapOf(
        YAMLTokenTypes.SCALAR_KEY to YAMLHighlighter.SCALAR_KEY,
        YAMLTokenTypes.SCALAR_STRING to YAMLHighlighter.SCALAR_STRING,
        YAMLTokenTypes.SCALAR_DSTRING to YAMLHighlighter.SCALAR_DSTRING,
        YAMLTokenTypes.SCALAR_TEXT to YAMLHighlighter.SCALAR_TEXT,
        YAMLTokenTypes.SCALAR_LIST to YAMLHighlighter.SCALAR_LIST,
        YAMLTokenTypes.COMMENT to YAMLHighlighter.COMMENT,
        YAMLTokenTypes.TEXT to YAMLHighlighter.TEXT,
        YAMLTokenTypes.LBRACE to YAMLHighlighter.SIGN,
        YAMLTokenTypes.RBRACE to YAMLHighlighter.SIGN,
        YAMLTokenTypes.LBRACKET to YAMLHighlighter.SIGN,
        YAMLTokenTypes.RBRACKET to YAMLHighlighter.SIGN,
        YAMLTokenTypes.COMMA to YAMLHighlighter.SIGN,
        YAMLTokenTypes.QUESTION to YAMLHighlighter.SIGN,
        YAMLTokenTypes.COLON to YAMLHighlighter.SIGN,
        YAMLTokenTypes.AMPERSAND to YAMLHighlighter.SIGN,
        YAMLTokenTypes.DOCUMENT_MARKER to YAMLHighlighter.SIGN,
        YAMLTokenTypes.SEQUENCE_MARKER to YAMLHighlighter.SIGN,
        YAMLTokenTypes.ANCHOR to YAMLHighlighter.ANCHOR,
        YAMLTokenTypes.ALIAS to YAMLHighlighter.ANCHOR,
        YAMLTokenTypes.TAG to YAMLHighlighter.ANCHOR
    )

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
//        println("tokenType $tokenType, language: ${tokenType.language}")
        return pack(map[tokenType])
    }

    override fun getHighlightingLexer(): Lexer {
        return YAMLFlexLexer()
    }
}