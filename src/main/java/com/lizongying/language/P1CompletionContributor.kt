package com.lizongying.language

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.PsiElement
import com.intellij.ui.JBColor
import org.jetbrains.yaml.psi.*
import org.jetbrains.yaml.psi.impl.YAMLPlainTextImpl
import java.awt.Color


enum class TagType {
    ATTR,
    NODE
}

data class Tag(
    val name: String,
    val type: TagType,
    val desc: String = "",
)

object Tags {
    val body = Tag("body", TagType.NODE)
    val head = Tag("head", TagType.NODE)

    val title = Tag("title", TagType.ATTR)
    val meta = Tag("meta", TagType.NODE)

    val nodeA = Tag(
        "a",
        TagType.NODE,
        "With its href attribute, creates a hyperlink to web pages, files, email addresses, locations in the same page, or anything else a URL can address."
    )
    val nodeAbbr = Tag("abbr", TagType.NODE, "Represents an abbreviation or acronym.")
    val nodeAddress = Tag("address", TagType.NODE,"Indicates that the enclosed HTML provides contact information for a person or people, or for an organization.")
    val nodeArea = Tag("area", TagType.NODE,"Defines an area inside an image map that has predefined clickable areas. An image map allows geometric areas on an image to be associated with hypertext links.")
    val nodeArticle = Tag("article", TagType.NODE,"表示文档、页面、应用或网站中的独立结构，其意在成为可独立分配的或可复用的结构，如在发布中，它可能是论坛帖子、杂志或新闻文章、博客、用户提交的评论、交互式组件，或者其他独立的内容项目。")
    val nodeAside = Tag("aside", TagType.NODE,"表示一个和其余页面内容几乎无关的部分，被认为是独立于该内容的一部分并且可以被单独的拆分出来而不会使整体受影响。其通常表现为侧边栏或者标注框（call-out boxes）。")
    val nodeAudio = Tag("audio", TagType.NODE,"用于在文档中嵌入音频内容。<audio> 元素可以包含一个或多个音频资源，这些音频资源可以使用 src 属性或者 <source> 元素来进行描述：浏览器将会选择最合适的一个来使用。也可以使用 MediaStream 将这个元素用于流式媒体。")
    val nodeB = Tag("b", TagType.NODE)
    val nodeBase = Tag("base", TagType.NODE)
    val nodeBdi = Tag("bdi", TagType.NODE)
    val nodeBdo = Tag("bdo", TagType.NODE)
    val nodeBlockquote = Tag("blockquote", TagType.NODE)
    val nodeBody = Tag("body", TagType.NODE)
    val nodeBr = Tag("br", TagType.NODE)
    val nodeButton = Tag("button", TagType.NODE)
    val nodeCanvas = Tag("canvas", TagType.NODE)
    val nodeCaption = Tag("caption", TagType.NODE)
    val nodeCite = Tag("cite", TagType.NODE)
    val nodeCode = Tag("code", TagType.NODE)
    val nodeCol = Tag("col", TagType.NODE)
    val nodeColgroup = Tag("colgroup", TagType.NODE)
    val nodeData = Tag("data", TagType.NODE)
    val nodeDatalist = Tag("datalist", TagType.NODE)
    val nodeDd = Tag("dd", TagType.NODE)
    val nodeDel = Tag("del", TagType.NODE)
    val nodeDetails = Tag("details", TagType.NODE)
    val nodeDfn = Tag("dfn", TagType.NODE)
    val nodeDialog = Tag("dialog", TagType.NODE)
    val nodeDiv = Tag("div", TagType.NODE)
    val nodeDl = Tag("dl", TagType.NODE)
    val nodeDt = Tag("dt", TagType.NODE)
    val nodeEm = Tag("em", TagType.NODE)
    val nodeEmbed = Tag("embed", TagType.NODE)
    val nodeFencedframe = Tag("fencedframe", TagType.NODE)
    val nodeFieldset = Tag("fieldset", TagType.NODE)
    val nodeFigcaption = Tag("figcaption", TagType.NODE)
    val nodeFigure = Tag("figure", TagType.NODE)
    val nodeFooter = Tag("footer", TagType.NODE)
    val nodeForm = Tag("form", TagType.NODE)
    val nodeH1 = Tag("h1", TagType.NODE)
    val nodeHead = Tag("head", TagType.NODE)
    val nodeHeader = Tag("header", TagType.NODE)
    val nodeHgroup = Tag("hgroup", TagType.NODE)
    val nodeHr = Tag("hr", TagType.NODE)
    val nodeHtml = Tag("html", TagType.NODE)
    val nodeI = Tag("i", TagType.NODE)
    val nodeIframe = Tag("iframe", TagType.NODE)
    val nodeImg = Tag("img", TagType.NODE)
    val nodeInput = Tag("input", TagType.NODE)
    val nodeIns = Tag("ins", TagType.NODE)
    val nodeKbd = Tag("kbd", TagType.NODE)
    val nodeLabel = Tag("label", TagType.NODE)
    val nodeLegend = Tag("legend", TagType.NODE)
    val nodeLi = Tag("li", TagType.NODE)
    val nodeLink = Tag("link", TagType.NODE)
    val nodeMain = Tag("main", TagType.NODE)
    val nodeMap = Tag("map", TagType.NODE)
    val nodeMark = Tag("mark", TagType.NODE)
    val nodeMenu = Tag("menu", TagType.NODE)
    val nodeMeta = Tag("meta", TagType.NODE)
    val nodeMeter = Tag("meter", TagType.NODE)
    val nodeNav = Tag("nav", TagType.NODE)
    val nodeNoscript = Tag("noscript", TagType.NODE)
    val nodeObject = Tag("object", TagType.NODE)
    val nodeOl = Tag("ol", TagType.NODE)
    val nodeOptgroup = Tag("optgroup", TagType.NODE)
    val nodeOption = Tag("option", TagType.NODE)
    val nodeOutput = Tag("output", TagType.NODE)
    val nodeP = Tag("p", TagType.NODE)
    val nodePicture = Tag("picture", TagType.NODE)
    val nodePre = Tag("pre", TagType.NODE)
    val nodeProgress = Tag("progress", TagType.NODE)
    val nodeQ = Tag("q", TagType.NODE)
    val nodeRp = Tag("rp", TagType.NODE)
    val nodeRt = Tag("rt", TagType.NODE)
    val nodeRuby = Tag("ruby", TagType.NODE)
    val nodeS = Tag("s", TagType.NODE)
    val nodeSamp = Tag("samp", TagType.NODE)
    val nodeScript = Tag("script", TagType.NODE)
    val nodeSearch = Tag("search", TagType.NODE)
    val nodeSection = Tag("section", TagType.NODE)
    val nodeSelect = Tag("select", TagType.NODE)
    val nodeSelectedcontent = Tag("selectedcontent", TagType.NODE)
    val nodeSlot = Tag("slot", TagType.NODE)
    val nodeSmall = Tag("small", TagType.NODE)
    val nodeSource = Tag("source", TagType.NODE)
    val nodeSpan = Tag("span", TagType.NODE)
    val nodeStrong = Tag("strong", TagType.NODE)
    val nodeStyle = Tag("style", TagType.NODE)
    val nodeSub = Tag("sub", TagType.NODE)
    val nodeSummary = Tag("summary", TagType.NODE)
    val nodeSup = Tag("sup", TagType.NODE)
    val nodeTable = Tag("table", TagType.NODE)
    val nodeTbody = Tag("tbody", TagType.NODE)
    val nodeTd = Tag("td", TagType.NODE)
    val nodeTemplate = Tag("template", TagType.NODE)
    val nodeTextarea = Tag("textarea", TagType.NODE)
    val nodeTfoot = Tag("tfoot", TagType.NODE)
    val nodeTh = Tag("th", TagType.NODE)
    val nodeThead = Tag("thead", TagType.NODE)
    val nodeTime = Tag("time", TagType.NODE)
    val nodeTitle = Tag("title", TagType.NODE)
    val nodeTr = Tag("tr", TagType.NODE)
    val nodeTrack = Tag("track", TagType.NODE)
    val nodeU = Tag("u", TagType.NODE)
    val nodeUl = Tag("ul", TagType.NODE)
    val nodeVar = Tag("var", TagType.NODE)
    val nodeVideo = Tag("video", TagType.NODE)
    val nodeWbr = Tag("wbr", TagType.NODE)








