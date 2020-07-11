/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget;

import org.teavm.jso.JSObject;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.html.HTMLAnchorElement;
import org.teavm.jso.dom.html.HTMLAudioElement;
import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLElement;
import org.teavm.jso.dom.html.HTMLFormElement;
import org.teavm.jso.dom.html.HTMLImageElement;
import org.teavm.jso.dom.html.HTMLInputElement;
import org.teavm.jso.dom.html.HTMLOptionElement;
import org.teavm.jso.dom.html.HTMLSelectElement;
import org.teavm.jso.dom.html.HTMLTextAreaElement;
import org.teavm.jso.dom.html.HTMLVideoElement;
import org.teavm.jso.dom.xml.Document;
import org.teavm.jso.dom.xml.Node;

import io.github.mmm.ui.api.datatype.UiEnabledFlags;
import io.github.mmm.ui.api.datatype.UiVisibleFlags;
import io.github.mmm.ui.api.event.UiClickEvent;
import io.github.mmm.ui.api.widget.UiCustomWidget;
import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.spi.widget.AbstractUiNativeWidgetWrapper;

/**
 * Implementation of {@link io.github.mmm.ui.api.widget.UiNativeWidget} for TeaVM.
 *
 * @param <W> type of {@link #getWidget() TeaVM widget}.
 * @since 1.0.0
 */
public abstract class TvmWidget<W extends JSObject> extends AbstractUiNativeWidgetWrapper<W> {

  /** The owner {@link Document} of the HTML. */
  protected static final HTMLDocument DOC = Window.current().getDocument();

  /** {@link HTMLElement#getTagName() Tag name} of UI icon. */
  protected static final String TAG_NAME_UI_ICON = "ui-icon";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_ID = "id";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_STYLE = "style";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_ROLE = "role";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_ARIA_HIDDEN = "aria-hidden";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_ARIA_LABEL = "aria-label";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_ARIA_SELECTED = "aria-selected";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_TABINDEX = "tabindex";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_FOR = "for";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_LIST = "list";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_MIN = "min";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_MAX = "max";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_REQUIRED = "required";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_COLSPAN = "colspan";

  /** {@link HTMLElement#getAttribute(String) Attribute name} {@value}. */
  protected static final String ATR_ROWSPAN = "rowspan";

  /** {@link #ATR_ROLE Role} {@value}. */
  protected static final String ROLE_PRESENTATION = "presentation";

  /** {@link org.teavm.jso.dom.events.Event#getType() Event type} {@value}. */
  protected static final String EVENT_TYPE_BLUR = "blur";

  /** {@link org.teavm.jso.dom.events.Event#getType() Event type} {@value}. */
  protected static final String EVENT_TYPE_FOCUS = "focus";

  /** {@link org.teavm.jso.dom.events.Event#getType() Event type} {@value}. */
  protected static final String EVENT_TYPE_CLICK = "click";

  /** {@link org.teavm.jso.dom.events.Event#getType() Event type} {@value}. */
  protected static final String EVENT_TYPE_INPUT = "input";

  /** {@link org.teavm.jso.dom.events.Event#getType() Event type} {@value}. */
  protected static final String EVENT_TYPE_CHANGE = "change";

  /** {@link org.teavm.jso.dom.events.Event#getType() Event type} {@value}. */
  protected static final String EVENT_TYPE_KEYDOWN = "keydown";

  /** {@link org.teavm.jso.dom.events.Event#getType() Event type} {@value}. */
  protected static final String EVENT_TYPE_POINTERDOWN = "pointerdown";

  /** {@link org.teavm.jso.dom.events.Event#getType() Event type} {@value}. */
  protected static final String EVENT_TYPE_POINTERMOVE = "pointermove";

  /** {@link org.teavm.jso.dom.events.Event#getType() Event type} {@value}. */
  protected static final String EVENT_TYPE_POINTERUP = "pointerup";

  /** {@link HTMLElement#getClassName() CSS class name} for error icon. */
  protected static final String CLASS_ERROR = "error";

