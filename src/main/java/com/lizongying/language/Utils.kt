package com.lizongying.language

import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiManager
import com.intellij.psi.XmlElementFactory
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.impl.source.xml.XmlFileImpl
import com.intellij.psi.xml.XmlElementType
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import com.lizongying.language.psi.P1File
import org.jetbrains.yaml.psi.YAMLMapping
import org.jetbrains.yaml.psi.YAMLSequence
import org.jetbrains.yaml.psi.impl.YAMLDocumentImpl
import org.jetbrains.yaml.psi.impl.YAMLPlainTextImpl
import java.io.File
import java.io.FileWriter
import java.io.IOException

object Utils {
    private val tags = listOf(
        "a",
        "abbr",
        "address",
        "area",
        "article",
        "aside",
        "audio",
        "b",
        "base",
        "bdi",
        "bdo",
        "blockquote",
        "body",
        "br",
        "button",
        "canvas",
        "caption",
        "cite",
        "code",
        "col",
        "colgroup",
        "data",
        "datalist",
        "dd",
        "del",
        "details",
        "dfn",
        "dialog",
        "div",
        "dl",
        "dt",
        "em",
        "embed",
        "fencedframe",
        "fieldset",
        "figcaption",
        "figure",
        "footer",
        "form",
        "h1",
        "head",
        "header",
        "hgroup",
        "hr",
        "html",
        "i",
        "iframe",
        "img",
        "input",
        "ins",
        "kbd",
        "label",
        "legend",
        "li",
        "link",
        "main",
        "map",
        "mark",
        "menu",
        "meta",
        "meter",
        "nav",
        "noscript",
        "object",
        "ol",
        "optgroup",
        "option",
        "output",
        "p",
        "picture",
        "pre",
        "progress",
        "q",
        "rp",
        "rt",
        "ruby",
        "s",
        "samp",
        "script",
        "search",
        "section",
        "select",
        "selectedcontent",
        "slot",
        "small",
        "source",
        "span",
        "strong",
        "style",
        "sub",
        "summary",
        "sup",
        "table",
        "tbody",
        "td",
        "template",
        "textarea",
        "tfoot",
        "th",
        "thead",
        "time",
        "title",
        "tr",
        "track",
        "u",
        "ul",
        "var",
        "video",
        "wbr",
    )

    private val selfClosingTags = listOf(
        "area",
        "base",
        "br",
        "col",
        "embed",
        "hr",
        "img",
        "input",
        "link",
        "meta",
        "param",
        "source",
        "track",
        "wbr"
    )

    fun processYamlFileToHtml(project: Project, psiFile: P1File, virtualFile: VirtualFile) {
        createXmlFile(project, virtualFile)?.let { xmlFile ->
            val document = PsiDocumentManager.getInstance(project).getDocument(xmlFile) ?: return
            val factory = XmlElementFactory.getInstance(project)

            for (child in psiFile.children) {
                when (child) {
                    is YAMLDocumentImpl -> {
                        processYAMLDocumentToHtml(child, document, factory)
                    }

                    is LeafPsiElement -> {}

                    else -> {
                        println("Unknown YAML element: $child ${child.text}")
                    }
                }
            }

//            PsiDocumentManager.getInstance(project).commitDocument(document)
//
//            // 获取 CodeStyleManager 实例
//            val codeStyleManager = CodeStyleManager.getInstance(project)
//            println("codeStyleManager $codeStyleManager")
//
//            // 格式化 xml 文件
//            val originalContent = xmlFile.text
//            println("xmlFile ${xmlFile.language}")
//            codeStyleManager.reformat(xmlFile)
//            PsiDocumentManager.getInstance(project).commitDocument(document)
//            val formattedContent = xmlFile.text
//
//            println("Original Content:\n$originalContent")
//            println("Formatted Content:\n$formattedContent")
//
//            // 确保编辑器中显示最新的格式化内容
        }
    }

    fun processYamlFileToHtml(project: Project, psiFile: P1File): XmlFile? {
        createXmlFile(project)?.let { xmlFile ->
            val factory = XmlElementFactory.getInstance(project)

            val document = PsiDocumentManager.getInstance(project).getDocument(xmlFile) ?: return xmlFile

            for (child in psiFile.children) {
                when (child) {
                    is YAMLDocumentImpl -> {
                        processYAMLDocumentToHtml(child, document, factory)
                    }

                    is LeafPsiElement -> {}

                    else -> {
                        println("Unknown element: $child ${child.text}")
                    }
                }
            }

            return xmlFile
        }

        return null
    }