    val id = Tag("id", TagType.ATTR)
    val clas = Tag("class", TagType.ATTR)
    val style = Tag("style", TagType.ATTR)
    val composes = Tag("composes", TagType.ATTR)

    val href = Tag("href", TagType.ATTR)
    val target = Tag("target", TagType.ATTR)
}

internal class P1CompletionContributor : CompletionContributor() {
    init {
//        extend(
//            CompletionType.BASIC,
//            PlatformPatterns.psiElement(KEY_VALUE_PAIR),
//            object : CompletionProvider<CompletionParameters>() {
//                public override fun addCompletions(
//                    parameters: CompletionParameters,
//                    context: ProcessingContext,
//                    resultSet: CompletionResultSet
//                ) {
//                    resultSet.addElement(LookupElementBuilder.create("Hello"))
//                }
//            }
//        )
    }


    private val htmlTagPropertyMap: Map<String, Set<Tag>> = mapOf(
        "div" to setOf(Tags.clas, Tags.id, Tags.style, Tags.composes),
        "p" to setOf(Tags.clas, Tags.id, Tags.style, Tags.composes),
        "a" to setOf(Tags.clas, Tags.id, Tags.style, Tags.composes, Tags.href, Tags.target),

        "body" to setOf(Tags.clas, Tags.id, Tags.style, Tags.composes),
        "composes" to setOf(
            Tags.nodeA,
            Tags.nodeAbbr,
            Tags.nodeAddress,
            Tags.nodeArea,
            Tags.nodeArticle,
            Tags.nodeAside,
            Tags.nodeAudio,
            Tags.nodeB,
            Tags.nodeBase,
            Tags.nodeBdi,
            Tags.nodeBdo,
            Tags.nodeBlockquote,
            Tags.nodeBody,
            Tags.nodeBr,
            Tags.nodeButton,
            Tags.nodeCanvas,
            Tags.nodeCaption,
            Tags.nodeCite,
            Tags.nodeCode,
            Tags.nodeCol,
            Tags.nodeColgroup,
            Tags.nodeData,
            Tags.nodeDatalist,
            Tags.nodeDd,
            Tags.nodeDel,
            Tags.nodeDetails,
            Tags.nodeDfn,
            Tags.nodeDialog,
            Tags.nodeDiv,
            Tags.nodeDl,
            Tags.nodeDt,
            Tags.nodeEm,
            Tags.nodeEmbed,
            Tags.nodeFencedframe,
            Tags.nodeFieldset,
            Tags.nodeFigcaption,
            Tags.nodeFigure,
            Tags.nodeFooter,
            Tags.nodeForm,
            Tags.nodeH1,
            Tags.nodeHead,
            Tags.nodeHeader,
            Tags.nodeHgroup,
            Tags.nodeHr,
            Tags.nodeHtml,
            Tags.nodeI,
            Tags.nodeIframe,
            Tags.nodeImg,
            Tags.nodeInput,
            Tags.nodeIns,
            Tags.nodeKbd,
            Tags.nodeLabel,
            Tags.nodeLegend,
            Tags.nodeLi,
            Tags.nodeLink,
            Tags.nodeMain,
            Tags.nodeMap,
            Tags.nodeMark,
            Tags.nodeMenu,
            Tags.nodeMeta,
            Tags.nodeMeter,
            Tags.nodeNav,
            Tags.nodeNoscript,
            Tags.nodeObject,
            Tags.nodeOl,
            Tags.nodeOptgroup,
            Tags.nodeOption,
            Tags.nodeOutput,
            Tags.nodeP,
            Tags.nodePicture,
            Tags.nodePre,
            Tags.nodeProgress,
            Tags.nodeQ,
            Tags.nodeRp,
            Tags.nodeRt,
            Tags.nodeRuby,
            Tags.nodeS,
            Tags.nodeSamp,
            Tags.nodeScript,
            Tags.nodeSearch,
            Tags.nodeSection,
            Tags.nodeSelect,
            Tags.nodeSelectedcontent,
            Tags.nodeSlot,
            Tags.nodeSmall,
            Tags.nodeSource,
            Tags.nodeSpan,
            Tags.nodeStrong,
            Tags.nodeStyle,
            Tags.nodeSub,
            Tags.nodeSummary,
            Tags.nodeSup,
            Tags.nodeTable,
            Tags.nodeTbody,
            Tags.nodeTd,
            Tags.nodeTemplate,
            Tags.nodeTextarea,
            Tags.nodeTfoot,
            Tags.nodeTh,
            Tags.nodeThead,
            Tags.nodeTime,
            Tags.nodeTitle,
            Tags.nodeTr,
            Tags.nodeTrack,
            Tags.nodeU,
            Tags.nodeUl,
            Tags.nodeVar,
            Tags.nodeVideo,
            Tags.nodeWbr
        ),
        "head" to setOf(Tags.title, Tags.meta, Tags.style),
        "html" to setOf(Tags.head, Tags.body, Tags.style)
    )

    private fun handleKey(keyText: String, element: Int, result: CompletionResultSet) {
        println("language111111 ${I18nUtils.getMessage("settings.language.title")}")
        if (htmlTagPropertyMap.containsKey(keyText.lowercase())) {
            htmlTagPropertyMap[keyText]?.forEach {
                val tailText = if (it.type == TagType.NODE) "node" else "attr"
                val color = if (it.type == TagType.NODE) JBColor.GREEN else JBColor.CYAN
                val lookupElement = LookupElementBuilder.create(it.name)
                    .withTypeText(it.desc)
//                    .withItemTextForeground(color)
                    .withTailText(" (${tailText})", true)
                    .withInsertHandler { context, _ ->
                        if (it.type == TagType.NODE) {
                            when (element) {
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
                        if (it.type == TagType.ATTR) {
                            context.editor.document.insertString(context.tailOffset, ": ")
                            context.editor.caretModel.moveToOffset(context.tailOffset)
                        }
                    }
                if (element == 1) {
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
                    println("is YAMLScalar, parent: ${element.parent}, parent.text: ${element.parent.text} element: ${element.text}")

                    var t = 0
                    if (element is YAMLPlainTextImpl) {
                        if (element.text == "-IntellijIdeaRulezzz") {
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
