package com.lizongying.language.tags

enum class GlobalAttribute(
    val label: String,
    val stage: Stage = Stage.NORMAL,
    val parents: List<Element> = emptyList(),
    val desc: String = "",
    val descCN: String = "",
    val descTW: String = "",
    val field: Field = Field.STRING
) {

    ANCHOR(
        "anchor",
        Stage.NON_STANDARD,
        emptyList(),
        "Associates a positioned element with an anchor element. The attribute's value is the id value of the element you want to anchor the positioned element to. The element can then be positioned using CSS anchor positioning.",
        "将一个定位元素与一个锚点元素关联。该属性的值是你希望将定位元素锚定到的元素的id值。然后，可以使用CSS锚点定位来定位该元素。",
        "將一個定位元素與一個錨點元素關聯。該屬性的值是你希望將定位元素錨定到的元素的id值。然後，可以使用CSS錨點定位來定位該元素。",
    ),

    AUTOCORRECT(
        "autocorrect",
        Stage.NORMAL,
        emptyList(),
        "Controls whether input text is automatically corrected for spelling errors.\nThis can be applied to elements that have editable text except for <input> elements with the attribute: type=\"password\", type=\"email\", or type=\"url\".",
        "控制输入的文本是否自动纠正拼写错误。\n此属性可以应用于具有可编辑文本的元素，但不适用于 <input> 元素，其属性为：type=\"password\"、type=\"email\" 或 type=\"url\"。",
        "控制輸入的文本是否自動糾正拼寫錯誤。\n此屬性可以應用於具有可編輯文本的元素，但不適用於 <input> 元素，其屬性為：type=\"password\"、type=\"email\" 或 type=\"url\"。",
    ),
    AUTOFOCUS(
        "autofocus",
        Stage.NORMAL,
        emptyList(),
        "Indicates that an element is to be focused on page load, or as soon as the <dialog> it is part of is displayed. This attribute is a boolean, initially false.",
        "表示一个元素将在页面加载时自动聚焦，或者在其所属的 <dialog> 显示时被聚焦。该属性是一个布尔值，初始化为 false。",
        "表示一個元素將在頁面加載時自動聚焦，或者在其所屬的 <dialog> 顯示時被聚焦。該屬性是一個布爾值，初始化爲 false。",
    ),

    EXPORTPARTS(
        "exportparts",
        Stage.NORMAL,
        emptyList(),
        "Used to transitively export shadow parts from a nested shadow tree into a containing light tree.",
        "用于将隐藏部分从一个嵌套的影子树（shadow tree）中过渡性地导出到一个包含该树的常规树（light tree）中。",
        "用于將隱藏部分從一個嵌套的影子樹（shadow tree）中過渡性地導出到一個包含該樹的常規樹（light tree）中。",
    ),

    INERT(
        "inert",
        Stage.NORMAL,
        emptyList(),
        "A boolean value that makes the browser disregard user input events for the element. Useful when click events are present.",
        "一个布尔值，使浏览器忽略该元素的用户输入事件。在有点击事件的情况下很有用。",
        "一個布爾值，使瀏覽器忽略該元素的用户輸入事件。在有點擊事件的情况下很有用。",
    ),

    IS(
        "is",
        Stage.NORMAL,
        emptyList(),
        "Allows you to specify that a standard HTML element should behave like a registered custom built-in element (see Using custom elements for more details).",
        "允许指定标准 HTML 元素的行为如同已注册的自定义内置元素一样（有关更多详细信息，请参阅使用自定义元素）。\n备注： item* 属性是 WHATWG HTML 微数据特性的一部分。",
        "允許指定標準 HTML 元素的行爲如同已注册的自定義内置元素一樣（有關更多詳細信息，請參閲使用自定義元素）。\n備注： item* 屬性是 WHATWG HTML 微數據特性的一部分。",
    ),
    ITEMID(
        "itemid",
        Stage.NORMAL,
        emptyList(),
        "The unique, global identifier of an item.",
        "项的唯一全局标识符。",
        "項的唯一全局標識符。",
    ),

    ITEMREF(
        "itemref",
        Stage.NORMAL,
        emptyList(),
        "Properties that are not descendants of an element with the itemscope attribute can be associated with the item using an itemref. It provides a list of element ids (not itemids) with additional properties elsewhere in the document.",
        "只有不是具有 itemscope 属性的元素的后代，它的属性才可以与使用 itemref 项目相关联。它提供了元素 ID 列表（而不是 itemid）以及文档中其他位置的其他属性。",
        "只有不是具有 itemscope 屬性的元素的後代，它的屬性纔可以與使用 itemref 項目相關聯。它提供了元素 ID 列表（而不是 itemid）以及文檔中其他位置的其他屬性。",
    ),
    ITEMSCOPE(
        "itemscope",
        Stage.NORMAL,
        emptyList(),
        "itemscope (usually) works along with itemtype to specify that the HTML contained in a block is about a particular item. itemscope creates the Item and defines the scope of the itemtype associated with it. itemtype is a valid URL of a vocabulary (such as schema.org) that describes the item and its properties context.",
        "itemscope（通常）与 itemtype 一起使用，以指定包含在关于特定项目代码块中的 HTML。itemscope 创建数据项并定义与之关联的 itemtype 的范围。itemtype 是描述项及其属性上下文的词汇表（例如 schema.org）的有效 URL。",
        "itemscope（通常）與 itemtype 一起使用，以指定包含在關于特定項目代碼塊中的 HTML。itemscope 創建數據項并定義與之關聯的 itemtype 的范圍。itemtype 是描述項及其屬性上下文的詞匯表（例如 schema.org）的有效 URL。",
    ),
    ITEMTYPE(
        "itemtype",
        Stage.NORMAL,
        emptyList(),
        "Specifies the URL of the vocabulary that will be used to define itemprops (item properties) in the data structure. itemscope is used to set the scope of where in the data structure the vocabulary set by itemtype will be active.",
        "指定将用于在数据结构中定义 itemprops（项属性）的词汇表的 URL。itemscope 用于设置数据结构中按 itemtype 设置的词汇表的生效范围。",
        "指定將用于在數據結構中定義 itemprops（項屬性）的詞匯表的 URL。itemscope 用于設置數據結構中按 itemtype 設置的詞匯表的生效范圍。",
    ),

    NONCE(
        "nonce",
        Stage.NORMAL,
        emptyList(),
        "A cryptographic nonce (\"number used once\") which can be used by Content Security Policy to determine whether or not a given fetch will be allowed to proceed.",
        "一个加密的 nonce（“只使用一次的数字”），可以被内容安全策略使用，以确定是否允许进行给定的获取。",
        "一個加密的 nonce（“只使用一次的數字”），可以被内容安全策略使用，以確定是否允許進行給定的穫取。",
    ),
    PART(
        "part",
        Stage.NORMAL,
        emptyList(),
        "A space-separated list of the part names of the element. Part names allows CSS to select and style specific elements in a shadow tree via the ::part pseudo-element.",
        "元素的部件名称的空格分隔列表。Part 名称允许 CSS 通过 ::part 伪元素选择和设置影子树中的特定元素。",
        "元素的部件名稱的空格分隔列表。Part 名稱允許 CSS 通過 ::part 僞元素選擇和設置影子樹中的特定元素。",
    ),
    POPOVER(
        "popover",
        Stage.NORMAL,
        emptyList(),
        "Used to designate an element as a popover element (see Popover API). Popover elements are hidden via display: none until opened via an invoking/control element (i.e., a <button> or <input type=\"button\"> with a popovertarget attribute) or a HTMLElement.showPopover() call.",
        "用于将某个元素指定为弹出式元素（详见 Popover API）。弹出式元素通过 display: none 隐藏，直到通过调用/控制元素（即 <button> 或 <input type=\"button\"> 带有 popovertarget 属性）或 HTMLElement.showPopover() 调用而打开。",
        "用于將某個元素指定爲彈出式元素（詳見 Popover API）。彈出式元素通過 display: none 隱藏，直到通過調用/控制元素（即 <button> 或 <input type=\"button\"> 帶有 popovertarget 屬性）或 HTMLElement.showPopover() 調用而打開。",
    ),

    VIRTUALKEYBOARDPOLICY(
        "virtualkeyboardpolicy",
        Stage.EXPERIMENTAL,
        emptyList(),
        "An enumerated attribute used to control the on-screen virtual keyboard behavior on devices such as tablets, mobile phones, or other devices where a hardware keyboard may not be available for elements that its content is editable (for example, it is an <input> or <textarea> element, or an element with the contenteditable attribute set).\nauto or an empty string, which automatically shows the virtual keyboard when the element is focused or tapped.\nmanual, which decouples focus and tap on the element from the virtual keyboard's state.",
        "一个枚举属性，用于控制屏幕上的虚拟键盘行为，如平板电脑、手机或其他设备上的硬件键盘可能无法使用的元素，也使用 contenteditable 属性。\nauto 或一个空字符串，当元素被聚焦或点击时自动显示虚拟键盘。\nmanual，它将焦点和元素上的点击与虚拟键盘的状态解耦。",
        "一個枚舉屬性，用于控制屏幕上的虚擬鍵盤行爲，如平板電腦、手機或其他設備上的硬件鍵盤可能無法使用的元素，也使用 contenteditable 屬性。\nauto 或一個空字符串，當元素被聚焦或點擊時自動顯示虚擬鍵盤。\nmanual，它將焦點和元素上的點擊與虚擬鍵盤的狀態解耦。",
    ),
    WRITINGSUGGESTIONS(
        "writingsuggestions",
        Stage.NORMAL,
        emptyList(),
        "An enumerated attribute indicating if browser-provided writing suggestions should be enabled under the scope of the element or not.\nfalse, which disables the browser's writing suggestions.\ntrue or an empty string, which enables writing suggestions.",
        "一个枚举属性，指示是否在该元素范围内启用浏览器提供的写作建议。\nfalse，表示禁用浏览器的写作建议。\ntrue 或空字符串，表示启用写作建议。",
        "一個枚舉屬性，指示是否在該元素範圍內啟用瀏覽器提供的寫作建議。\nfalse，表示禁用瀏覽器的寫作建議。\ntrue 或空字符串，表示啟用寫作建議。",
    );

    companion object {
        val all
            get() = enumValues<GlobalAttribute>()
    }
}