    private fun processYAMLDocumentToHtml(
        yamlDocument: YAMLDocumentImpl,
        document: Document,
        factory: XmlElementFactory
    ) {
        when (val topLevelValue = yamlDocument.topLevelValue) {
            is YAMLMapping -> {
                val html = topLevelValue.getKeyValueByKey("html")
                if (html != null) {
                    val tag = factory.createTagFromText("<html></html>")
                    when (val value = html.value) {
                        is YAMLMapping -> {
                            processYAMLMappingToHtml(value, tag, factory)
                        }

                        is YAMLSequence -> {
                            processYAMLSequenceToHtml(value, tag, factory)
                        }

                        else -> {
                            println("unknown type")
                        }
                    }
                    document.setText("<!DOCTYPE html>${tag.text}")
                }
            }
        }
    }

    private fun processYAMLMappingToHtml(
        yamlMapping: YAMLMapping,
        parentTag: XmlTag,
        factory: XmlElementFactory
    ) {
        for (i in yamlMapping.keyValues) {
            if (i.keyText == "class") {
                when (val value = i.value) {
                    is YAMLSequence -> {
                        parentTag.setAttribute(
                            i.keyText,
                            value.items.filter { it.value != null }.map { it.value?.text }.joinToString(" ")
                        )
                    }

                    else -> {
                        parentTag.setAttribute(i.keyText, i.valueText)
                    }
                }
                continue
            }

            if (i.keyText == "style") {
                when (val value = i.value) {
                    is YAMLMapping -> {
                        parentTag.setAttribute(
                            i.keyText,
                            value.keyValues.joinToString("; ") { "${it.keyText}: ${it.valueText}" }
                        )
                    }

                    else -> {
                        parentTag.setAttribute(i.keyText, i.valueText)
                    }
                }
                continue
            }

            if (i.keyText == "id") {
                parentTag.setAttribute(i.keyText, i.valueText)
                continue
            }

            if (i.keyText == "composes") {
                when (val value = i.value) {
                    is YAMLSequence -> {
                        processYAMLSequenceToHtml(value, parentTag, factory)
                    }

                    is YAMLPlainTextImpl -> {
                        parentTag.add(factory.createDisplayText(value.textValue))
                    }

                    else -> {
                        println("${i.keyText} is unknown $value")
                    }
                }
                continue
            }

            if (i.keyText !in tags) {
                parentTag.setAttribute(i.keyText, i.valueText)
                continue
            }

            val text = if (i.keyText in selfClosingTags) "<${i.keyText} />" else "<${i.keyText}></${i.keyText}>"
            val tag = factory.createTagFromText(text)
            when (val value = i.value) {
                is YAMLMapping -> {
                    processYAMLMappingToHtml(value, tag, factory)
                }

                is YAMLSequence -> {
                    processYAMLSequenceToHtml(value, tag, factory)
                }

                is YAMLPlainTextImpl -> {
                    tag.add(factory.createDisplayText(value.textValue))
                }

                null -> {}

                else -> {
                    println("${i.keyText} unknown $value")
                }
            }

            parentTag.add(tag)
            println("${tag.name} add to ${parentTag.name}")
        }
    }

    private fun processYAMLSequenceToHtml(
        yamlSequence: YAMLSequence,
        parentTag: XmlTag,
        factory: XmlElementFactory
    ) {
        for (i in yamlSequence.items) {
            val value = i.value
            if (value is YAMLMapping) {
                processYAMLMappingToHtml(value, parentTag, factory)
            }
        }
    }

    // 生成并保存 HTML 文件
    fun generateHtmlFile(htmlContent: String, outputFilePath: String) {
        try {
            val fileWriter = FileWriter(outputFilePath)
            fileWriter.write(htmlContent)
            fileWriter.close()
            println("HTML file generated successfully: $outputFilePath")
        } catch (e: IOException) {
            println("Error writing HTML file: ${e.message}")
        }
    }

    private fun createXmlFile(project: Project): XmlFile? {
        return LocalFileSystem.getInstance().findFileByIoFile(File.createTempFile("", ".html").apply {
            deleteOnExit()
        })?.let {
            createXmlFile(project, it)
        }
    }

    private fun createXmlFile(project: Project, virtualFile: VirtualFile): XmlFile? {
        return PsiManager.getInstance(project).findFile(virtualFile)?.viewProvider?.let {
            XmlFileImpl(it, XmlElementType.HTML_FILE)
        }
    }
}