  /** {@link HTMLElement#getClassName() CSS class name} for warning icon. */
  protected static final String CLASS_WARNING = "warning";

  /** {@link HTMLElement#getClassName() CSS class name} for info icon. */
  protected static final String CLASS_INFO = "info";

  /** {@link HTMLElement#getClassName() CSS class name} for expand icon. */
  protected static final String CLASS_EXPAND = "expand";

  /** {@link HTMLElement#getClassName() CSS class name} for collapse icon. */
  protected static final String CLASS_COLLAPSE = "collapse";

  /** {@link HTMLElement#getClassName() CSS class name} for question icon. */
  protected static final String CLASS_QUESTION = "question";

  /** CSS style for a collapsed widget (hiding its children). */
  protected static final String STYLE_COLLAPSED = "collapsed";

  /** CSS style for a collapsible widget (can be collapsed/expanded by the end-user). */
  protected static final String STYLE_COLLAPSIBLE = "collapsible";

  /** CSS style for a disabled widget that does not support disabling natively (e.g. anchor). */
  protected static final String STYLE_DISABLED = "disabled";

  /** CSS style for a resizable widget. */
  protected static final String STYLE_RESIZABLE = "resizable";

  /** @see #getWidget() */
  protected W widget;

  /**
   * The constructor.
   *
   * @param widget the {@link #getWidget() TeaVM widget}.
   */
  public TvmWidget(W widget) {

    super();
    this.widget = widget;
  }

  @Override
  public JSObject getTopWidget() {

    return this.widget;
  }

  @Override
  public W getWidget() {

    return this.widget;
  }

  /**
   * @param widget the TeaVM {@link #getWidget() widget} to initialize.
   */
  protected void setWidget(W widget) {

    this.widget = widget;
    if (this.widget == null) {
      return;
    }
    if (!isVisible(UiVisibleFlags.ALL)) {
      setVisibleNative(false);
    }
    if (!isEnabled(UiEnabledFlags.ALL)) {
      setEnabledNative(false);
    }
    if (isReadOnly()) {
      setReadOnlyNative(true);
    }
    String tooltip = getTooltip();
    if (tooltip != null) {
      setTooltipNative(tooltip);
    }
    onStylesChanged(getStyles().get());
  }

  /**
   * @return the {@link HTMLElement} representing this widget.
   */
  public abstract HTMLElement getElement();

  @Override
  protected void setIdNative(String id) {

    getElement().setAttribute(ATR_ID, id);
  }

  @Override
  protected void setReadOnlyNative(boolean readOnly) {

    // nothing by default, override to change
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    // nothing by default, override to change
  }

  /**
   * @param event the click {@link Event}.
   */
  protected void onClick(Event event) {

    fireEvent(new UiClickEvent(this, false));
  }

  /**
   * @param node the {@link Node} to remove all children from.
   */
  protected static void removeAllChildren(Node node) {

    Node firstChild = node.getFirstChild();
    while (firstChild != null) {
      node.removeChild(firstChild);
      firstChild = node.getFirstChild();
    }
  }

  /**
   * @param uiWidget the {@link UiWidget}.
   * @return the {@link #getWidget() containing} JavaFx {@link Node}.
   */
  public static Node getTopNode(UiWidget uiWidget) {

    if (uiWidget instanceof UiCustomWidget) {
      return getTopNode(((UiCustomWidget<?>) uiWidget).getDelegate());
    } else if (uiWidget != null) {
      return ((TvmWidgetHtmlElement<?>) uiWidget).getTopWidget();
    }
    return null;
  }

  /**
   * @param parent the parent {@link Node}.
   * @param child the child {@link Node} to insert as first child.
   */
  protected static void insertFirst(Node parent, Node child) {

    Node firstChild = parent.getFirstChild();
    if (firstChild == null) {
      parent.appendChild(child);
    } else {
      parent.insertBefore(child, firstChild);
    }
  }

