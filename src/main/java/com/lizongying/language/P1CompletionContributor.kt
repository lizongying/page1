package com.lizongying.language

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.PsiElement
import com.lizongying.language.Tags.COMPOSES
import com.lizongying.language.tags.*
import com.lizongying.language.tags.Element.Companion.selfClosingTags
import org.jetbrains.yaml.psi.*
import org.jetbrains.yaml.psi.impl.YAMLPlainTextImpl


data class Tag(
    val label: String,
    val stage: Stage = Stage.NORMAL,
    val type: Type,
    val desc: String = "",
    val descCN: String = "",
    val descTW: String = "",
)

object Tags {
    val id = Tag("id", Stage.NORMAL, Type.ATTRIBUTE)
    val clas = Tag("class", Stage.NORMAL, Type.ATTRIBUTE)
    val style = Tag("style", Stage.NORMAL, Type.ATTRIBUTE)

    val href = Tag("href", Stage.NORMAL, Type.ATTRIBUTE)
    val target = Tag("target", Stage.NORMAL, Type.ATTRIBUTE)

    const val COMPOSES = "composes"
}

internal class P1CompletionContributor : CompletionContributor() {
    private val reElement = Regex("^-\\s([^:]+):.*")

    private val htmlTagPropertyMap = mutableMapOf(
        "div" to mutableSetOf(Tags.clas, Tags.id, Tags.style),
        "p" to mutableSetOf(Tags.clas, Tags.id, Tags.style),
        "a" to mutableSetOf(Tags.clas, Tags.id, Tags.style, Tags.href, Tags.target),

        COMPOSES to mutableSetOf(),

        "html" to mutableSetOf(Tags.style),
        "head" to mutableSetOf(Tags.style),
        "body" to mutableSetOf(Tags.style),
    )

    init {
        Element.all.forEach { element ->
            if (htmlTagPropertyMap[element.label] == null) {
                htmlTagPropertyMap[element.label] = mutableSetOf()
            }

            for (it in Element.getChildren(element)) {
                htmlTagPropertyMap[element.label]?.add(
                    Tag(
                        it.label,
                        it.stage,
                        Type.ELEMENT,
                        it.desc,
                        it.descCN,
                        it.descTW
                    )
                )
            }

            // add composes
            if (element.label !in selfClosingTags) {
                htmlTagPropertyMap[element.label]?.add(
                    Tag(
                        COMPOSES,
                        Stage.NORMAL,
                        Type.ATTRIBUTE,
                        "Can contain multiple repeatable child elements.",
                        "可以包含多个可重复的子元素。",
                        "可以包含多個可重複的子元素。"
                    )
                )
            }

            htmlTagPropertyMap[COMPOSES]?.add(
                Tag(
                    element.label,
                    element.stage,
                    Type.ELEMENT,
                    element.desc,
                    element.descCN,
                    element.descTW
                )
            )

            // add event
            Event.all.forEach {
                htmlTagPropertyMap[element.label]?.add(
                    Tag(
                        it.label,
                        it.stage,
                        Type.ATTRIBUTE,
                        it.desc,
                        it.descCN,
                        it.descTW
                    )
                )
            }

            // add GlobalAttribute
            GlobalAttribute.all.forEach {
                htmlTagPropertyMap[element.label]?.add(
                    Tag(
                        it.label,
                        it.stage,
                        Type.ATTRIBUTE,
                        it.desc,
                        it.descCN,
                        it.descTW
                    )
                )
            }
        }
    }

    private fun handleKey(
        keyText: String,
        prefix: String,
        allowTypes: List<Type>,
        filter: List<String>,
        result: CompletionResultSet
    ) {
        if (htmlTagPropertyMap.containsKey(keyText.lowercase())) {
            htmlTagPropertyMap[keyText]?.forEach {
                if (!allowTypes.contains(it.type)) {
                    return@forEach
                }

                if (filter.contains(it.label)) {
                    return@forEach
                }

                var tailText = it.type.toString()

                if (it.stage != Stage.NORMAL) {
                    tailText += " ${it.stage}"
                }

                var desc = when (I18nUtils.country) {
                    "CN" -> it.descCN
                    "TW" -> it.descTW
                    else -> it.desc
                }

                if (desc.isEmpty()) {
                    desc = it.desc
                }

                val lookupElement = LookupElementBuilder.create(it.label)
                    .withTypeText(desc)
                    .withTailText(" (${tailText})", true)
                    .withInsertHandler { context, _ ->
                        when (it.type) {
                            Type.ELEMENT -> {
                                context.editor.document.insertString(context.startOffset, prefix)
                                context.editor.document.insertString(context.tailOffset, ": ")
                                context.editor.caretModel.moveToOffset(context.tailOffset)
                            }

                            // 如果是屬性
                            Type.ATTRIBUTE -> {
                                if (it.label != "data-") {
                                    context.editor.document.insertString(context.tailOffset, ": ")
                                }
                                context.editor.caretModel.moveToOffset(context.tailOffset)
                            }
                        }
                    }
                if (prefix == " ") {
                    result.addElement(LookupElementBuilder.create(""))
                }
                result.addElement(lookupElement)
            }
        } else {
            htmlTagPropertyMap.keys.forEach { tag ->
                result.addElement(LookupElementBuilder.create(tag))
            }
        }
    }

