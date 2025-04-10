package com.lizongying.language.tags

// https://developer.mozilla.org/zh-CN/docs/Web/HTML/Global_attributes
enum class Event(
    val label: String,
    val stage: Stage = Stage.NORMAL,
    val parents: List<Element> = emptyList(),
    val desc: String = "",
    val descCN: String = "",
    val descTW: String = ""
) {
    ONABORT(
        "onabort",
        Stage.NORMAL,
        emptyList(),
        "The loading of a media is aborted",
        "事件在视频/音频（audio/video）终止加载时触发。图像的加载被中断。 ( <object>)",
        "事件在視頻/音頻（audio/video）終止加載時觸發。圖像的加載被中斷。 ( <object>)"
    ),

    ONAFTERPRINT(
        "onafterprint",
        Stage.NORMAL,
        emptyList(),
        "A page has started printing",
        "该事件在页面已经开始打印，或者打印窗口已经关闭时触发",
        "該事件在頁面已經開始打印，或者打印窗口已經關閉時觸發"
    ),
    ONANIMATIONEND(
        "onanimationend",
        Stage.NORMAL,
        emptyList(),
        "A CSS animation has completed",
        "该事件在 CSS 动画结束播放时触发",
        "該事件在 CSS 動畫結束播放時觸發"
    ),
    ONANIMATIONITERATION(
        "onanimationiteration",
        Stage.NORMAL,
        emptyList(),
        "A CSS animation is repeated",
        "该事件在 CSS 动画重复播放时触发",
        "該事件在 CSS 動畫重複播放時觸發"
    ),
    ONANIMATIONSTART(
        "onanimationstart",
        Stage.NORMAL,
        emptyList(),
        "A CSS animation has started",
        "该事件在 CSS 动画开始播放时触发",
        "該事件在 CSS 動畫開始播放時觸發"
    ),
    ONBEFOREPRINT(
        "onbeforeprint",
        Stage.NORMAL,
        emptyList(),
        "A page is about to be printed",
        "该事件在页面即将开始打印时触发",
        "該事件在頁面即將開始打印時觸發"
    ),

//    ONAUTOCOMPLETE("onautocomplete",Stage.NORMAL,emptyList(),"", "", ""),
//    ONAUTOCOMPLETEERROR("onautocompleteerror",Stage.NORMAL,emptyList(),"", "", ""),

    ONBEFOREUNLOAD(
        "onbeforeunload",
        Stage.NORMAL,
        emptyList(),
        "",
        "该事件在即将离开页面（刷新或关闭）时触发",
        "該事件在即將離開頁面（刷新或關閉）時觸發"
    ),

    ONBLUR(
        "onblur",
        Stage.NORMAL,
        emptyList(),
        "An element loses focus",
        "当一个元素失去焦点的时候 blur 事件被触发。它和 focusout 事件的主要区别是 focusout 支持冒泡。",
        "當一個元素失去焦點的時候 blur 事件被觸發。它和 focusout 事件的主要區别是 focusout 支持冒泡。"
    ),

    // https://html.spec.whatwg.org/multipage/indices.html#event
    // https://developer.mozilla.org/zh-CN/docs/Web/API/Animation/cancel_event
    ONCANCEL(
        "oncancel",
        Stage.EXPERIMENTAL,
        emptyList(),
        "The cancel event of the Animation interface is fired when the Animation.cancel() method is called or when the animation enters the \"idle\" play state from another state, such as when the animation is removed from an element before it finishes playing.",
        "Web Animations API 的 Animation 接口的 oncancel 属性是 cancel 事件的事件处理程序。",
        "Web Animations API 的 Animation 接口的 oncancel 屬性是 cancel 事件的事件處理程序。"
    ),

    ONCANPLAY(
        "oncanplay",
        Stage.NORMAL,
        emptyList(),
        "The browser can start playing a media (has buffered enough to begin)",
        "事件在用户可以开始播放视频/音频（audio/video）时触发。",
        "事件在用户可以開始播放視頻/音頻（audio/video）時觸發。"
    ),
    ONCANPLAYTHROUGH(
        "oncanplaythrough",
        Stage.NORMAL,
        emptyList(),
        "The browser can play through a media without stopping for buffering",
        "事件在视频/音频（audio/video）可以正常播放且无需停顿和缓冲时触发。",
        "事件在視頻/音頻（audio/video）可以正常播放且無需停頓和緩衝時觸發。"
    ),
    ONCHANGE(
        "onchange",
        Stage.NORMAL,
        emptyList(),
        "The content of a form element has changed",
        "该事件在表单元素的内容改变时触发( <input>, <keygen>, <select>, 和 <textarea>)",
        "該事件在表單元素的内容改變時觸發( <input>, <keygen>, <select>, 和 <textarea>)"
    ),
    ONCLICK(
        "onclick",
        Stage.NORMAL,
        emptyList(),
        "An element is clicked on",
        "当用户点击某个对象时调用的事件句柄。",
        "當用户點擊某個對象時調用的事件句柄。"
    ),
    ONCLOSE(
        "onclose",
        Stage.NORMAL,
        emptyList(),
        "The close event is fired when a connection with a WebSocket is closed.",
        "WebSocket.onclose 属性返回一个事件监听器，这个事件监听器将在 WebSocket 连接的readyState 变为 CLOSED时被调用，它接收一个名字为“close”的 CloseEvent 事件。",
        "WebSocket.onclose 屬性返回一個事件監聽器，這個事件監聽器將在 WebSocket 連接的readyState 變爲 CLOSED時被調用，它接收一個名字爲“close”的 CloseEvent 事件。"
    ),
    ONCONTEXTMENU(
        "oncontextmenu",
        Stage.NORMAL,
        emptyList(),
        "An element is right-clicked to open a context menu",
        "在用户点击鼠标右键打开上下文菜单时触发",
        "在用户點擊鼠標右鍵打開上下文菜單時觸發"
    ),
    ONCUECHANGE(
        "oncuechange",
        Stage.NORMAL,
        emptyList(),
        "The cuechange event fires when a TextTrack has changed the currently displaying cues. The event is fired on both the TextTrack and the HTMLTrackElement in which it's being presented, if any.",
        "oncuechange 属性属于 GlobalEventHandlers，是一个处理 cuechange 事件的事件处理器。当TextTrack更改了当前显示的提示时，cuechange 事件将会触发。",
        "oncuechange 屬性屬于 GlobalEventHandlers，是一個處理 cuechange 事件的事件處理器。當TextTrack更改了當前顯示的提示時，cuechange 事件將會觸發。"
    ),

    ONCOPY(
        "oncopy",
        Stage.NORMAL,
        emptyList(),
        "The content of an element is copied",
        "该事件在用户拷贝元素内容时触发",
        "該事件在用户拷貝元素内容時觸發"
    ),
    ONCUT(
        "oncut",
        Stage.NORMAL,
        emptyList(),
        "The content of an element is cut",
        "该事件在用户剪切元素内容时触发",
        "該事件在用户剪切元素内容時觸發"
    ),

    ONDBLCLICK(
        "ondblclick",
        Stage.NORMAL,
        emptyList(),
        "An element is double-clicked",
        "当用户双击某个对象时调用的事件句柄。",
        "該事件在用户剪切元素内容時觸發"
    ),
    ONDRAG(
        "ondrag",
        Stage.NORMAL,
        emptyList(),
        "An element is being dragged",
        "该事件在用户完成元素的拖动时触发",
        "該事件在用户完成元素的拖動時觸發"
    ),

//    ONDRAGDROP(
//        "ondragdrop",
//        Stage.NON_STANDARD,
//        emptyList(),
//        "The user drops an object onto the browser window, such as dropping a file on the browser window.",
//        "",
//        ""
//    ),

    ONDRAGEND(
        "ondragend",
        Stage.NORMAL,
        emptyList(),
        "Dragging of an element has ended",
        "该事件在用户完成元素的拖动时触发",
        "該事件在用户完成元素的拖動時觸發"
    ),
    ONDRAGENTER(
        "ondragenter",
        Stage.NORMAL,
        emptyList(),
        "A dragged element enters the drop target",
        "该事件在拖动的元素进入放置目标时触发",
        "該事件在拖動的元素進入放置目標時觸發"
    ),
    ONDRAGLEAVE(
        "ondragleave",
        Stage.NORMAL,
        emptyList(),
        "A dragged element leaves the drop target",
        "该事件在拖动元素离开放置目标时触发",
        "該事件在拖動元素離開放置目標時觸發"
    ),
    ONDRAGOVER(
        "ondragover",
        Stage.NORMAL,
        emptyList(),
        "A dragged element is over the drop target",
        "该事件在拖动元素在放置目标上时触发",
        "該事件在拖動元素在放置目標上時觸發"
    ),
    ONDRAGSTART(
        "ondragstart",
        Stage.NORMAL,
        emptyList(),
        "Dragging of an element has started",
        "该事件在用户开始拖动元素时触发",
        "該事件在用户開始拖動元素時觸發"
    ),
    ONDROP(
        "ondrop",
        Stage.NORMAL,
        emptyList(),
        "A dragged element is dropped on the target",
        "该事件在拖动元素放置在目标区域时触发",
        "該事件在拖動元素放置在目標區域時觸發"
    ),
    ONDURATIONCHANGE(
        "ondurationchange",
        Stage.NORMAL,
        emptyList(),
        "The duration of a media is changed",
        "事件在视频/音频（audio/video）的时长发生变化时触发。",
        "事件在視頻/音頻（audio/video）的時長發生變化時觸發。"
    ),
    ONEMPTIED(
        "onemptied",
        Stage.NORMAL,
        emptyList(),
        "The emptied event is fired when the media has become empty; for example, this event is sent if the media has already been loaded (or partially loaded), and the load() method is called to reload it.",
        "当期播放列表为空时触发",
        "當期播放列表爲空時觸發"
    ),
    ONENDED(
        "onended",
        Stage.NORMAL,
        emptyList(),
        "A media has reach the end (\"thanks for listening\")",
        "事件在视频/音频（audio/video）播放结束时触发。",
        "事件在視頻/音頻（audio/video）播放結束時觸發。"
    ),
    ONERROR(
        "onerror",
        Stage.NORMAL,
        emptyList(),
        "An error has occurred while loading a file",
        "事件在视频/音频（audio/video）数据加载期间发生错误时触发。在加载文档或图像时发生错误。 ( <object>, <body>和 <frameset>)",
        "事件在視頻/音頻（audio/video）數據加載期間發生錯誤時觸發。在加載文檔或圖像時發生錯誤。 ( <object>, <body>和 <frameset>)"
    ),
    ONFOCUS("onfocus", Stage.NORMAL, emptyList(), "An element gets focus", "元素获取焦点时触发", ""),

    ONFOCUSIN(
        "onfocusin",
        Stage.NORMAL,
        emptyList(),
        "An element is about to get focus",
        "元素即将获取焦点时触发",
        "元素获取焦点时触发"
    ),
    ONFOCUSOUT(
        "onfocusout",
        Stage.NORMAL,
        emptyList(),
        "An element is about to lose focus",
        "元素即将失去焦点时触发",
        "元素穫取焦點時元素即將失去焦點時觸發"
    ),
    ONFULLSCREENCHANGE(
        "onfullscreenchange",
        Stage.NORMAL,
        emptyList(),
        "An element is displayed in fullscreen mode",
        "fullscreenchange 事件会在浏览器进入或退出全屏模式后立即触发。",
        "fullscreenchange 事件會在瀏覽器進入或退出全屏模式後立即觸發"
    ),
    ONFULLSCREENERROR(
        "onfullscreenerror",
        Stage.NORMAL,
        emptyList(),
        "An element can not be displayed in fullscreen mode",
        "fullscreenerror 事件在浏览器不能切换全屏模式时触发。",
        "fullscreenerror 事件在瀏覽器不能切换全屏模式時觸發。"
    ),
    ONHASHCHANGE(
        "onhashchange",
        Stage.NORMAL,
        emptyList(),
        "There has been changes to the anchor part of a URL",
        "该事件在当前 URL 的锚部分发生修改时触发。",
        "該事件在當前 URL 的錨部分發生修改時觸發。"
    ),

    ONINPUT(
        "oninput",
        Stage.NORMAL,
        emptyList(),
        "An element gets user input",
        "元素获取用户输入时触发",
        "元素穫取用户輸入時觸發"
    ),
    ONINVALID(
        "oninvalid",
        Stage.NORMAL,
        emptyList(),
        "An element is invalid",
        "若一个可提交元素在检查有效性时，不符合对它的约束条件，则会触发 invalid 事件。",
        "若一個可提交元素在檢查有效性時，不符合對它的約束條件，則會觸發 invalid 事件。"
    ),
    ONKEYDOWN("onkeydown", Stage.NORMAL, emptyList(), "A key is down", "某个键盘按键被按下。", "某個鍵盤按鍵被按下"),
    ONKEYPRESS(
        "onkeypress",
        Stage.NORMAL,
        emptyList(),
        "A key is pressed",
        "某个键盘按键被按下并松开。",
        "某個鍵盤按鍵被按下并松開"
    ),
    ONKEYUP("onkeyup", Stage.NORMAL, emptyList(), "A key is released", "某个键盘按键被松开。", "某個鍵盤按鍵被松開。"),
    ONLOAD(
        "onload",
        Stage.NORMAL,
        emptyList(),
        "An object has loaded",
        "一张页面或一幅图像完成加载。",
        "一張頁面或一幅圖像完成加載。"
    ),
    ONLOADEDDATA(
        "onloadeddata",
        Stage.NORMAL,
        emptyList(),
        "Media data is loaded",
        "事件在浏览器加载视频/音频（audio/video）当前帧时触发触发。",
        "事件在瀏覽器加載視頻/音頻（audio/video）當前幀時觸發觸發。"
    ),
    ONLOADEDMETADATA(
        "onloadedmetadata",
        Stage.NORMAL,
        emptyList(),
        "Meta data (like dimensions and duration) are loaded",
        "事件在指定视频/音频（audio/video）的元数据加载后触发。",
        "事件在指定視頻/音頻（audio/video）的元數據加載後觸發。"
    ),
    ONLOADSTART(
        "onloadstart",
        Stage.NORMAL,
        emptyList(),
        "The browser starts looking for the specified media",
        "事件在浏览器开始寻找指定视频/音频（audio/video）触发。",
        "事件在瀏覽器開始尋找指定視頻/音頻（audio/video）觸發。"
    ),

    ONMESSAGE(
        "onmessage",
        Stage.NORMAL,
        emptyList(),
        "A message is received through the event source",
        "该事件通过或者从对象(WebSocket, Web Worker, Event Source 或者子 frame 或父窗口)接收到消息时触发",
        "該事件通過或者從對象(WebSocket, Web Worker, Event Source 或者子 frame 或父窗口)接收到消息時觸發"
    ),
    ONMOUSEDOWN(
        "onmousedown",
        Stage.NORMAL,
        emptyList(),
        "The mouse button is pressed over an element",
        "鼠标按钮被按下。",
        "鼠標按鈕被按下。"
    ),
    ONMOUSEENTER(
        "onmouseenter",
        Stage.NORMAL,
        emptyList(),
        "The pointer is moved onto an element",
        "当鼠标指针移动到元素上时触发。",
        "當鼠標指針移動到元素上時觸發。"
    ),
    ONMOUSELEAVE(
        "onmouseleave",
        Stage.NORMAL,
        emptyList(),
        "The pointer is moved out of an element",
        "当鼠标指针移出元素时触发",
        "當鼠標指針移出元素時觸發"
    ),
    ONMOUSEMOVE(
        "onmousemove",
        Stage.NORMAL,
        emptyList(),
        "The pointer is moved over an element",
        "鼠标被移动。",
        "鼠標被移動。"
    ),
    ONMOUSEOUT(
        "onmouseout",
        Stage.NORMAL,
        emptyList(),
        "The pointer is moved out of an element",
        "鼠标从某元素移开。",
        "鼠標從某元素移開。"
    ),
    ONMOUSEOVER(
        "onmouseover",
        Stage.NORMAL,
        emptyList(),
        "The pointer is moved onto an element",
        "鼠标移到某元素之上。",
        "鼠標移到某元素之上。"
    ),
    ONMOUSEUP(
        "onmouseup",
        Stage.NORMAL,
        emptyList(),
        "A user releases a mouse button over an element",
        "鼠标按键被松开。",
        "鼠標按鍵被松開。"
    ),
    ONMOUSEWHEEL(
        "onmousewheel",
        Stage.DEPRECATED,
        emptyList(),
        "Use the wheel event instead",
        "使用 onwheel 事件替代",
        "使用 onwheel 事件替代"
    ),

//    ONMOVE("onmove", Stage.NORMAL, emptyList(), "The user or script moves a window or frame.", "", ""),

    ONOFFLINE(
        "onoffline",
        Stage.NORMAL,
        emptyList(),
        "The browser starts working offline",
        "该事件在浏览器开始离线工作时触发。",
        "該事件在瀏覽器開始離綫工作時觸發。"
    ),
    ONONLINE(
        "ononline",
        Stage.NORMAL,
        emptyList(),
        "The browser starts working online",
        "该事件在浏览器开始在线工作时触发。",
        "該事件在瀏覽器開始在綫工作時觸發"
    ),
    ONOPEN(
        "onopen",
        Stage.NORMAL,
        emptyList(),
        "A connection with the event source is opened",
        "当WebSocket 的连接状态readyState 变为1时调用",
        "當WebSocket 的連接狀態readyState 變爲1時調用"
    ),
    ONPAGEHIDE(
        "onpagehide",
        Stage.NORMAL,
        emptyList(),
        "User navigates away from a webpage",
        "该事件在用户离开当前网页跳转到另外一个页面时触发",
        "該事件在用户離開當前網頁跳轉到另外一個頁面時觸發"
    ),
    ONPAGESHOW(
        "onpageshow",
        Stage.NORMAL,
        emptyList(),
        "User navigates to a webpage",
        "该事件在用户访问页面时触发",
        "該事件在用户訪問頁面時觸發"
    ),
    ONPASTE(
        "onpaste",
        Stage.NORMAL,
        emptyList(),
        "Some content is pasted in an element",
        "该事件在用户粘贴元素内容时触发",
        "該事件在用户粘貼元素内容時觸發"
    ),

    ONPAUSE(
        "onpause",
        Stage.NORMAL,
        emptyList(),
        "A media is paused",
        "事件在视频/音频（audio/video）暂停时触发。",
        "事件在視頻/音頻（audio/video）暫停時觸發。"
    ),
    ONPLAY(
        "onplay",
        Stage.NORMAL,
        emptyList(),
        "The media has started or is no longer paused",
        "事件在视频/音频（audio/video）开始播放时触发。",
        "事件在視頻/音頻（audio/video）開始播放時觸發。"
    ),
    ONPLAYING(
        "onplaying",
        Stage.NORMAL,
        emptyList(),
        "The media is playing after being paused or buffered",
        "事件在视频/音频（audio/video）暂停或者在缓冲后准备重新开始播放时触发。",
        "事件在視頻/音頻（audio/video）暫停或者在緩衝後準備重新開始播放時觸發。"
    ),
    ONPOPSTATE(
        "onpopstate",
        Stage.NORMAL,
        emptyList(),
        "The window's history changes",
        "该事件在窗口的浏览历史（history 对象）发生改变时触发。",
        "該事件在窗口的瀏覽歷史（history 對象）發生改變時觸發。"
    ),
    ONPROGRESS(
        "onprogress",
        Stage.NORMAL,
        emptyList(),
        "The browser is downloading media data",
        "事件在浏览器下载指定的视频/音频（audio/video）时触发。",
        "事件在瀏覽器下載指定的視頻/音頻（audio/video）時觸發。"
    ),
    ONRATECHANGE(
        "onratechange",
        Stage.NORMAL,
        emptyList(),
        "The playing speed of a media is changed",
        "事件在视频/音频（audio/video）的播放速度发送改变时触发。",
        "事件在視頻/音頻（audio/video）的播放速度發送改變時觸發。"
    ),
    ONRESET("onreset", Stage.NORMAL, emptyList(), "A form is reset", "表单重置时触发", "表單重置時觸發"),
    ONRESIZE(
        "onresize",
        Stage.NORMAL,
        emptyList(),
        "The document view is resized",
        "窗口或框架被重新调整大小。",
        "窗口或框架被重新調整大小。"
    ),
    ONSCROLL(
        "onscroll",
        Stage.NORMAL,
        emptyList(),
        "An scrollbar is being scrolled",
        "当文档被滚动时发生的事件。",
        "當文檔被滚動時發生的事件。"
    ),
    ONSEARCH(
        "onsearch",
        Stage.NORMAL,
        emptyList(),
        "Something is written in a search field",
        "用户向搜索域输入文本时触发 ( <input=\"search\">)",
        "用户嚮搜索域輸入文本時觸發 ( <input=\"search\">)"
    ),
    ONSEEKED(
        "onseeked",
        Stage.NORMAL,
        emptyList(),
        "Skipping to a media position is finished",
        "事件在用户重新定位视频/音频（audio/video）的播放位置后触发。",
        "事件在用户重新定位視頻/音頻（audio/video）的播放位置後觸發。"
    ),
    ONSEEKING(
        "onseeking",
        Stage.NORMAL,
        emptyList(),
        "Skipping to a media position is started",
        "事件在用户开始重新定位视频/音频（audio/video）时触发。",
        "事件在用户開始重新定位視頻/音頻（audio/video）時觸發。"
    ),
    ONSELECT(
        "onselect",
        Stage.NORMAL,
        emptyList(),
        "User selects some text",
        "用户选取文本时触发 ( <input> 和 <textarea>)",
        "該事件當 <menu> 元素在上下文菜單顯示時觸發"
    ),
    ONSHOW(
        "onshow",
        Stage.NORMAL,
        emptyList(),
        "A <menu> element is shown as a context menu",
        "该事件当 <menu> 元素在上下文菜单显示时触发",
        ""
    ),
//    ONSORT("onsort", Stage.NON_STANDARD, emptyList(), "", "", ""),
    ONSTALLED(
        "onstalled",
        Stage.NORMAL,
        emptyList(),
        "The browser is trying to get unavailable media data",
        "事件在浏览器获取媒体数据，但媒体数据不可用时触发。",
        "事件在瀏覽器穫取媒體數據，但媒體數據不可用時觸發。"
    ),
    ONSTORAGE(
        "onstorage",
        Stage.NORMAL,
        emptyList(),
        "A Web Storage area is updated",
        "该事件在 Web Storage(HTML 5 Web 存储)更新时触发",
        ""
    ),
    ONSUBMIT("onsubmit", Stage.NORMAL, emptyList(), "A form is submitted", "表单提交时触发", "表單提交時觸發"),
    ONSUSPEND(
        "onsuspend",
        Stage.NORMAL,
        emptyList(),
        "The browser is intentionally not getting media data",
        "事件在浏览器读取媒体数据中止时触发。",
        "事件在浏览器读取媒体数据中止时触发。"
    ),
    ONTIMEUPDATE(
        "ontimeupdate",
        Stage.NORMAL,
        emptyList(),
        "The playing position has changed (the user moves to a different point in the media)",
        "事件在当前的播放位置发送改变时触发。",
        "事件在當前的播放位置發送改變時觸發。"
    ),
    ONTOGGLE(
        "ontoggle",
        Stage.NORMAL,
        emptyList(),
        "The user opens or closes the <details> element",
        "该事件在用户打开或关闭 <details> 元素时触发",
        "該事件在用户打開或關閉 <details> 元素時觸發"
    ),

    ONTOUCHCANCEL("ontouchcancel", Stage.NORMAL, emptyList(), "The touch is interrupted", "touchcancel 事件在触点被中断时触发，中断方式基于特定实现而有所不同（例如，创建了太多的触点）。", "touchcancel 事件在觸點被中斷時觸發，中斷方式基于特定實現而有所不同（例如，創建了太多的觸點）。"),
    ONTOUCHEND("ontouchend", Stage.NORMAL, emptyList(), "A finger is removed from a touch screen", "touchend 事件在一个或多个触点从触控平面上移开时触发。注意，也有可能触发 touchcancel 事件。", "touchend 事件在一個或多個觸點從觸控平面上移開時觸發。注意，也有可能觸發 touchcancel 事件。"),
    ONTOUCHMOVE("ontouchmove", Stage.NORMAL, emptyList(), "A finger is dragged across the screen", "touchmove 事件在触点于触控平面上移动时触发。", "touchmove 事件在觸點于觸控平面上移動時觸發。"),
    ONTOUCHSTART("ontouchstart", Stage.NORMAL, emptyList(), "A finger is placed on a touch screen", "touchstart 事件在一个或多个触点与触控设备表面接触时被触发。", "touchstart 事件在一個或多個觸點與觸控設備表面接觸時被觸發。"),
    ONTRANSITIONEND(
        "ontransitionend",
        Stage.NORMAL,
        emptyList(),
        "A CSS transition has completed",
        "该事件在 CSS 完成过渡后触发。",
        "該事件在 CSS 完成過渡後觸發。"
    ),
    ONUNLOAD(
        "onunload",
        Stage.NORMAL,
        emptyList(),
        "A page has unloaded",
        "用户退出页面。 ( <body> 和 <frameset>)",
        "用户退出頁面。 ( <body> 和 <frameset>)"
    ),
    ONVISIBILITYCHANGE(
        "onvisibilitychange",
        Stage.NORMAL,
        emptyList(),
        "The visibilitychange event is fired at the document when the contents of its tab have become visible or have been hidden.",
        "该事件用于检测当前页面的可见性状态是否发生变化。",
        "該事件用于檢測當前頁面的可見性狀態是否發生變化。"
    ),
    ONVOLUMECHANGE(
        "onvolumechange",
        Stage.NORMAL,
        emptyList(),
        "The volume of a media is changed (includes muting)",
        "事件在音量发生改变时触发。",
        "事件在音量發生改變時觸發。"
    ),
    ONWAITING(
        "onwaiting",
        Stage.NORMAL,
        emptyList(),
        "A media is paused but is expected to resume (e.g. buffering)",
        "事件在视频由于要播放下一帧而需要缓冲时触发。",
        "事件在視頻由于要播放下一幀而需要緩衝時觸發。"
    ),

    ONWHEEL(
        "onwheel",
        Stage.NORMAL,
        emptyList(),
        "The mouse wheel rolls up or down over an element",
        "该事件在鼠标滚轮在元素上下滚动时触发",
        "該事件在鼠標滚輪在元素上下滚動時觸發"
    ),
    ;

    companion object {
        val all
            get() = enumValues<Event>()
    }
}