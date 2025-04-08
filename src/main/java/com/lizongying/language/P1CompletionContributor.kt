package com.lizongying.language

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.PsiElement
import com.intellij.ui.JBColor
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
    val body = Tag("body", Stage.NORMAL, Type.ELEMENT)
    val head = Tag("head", Stage.NORMAL, Type.ELEMENT)

    val title = Tag("title", Stage.NORMAL, Type.ATTRIBUTE)
    val meta = Tag("meta", Stage.NORMAL, Type.ELEMENT)

    val id = Tag("id", Stage.NORMAL, Type.ATTRIBUTE)
    val clas = Tag("class", Stage.NORMAL, Type.ATTRIBUTE)
    val style = Tag("style", Stage.NORMAL, Type.ATTRIBUTE)

    val href = Tag("href", Stage.NORMAL, Type.ATTRIBUTE)
    val target = Tag("target", Stage.NORMAL, Type.ATTRIBUTE)

    const val COMPOSES = "composes"
}

internal class P1CompletionContributor : CompletionContributor() {
    private val htmlTagPropertyMap = mutableMapOf(
        "div" to mutableSetOf(Tags.clas, Tags.id, Tags.style),
        "p" to mutableSetOf(Tags.clas, Tags.id, Tags.style),
        "a" to mutableSetOf(Tags.clas, Tags.id, Tags.style, Tags.href, Tags.target),

        "body" to mutableSetOf(Tags.clas, Tags.id, Tags.style),
        COMPOSES to mutableSetOf(),

        "head" to mutableSetOf(Tags.title, Tags.meta, Tags.style),
        "html" to mutableSetOf(Tags.head, Tags.body, Tags.style)
    )

    init {
        Element.all.forEach { element ->
            if (htmlTagPropertyMap[element.label] == null) {
                htmlTagPropertyMap[element.label] = mutableSetOf()
            }

            // composes
            if (element.label !in selfClosingTags) {
                htmlTagPropertyMap[element.label]?.add(Tag(COMPOSES, Stage.NORMAL, Type.ATTRIBUTE))
            }

            htmlTagPropertyMap[COMPOSES]?.add(Tag(element.label, Stage.NORMAL, Type.ELEMENT))

            // add event
            Event.all.forEach {
                htmlTagPropertyMap[element.label]?.add(Tag(it.label, Stage.NORMAL, Type.ATTRIBUTE))
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

    private fun handleKey(keyText: String, t: Int, result: CompletionResultSet) {
        if (htmlTagPropertyMap.containsKey(keyText.lowercase())) {
            htmlTagPropertyMap[keyText]?.forEach {
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

                val color = if (it.type == Type.ELEMENT) JBColor.GREEN else JBColor.CYAN
                val lookupElement = LookupElementBuilder.create(it.label)
                    .withTypeText(desc)
//                    .withItemTextForeground(color)
                    .withTailText(" (${tailText})", true)
                    .withInsertHandler { context, _ ->
                        if (it.type == Type.ELEMENT) {
                            when (t) {
                                0 -> {
                                    context.editor.document.insertString(context.startOffset, "- ")
                                }

                                1 -> {
                                    context.editor.document.insertString(context.startOffset, " ")
                                }

                                2 -> {

                                }
                            }

                            context.editor.document.insertString(context.tailOffset, ": ")
                            context.editor.caretModel.moveToOffset(context.tailOffset)
                        }

//                        如果是屬性
                        if (it.type == Type.ATTRIBUTE) {
                            if (it.label != "data-") {
                                context.editor.document.insertString(context.tailOffset, ": ")
                            }
                            context.editor.caretModel.moveToOffset(context.tailOffset)
                        }
                    }
                if (t == 1) {
                    result.addElement(LookupElementBuilder.create(""))
                }
                result.addElement(lookupElement)
            }
        } else {
            // 补全 HTML 标签
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
                    println("is YAMLScalar, parent: ${element.parent}, parent.text: ${element.parent.text} element: $element element.text: ${element.text}")

                    var t = 0
                    if (element is YAMLPlainTextImpl) {
                        // -
                        if (element.text.startsWith("-IntellijIdeaRulezzz")) {
                            t = 1
                        }
                    }

                    when (val parent = element.parent) {
                        is YAMLKeyValue -> {
                            handleKey(parent.keyText, t, result)
                        }

                        is YAMLMapping -> {
                            val p1 = parent.parent
                            if (p1 is YAMLKeyValue) {
                                handleKey(p1.keyText, t, result)
                            }
                        }

                        is YAMLSequence -> {
                            val p1 = parent.parent
                            if (p1 is YAMLKeyValue) {
                                handleKey(p1.keyText, t, result)
                            }
                        }

                        // -
                        is YAMLCompoundValue -> {
                            val p1 = parent.parent
                            if (p1 is YAMLKeyValue) {
                                handleKey(p1.keyText, t, result)
                            }
                        }

                        // - a
                        is YAMLSequenceItem -> {
                            t = 2
                            val p1 = parent.parent
                            if (p1 is YAMLSequence) {
                                val p2 = p1.parent
                                if (p2 is YAMLKeyValue) {
                                    handleKey(p2.keyText, t, result)
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