  /**
   * @param parent the parent {@link Node}.
   * @param child the child {@link Node} to insert as child at the given index. May be {@code -1} to
   *        {@link Node#appendChild(Node) insert at the end}.
   * @param index the position to insert the {@code child} at.
   */
  protected static void insertAt(Node parent, Node child, int index) {

    if (index == 0) {
      insertFirst(parent, child);
      return;
    } else if (index == -1) {
      parent.appendChild(child);
      return;
    }
    Node node = parent.getFirstChild();
    int i = 0;
    while ((node != null) && (i < index)) {
      node = node.getNextSibling();
      i++;
    }
    if (i < index) {
      throw new IndexOutOfBoundsException(Integer.toString(index));
    }
    if (node == null) {
      parent.appendChild(child);
    } else {
      parent.insertBefore(child, node);
    }
  }

  /**
   * @return a new {@link HTMLButtonElement}.
   */
  public static HTMLButtonElement newButton() {

    return DOC.createElement("button").cast();
  }

  /**
   * @return a new {@link HTMLAnchorElement}.
   */
  public static HTMLAnchorElement newAnchor() {

    return DOC.createElement("a").cast();
  }

  /**
   * @return a new {@link HTMLInputElement}.
   */
  public static HTMLInputElement newInput() {

    return DOC.createElement("input").cast();
  }

  /**
   * @param type the {@link HTMLInputElement#getType() input type}.
   * @return a new {@link HTMLInputElement}.
   */
  public static HTMLInputElement newInput(String type) {

    HTMLInputElement input = newInput();
    input.setType(type);
    return input;
  }

  /**
   * @return a new {@link HTMLElement output element}.
   */
  public static HTMLElement newOutput() {

    return DOC.createElement("output").cast();
  }

  /**
   * @return a new {@link HTMLTextAreaElement}.
   */
  public static HTMLTextAreaElement newTextArea() {

    return DOC.createElement("textarea").cast();
  }

  /**
   * @return a new {@link HTMLSelectElement}.
   */
  public static HTMLSelectElement newSelect() {

    return DOC.createElement("select").cast();
  }

  /**
   * @return a new {@link HTMLFormElement}.
   */
  public static HTMLFormElement newForm() {

    return DOC.createElement("form").cast();
  }

  /**
   * @return a new {@link HTMLImageElement}.
   */
  public static HTMLImageElement newImage() {

    return DOC.createElement("img").cast();
  }

  /**
   * @return a new {@link HTMLVideoElement}.
   */
  public static HTMLVideoElement newVideo() {

    return DOC.createElement("video").cast();
  }

  /**
   * @return a new {@link HTMLAudioElement}.
   */
  public static HTMLAudioElement newAudio() {

    return DOC.createElement("audio").cast();
  }

  /**
   * @return a new {@link HTMLCanvasElement}.
   */
  public static HTMLCanvasElement newCanvas() {

    return DOC.createElement("canvas").cast();
  }

  /**
   * @return a new {@link HTMLElement fieldset element}.
   */
  public static HTMLElement newFieldSet() {

    return DOC.createElement("fieldset").cast();
  }

  /**
   * @return a new {@link HTMLElement legend element}.
   */
  public static HTMLElement newLegend() {

    return DOC.createElement("legend").cast();
  }

  /**
   * @return a new {@link HTMLElement label element}.
   */
  public static HTMLElement newLabel() {

    return DOC.createElement("label").cast();
  }

  /**
   * @return a new {@link HTMLElement div element}.
   */
  public static HTMLElement newDiv() {

    return DOC.createElement("div").cast();
  }

  /**
   * @return a new {@link HTMLElement nav(igation) element}.
   */
  public static HTMLElement newNav() {

    return DOC.createElement("nav").cast();
  }

  /**
   * @return a new {@link HTMLElement unordered list element}.
   */
  public static HTMLElement newUl() {

    return DOC.createElement("ul").cast();
  }

