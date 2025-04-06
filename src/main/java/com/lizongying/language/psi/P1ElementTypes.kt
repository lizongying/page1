package com.lizongying.language.psi

import com.intellij.psi.TokenType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.yaml.YAMLElementType
import org.jetbrains.yaml.YAMLParserDefinition
import org.jetbrains.yaml.YAMLTokenTypes

class P1ElementTypes {
    val DOCUMENT: P1ElementType = P1ElementType("Document ---")

    val KEY_VALUE_PAIR: P1ElementType = P1ElementType("Key value pair")

    //P1ElementType VALUE = new P1ElementType("Value");
    val HASH: P1ElementType = P1ElementType("Hash")
    val ARRAY: P1ElementType = P1ElementType("Array")
    val SEQUENCE_ITEM: P1ElementType = P1ElementType("Sequence item")
    val COMPOUND_VALUE: P1ElementType = P1ElementType("Compound value")
    val MAPPING: P1ElementType = P1ElementType("Mapping")
    val SEQUENCE: P1ElementType = P1ElementType("Sequence")
    val SCALAR_LIST_VALUE: P1ElementType = P1ElementType("Scalar list value")
    val SCALAR_TEXT_VALUE: P1ElementType = P1ElementType("Scalar text value")
    val SCALAR_PLAIN_VALUE: P1ElementType = P1ElementType("Scalar plain style")
    val SCALAR_QUOTED_STRING: P1ElementType = P1ElementType("Scalar quoted string")
    val ANCHOR_NODE: P1ElementType = P1ElementType("Anchor node")
    val ALIAS_NODE: P1ElementType = P1ElementType("Alias node")

    val BLOCK_SCALAR_ITEMS: TokenSet = TokenSet.create(
        YAMLTokenTypes.SCALAR_LIST,
        YAMLTokenTypes.SCALAR_TEXT
    )

    val TEXT_SCALAR_ITEMS: TokenSet = TokenSet.create(
        YAMLTokenTypes.SCALAR_STRING,
        YAMLTokenTypes.SCALAR_DSTRING,
        YAMLTokenTypes.TEXT
    )

    val SCALAR_ITEMS: TokenSet = TokenSet.orSet(BLOCK_SCALAR_ITEMS, TEXT_SCALAR_ITEMS)

    val SCALAR_VALUES: TokenSet = TokenSet.orSet(
        SCALAR_ITEMS, TokenSet.create(
            SCALAR_LIST_VALUE
        )
    )

    val EOL_ELEMENTS: TokenSet = TokenSet.create(
        YAMLTokenTypes.EOL,
        YAMLTokenTypes.SCALAR_EOL
    )

    val SPACE_ELEMENTS: TokenSet = TokenSet.orSet(
        EOL_ELEMENTS, TokenSet.create(
            YAMLTokenTypes.WHITESPACE,
            TokenType.WHITE_SPACE,
            YAMLTokenTypes.INDENT
        )
    )

    val BLANK_ELEMENTS: TokenSet = TokenSet.orSet(
        SPACE_ELEMENTS, TokenSet.create(
            YAMLTokenTypes.COMMENT
        )
    )

    val CONTAINERS: TokenSet = TokenSet.create(
        SCALAR_LIST_VALUE,
        SCALAR_TEXT_VALUE,
        DOCUMENT,
        SEQUENCE,
        MAPPING,
        SCALAR_QUOTED_STRING,
        SCALAR_PLAIN_VALUE
    )

    val BRACKETS: TokenSet = TokenSet.create(
        YAMLTokenTypes.LBRACE,
        YAMLTokenTypes.RBRACE,
        YAMLTokenTypes.LBRACKET,
        YAMLTokenTypes.RBRACKET
    )

    val DOCUMENT_BRACKETS: TokenSet = TokenSet.create(
        YAMLTokenTypes.DOCUMENT_MARKER,
        YAMLTokenTypes.DOCUMENT_END
    )

    val TOP_LEVEL: TokenSet = TokenSet.create(
        YAMLParserDefinition.FILE,
        DOCUMENT
    )

    val INCOMPLETE_BLOCKS: TokenSet = TokenSet.create(
        MAPPING,
        SEQUENCE,
        COMPOUND_VALUE,
        SCALAR_LIST_VALUE,
        SCALAR_TEXT_VALUE
    )

    val YAML_COMMENT_TOKENS: TokenSet = TokenSet.create(YAMLTokenTypes.COMMENT)

    val WHITESPACE_TOKENS: TokenSet = TokenSet.create(YAMLTokenTypes.WHITESPACE)
}