    override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
        val position = parameters.position

        val file = position.containingFile
        if (file.fileType == P1FileType) {
            when (val element = position.parent) {
                is YAMLScalarText -> {
                    println("is YAMLScalarText 1:${element.textValue} 2:${element.text}")
                }

                is YAMLScalar -> {
                    println("is YAMLScalar, parent: ${element.parent} element: $element")

                    var prefix = "- "
                    if (element is YAMLPlainTextImpl) {
                        // -
                        if (element.text.startsWith("-IntellijIdeaRulezzz")) {
                            prefix = " "
                        }
                    }

                    when (val parent = element.parent) {
                        is YAMLKeyValue -> {
                            if (Element.tags.contains(parent.keyText)) {
                                if (prefix == "- ") {
                                    prefix = ""
                                }
                            }
                            var key = parent.keyText
                            val p1 = parent.parent
                            var allowTypes = mutableListOf(Type.ELEMENT, Type.ATTRIBUTE)
                            val filter = mutableListOf<String>()
                            if (parent.keyText == COMPOSES && p1 is YAMLMapping) {
                                allowTypes = mutableListOf(Type.ELEMENT)
                                filter.add(COMPOSES)
                                val p2 = p1.parent
                                if (p2 is YAMLKeyValue) {
                                    key = p2.keyText
                                }
                            }
                            println("YAMLKeyValue $p1 $key")
                            handleKey(key, prefix, allowTypes, filter, result)
                        }

                        is YAMLMapping -> {
                            val p1 = parent.parent
                            if (p1 is YAMLKeyValue) {
                                if (Element.tags.contains(p1.keyText)) {
                                    prefix = ""
                                }
                                handleKey(
                                    p1.keyText,
                                    prefix,
                                    listOf(Type.ELEMENT, Type.ATTRIBUTE),
                                    parent.keyValues.map { it.keyText },
                                    result
                                )
                            }
                        }

                        is YAMLSequence -> {
                            val p1 = parent.parent
                            if (p1 is YAMLKeyValue) {
                                val filter = mutableListOf<String>()
                                var key = p1.keyText
                                val p2 = p1.parent
                                if (key == COMPOSES && p2 is YAMLMapping) {
                                    val p3 = p2.parent
                                    if (p3 is YAMLKeyValue) {
                                        key = p3.keyText
                                        filter.add(COMPOSES)
                                        parent.items.forEach { item ->
                                            val v = item.value
                                            if (v is YAMLMapping) {
                                                v.keyValues.forEach {
                                                    filter.add(it.keyText)
                                                }
                                            }
                                        }
                                    }
                                }

                                println("YAMLSequence $p2 $filter")
                                handleKey(key, prefix, listOf(Type.ELEMENT), filter, result)
                            }
                        }

                        // -
                        is YAMLCompoundValue -> {
                            val p1 = parent.parent
                            if (p1 is YAMLKeyValue) {
                                val filter = mutableListOf<String>()
                                var key = p1.keyText
                                val p2 = p1.parent
                                if (key == COMPOSES && p2 is YAMLMapping) {
                                    val p3 = p2.parent
                                    if (p3 is YAMLKeyValue) {
                                        key = p3.keyText
                                        filter.add(COMPOSES)

                                        val v = p1.value
                                        if (v is YAMLCompoundValue) {
                                            v.text.split("\n").map { it.trim() }.forEach { item ->
                                                reElement.find(item)?.groupValues?.get(1)?.let {
                                                    filter.add(it)
                                                }
                                            }
                                        }
                                    }
                                }

                                println("YAMLCompoundValue $p2 $filter")
                                handleKey(key, prefix, listOf(Type.ELEMENT), filter, result)
                            }
                        }

                        // - a
                        is YAMLSequenceItem -> {
                            prefix = ""
                            val p1 = parent.parent
                            if (p1 is YAMLSequence) {
                                val p2 = p1.parent
                                if (p2 is YAMLKeyValue) {
                                    val filter = mutableListOf<String>()
                                    var key = p2.keyText
                                    val p3 = p2.parent
                                    if (key == COMPOSES && p3 is YAMLMapping) {
                                        val p4 = p3.parent
                                        if (p4 is YAMLKeyValue) {
                                            key = p4.keyText
                                            filter.add(COMPOSES)
                                            p1.items.forEach { item ->
                                                val v = item.value
                                                if (v is YAMLMapping) {
                                                    v.keyValues.forEach {
                                                        filter.add(it.keyText)
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    println("YAMLSequenceItem $p3")
                                    handleKey(key, prefix, listOf(Type.ELEMENT), filter, result)
                                }
                            }
                        }

                        else -> {
                            println("parent $parent")
                        }
                    }
                }

                else -> {
                    println("element: $element element.text:${element.text}")
                }
            }
        }
    }

    // 如果是-，则返回 true，触发补全
    override fun invokeAutoPopup(position: PsiElement, typeChar: Char): Boolean {
        if (typeChar == '-') {
            return true
        }
        return false
    }
}