  /**
   * @return a new {@link HTMLElement list item element}.
   */
  public static HTMLElement newLi() {

    return DOC.createElement("li").cast();
  }

  /**
   * @return a new {@link HTMLElement span element}.
   */
  public static HTMLElement newSpan() {

    return DOC.createElement("span").cast();
  }

  /**
   * @return a new {@link HTMLElement horizontal ruler}.
   */
  public static HTMLElement newHr() {

    return DOC.createElement("hr").cast();
  }

  /**
   * @return a new {@link HTMLElement datalist element}.
   */
  public static HTMLElement newDatalist() {

    return DOC.createElement("datalist").cast();
  }

  /**
   * @param document the {@link HTMLDocument}.
   * @return a new {@link HTMLOptionElement option}.
   */
  public static HTMLOptionElement newOption(HTMLDocument document) {

    if (document == null) {
      document = DOC;
    }
    return document.createElement("option").cast();
  }

  /**
   * @param parent the parent {@link HTMLElement} - typically a {@link #newDatalist() datalist}.
   * @param value the value of the option.
   * @return a new {@link HTMLOptionElement option}.
   */
  public static HTMLOptionElement newOption(HTMLElement parent, String value) {

    HTMLOptionElement option = newOption(parent.getOwnerDocument());
    option.setValue(value);
    parent.appendChild(option);
    return option;
  }

  /**
   * @return a new {@link HTMLElement table element}.
   */
  public static HTMLElement newTable() {

    return DOC.createElement("table").cast();
  }

  /**
   * @return a new {@link HTMLElement table header element}.
   */
  public static HTMLElement newTableHead() {

    return DOC.createElement("thead").cast();
  }

  /**
   * @return a new {@link HTMLElement table body element}.
   */
  public static HTMLElement newTableBody() {

    return DOC.createElement("tbody").cast();
  }

  /**
   * @return a new {@link HTMLElement table footer element}.
   */
  public static HTMLElement newTableFoot() {

    return DOC.createElement("tfoot").cast();
  }

  /**
   * @return a new {@link HTMLElement table row} (tr).
   */
  public static HTMLElement newTableRow() {

    return DOC.createElement("tr").cast();
  }

  /**
   * @return a new {@link HTMLElement table data cell} (td).
   */
  public static HTMLElement newTableDataCell() {

    return DOC.createElement("td").cast();
  }

  /**
   * @return a new {@link HTMLElement table header cell} (th).
   */
  public static HTMLElement newTableHeaderCell() {

    return DOC.createElement("th").cast();
  }

  /**
   * @return a new {@link HTMLElement header element}.
   */
  public static HTMLElement newHeader() {

    return DOC.createElement("header").cast();
  }

  /**
   * @return a new {@link HTMLElement footer element}.
   */
  public static HTMLElement newFooter() {

    return DOC.createElement("footer").cast();
  }

  /**
   * @return a new {@link HTMLElement section element}.
   */
  public static HTMLElement newSection() {

    return DOC.createElement("section").cast();
  }

  /**
   * @return a new {@link HTMLElement aside element}.
   */
  public static HTMLElement newASide() {

    return DOC.createElement("aside").cast();
  }

  /**
   * @return a new {@link HTMLElement main element}.
   */
  public static HTMLElement newMain() {

    return DOC.createElement("main").cast();
  }

  /**
   * @param tag the {@link HTMLElement#getTagName() tag name}.
   * @return a new {@link HTMLElement div element}.
   */
  public static HTMLElement newElement(String tag) {

    return DOC.createElement(tag).cast();
  }

  /**
   * @param name the {@link HTMLElement#getClassName() CSS class name} of the icon.
   * @return a new {@link HTMLElement}.
   */
  public static HTMLElement newIcon(String name) {

    HTMLElement element = newElement(TAG_NAME_UI_ICON);
    if (name != null) {
      element.setClassName(name);
    }
    element.setAttribute(ATR_ARIA_HIDDEN, "true");
    return element;
  }

}
