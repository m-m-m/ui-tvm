/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.window;

import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.Event;
import org.teavm.jso.dom.events.EventListener;
import org.teavm.jso.dom.events.KeyboardEvent;
import org.teavm.jso.dom.events.MouseEvent;
import org.teavm.jso.dom.html.HTMLBodyElement;
import org.teavm.jso.dom.html.HTMLButtonElement;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.base.placement.Direction;
import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.attribute.AttributeWriteMaximized;
import io.github.mmm.ui.attribute.AttributeWritePositionRange;
import io.github.mmm.ui.attribute.AttributeWriteSizeRange;
import io.github.mmm.ui.datatype.UiSize;
import io.github.mmm.ui.datatype.UiWindowSizing;
import io.github.mmm.ui.tvm.widget.TvmAbstractWindow;
import io.github.mmm.ui.widget.window.UiChildWindow;

/**
 * Abstract base implementation of {@link TvmAbstractWindow} for windows inside the browser content (body).
 *
 * @since 1.0.0
 */
public abstract class TvmChildWindow extends TvmAbstractWindow<HTMLElement>
    implements UiChildWindow, AttributeWriteMaximized, AttributeWritePositionRange, AttributeWriteSizeRange {

  private static final String STYLE_RESIZABLE = "resizable";

  private static final String STYLE_MOVABLE = "movable";

  private static EventListener<KeyboardEvent> keyboardListener;

  /** The browser {@link Window} owning this child window. */
  protected final Window window;

  /** The HTML body element of the {@link Window}. */
  protected final HTMLBodyElement body;

  private final HTMLElement headerElement;

  private final HTMLElement titleElement;

  private final HTMLElement controlsElement;

  private final HTMLButtonElement minimizeButton;

  private final HTMLButtonElement maximizeButton;

  private final HTMLButtonElement normalizeButton;

  private final HTMLButtonElement closeButton;

  /** Content pane with all content of the window except the header. */
  protected final HTMLElement contentPane;

  private final HTMLElement borders;

  private boolean visible;

  private double x;

  private double y;

  private double minX;

  private double minY;

  private double maxX;

  private double maxY;

  /** The z-index. */
  protected int z;

  private double width;

  private double height;

  private double minWidth;

  private double minHeight;

  private double maxWidth;

  private double maxHeight;

  private UiWindowSizing sizing;

  private boolean resizable;

  private boolean movable;

  private boolean closable;

  private String title;

  private int clientX;

  private int clientY;

  private Direction direction;

  private EventListener<?> pointerMoveListener;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmChildWindow(UiContext context) {

    this(context, Window.current());
  }

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   * @param window the browser {@link Window}.
   */
  public TvmChildWindow(UiContext context, Window window) {

    super(context, newElement("ui-window"));
    this.window = window;
    this.body = window.getDocument().getBody();

    if (keyboardListener == null) {
      keyboardListener = e -> {
        if (e.getKeyCode() == 27) {
          onEscapeKey();
        }
      };
      window.getDocument().addEventListener(EVENT_TYPE_KEYDOWN, keyboardListener);
    }
    // header
    this.headerElement = newElement("ui-wheader");
    this.titleElement = newElement("ui-wtitle");
    this.titleElement.addEventListener(EVENT_TYPE_POINTERDOWN, e -> onPointerDown(e, null), true);
    this.controlsElement = newElement("ui-wcontrols");
    this.minimizeButton = newButton();
    this.minimizeButton.setClassName("minimize");
    this.minimizeButton.addEventListener(EVENT_TYPE_CLICK, this::onMinimize);
    this.controlsElement.appendChild(this.minimizeButton);
    this.normalizeButton = newButton();
    this.normalizeButton.setClassName("normalize");
    this.normalizeButton.setHidden(true);
    this.normalizeButton.addEventListener(EVENT_TYPE_CLICK, this::onNormalize);
    this.controlsElement.appendChild(this.normalizeButton);
    this.maximizeButton = newButton();
    this.maximizeButton.setClassName("maximize");
    this.maximizeButton.addEventListener(EVENT_TYPE_CLICK, this::onMaximize);
    this.controlsElement.appendChild(this.maximizeButton);
    this.closeButton = newButton();
    this.closeButton.setClassName("close");
    this.closeButton.addEventListener(EVENT_TYPE_CLICK, this::onClose);
    this.controlsElement.appendChild(this.closeButton);

    this.headerElement.appendChild(this.titleElement);
    this.headerElement.appendChild(this.controlsElement);
    this.widget.appendChild(this.headerElement);

    // content
    this.contentPane = newElement("ui-wcontent");
    this.contentPane.appendChild(this.content.getTopWidget());
    this.borders = newElement("ui-wborders");
    for (Direction dir : Direction.values()) {
      HTMLElement border = createBorder(dir);
      this.borders.appendChild(border);
    }
    this.contentPane.appendChild(this.borders);
    this.widget.appendChild(this.contentPane);

    this.window.getDocument().addEventListener(EVENT_TYPE_POINTERUP, this::onPointerUp);
    this.pointerMoveListener = this::onPointerMove;
    this.widget.addEventListener(EVENT_TYPE_CLICK, this::onClick);
    this.x = -1;
    this.y = -1;
    this.maxX = Integer.MAX_VALUE;
    this.maxY = Integer.MAX_VALUE;
    this.minWidth = 200;
    this.maxWidth = Integer.MAX_VALUE;
    this.minHeight = 200;
    this.maxHeight = Integer.MAX_VALUE;
    setResizable(true);
    setMovable(true);
    this.closable = true;
    this.sizing = UiWindowSizing.NORMAL;
    this.width = window.getInnerWidth() / 2;
    this.height = window.getInnerHeight() / 2;
  }

  private static void onEscapeKey() {

    if (TvmPopup.TOPMOST_POPUP != null) {
      if (TvmPopup.TOPMOST_POPUP.isClosable()) {
        TvmPopup.TOPMOST_POPUP.close();
      }
    } else if (TvmWindow.TOPMOST_WINDOW != null) {
      if (TvmWindow.TOPMOST_WINDOW.isClosable()) {
        TvmWindow.TOPMOST_WINDOW.close();
      }
    }
  }

  private HTMLElement createBorder(Direction dir) {

    HTMLElement border = newElement("ui-wborder");
    border.setClassName(dir.getValue());
    border.addEventListener(EVENT_TYPE_POINTERDOWN, e -> onPointerDown(e, dir), true);
    return border;
  }

  @Override
  public HTMLElement getElement() {

    return this.widget;
  }

  @Override
  public boolean isResizable() {

    return this.resizable;
  }

  @Override
  public void setResizable(boolean resizable) {

    if (this.resizable == resizable) {
      return;
    }
    if (resizable) {
      getStyles().add(STYLE_RESIZABLE);
    } else {
      getStyles().remove(STYLE_RESIZABLE);
    }
    this.borders.setHidden(!resizable);
    this.resizable = resizable;
  }

  @Override
  public boolean isMovable() {

    return this.movable;
  }

  @Override
  public void setMovable(boolean movable) {

    if (this.movable == movable) {
      return;
    }
    if (movable) {
      getStyles().add(STYLE_MOVABLE);
    } else {
      getStyles().remove(STYLE_MOVABLE);
    }
    this.movable = movable;
  }

  @Override
  public String getTitle() {

    return this.title;
  }

  @Override
  public void setTitle(String title) {

    setTextContent(this.titleElement, title);
    this.title = title;
  }

  @Override
  public boolean isClosable() {

    return this.closable;
  }

  @Override
  public void setClosable(boolean closable) {

    if (this.closable == closable) {
      return;
    }
    this.closeButton.setHidden(!closable);
    this.closable = closable;
  }

  @Override
  public double getWidthInPixel() {

    return this.width;
  }

  @Override
  public void setWidth(UiSize width) {

    this.width = UiSize.getSafe(width).toPixel(Window.current().getInnerWidth());
    updateStyle();
  }

  @Override
  public double getHeightInPixel() {

    return this.height;
  }

  @Override
  public void setHeight(UiSize height) {

    this.height = UiSize.getSafe(height).toPixel(Window.current().getInnerHeight());
    updateStyle();
  }

  @Override
  public void setSize(UiSize width, UiSize height) {

    this.width = UiSize.getSafe(width).toPixel(Window.current().getInnerWidth());
    this.height = UiSize.getSafe(height).toPixel(Window.current().getInnerHeight());
    updateStyle();
  }

  @Override
  public double getMinWidth() {

    return this.minWidth;
  }

  @Override
  public void setMinWidth(double minWidth) {

    this.minWidth = clipSize(minWidth);
  }

  @Override
  public double getMaxWidth() {

    return this.maxWidth;
  }

  @Override
  public void setMaxWidth(double maxWidth) {

    this.maxWidth = clipSize(maxWidth);
  }

  @Override
  public double getMinHeight() {

    return this.minHeight;
  }

  @Override
  public void setMinHeight(double minHeight) {

    this.minHeight = clipSize(minHeight);
  }

  @Override
  public double getMaxHeight() {

    return this.maxHeight;
  }

  @Override
  public void setMaxHeight(double maxHeight) {

    this.maxHeight = clipSize(maxHeight);
  }

  @Override
  public double getX() {

    return this.x;
  }

  @Override
  public void setX(double x) {

    this.x = x;
    updateStyle();
  }

  @Override
  public double getY() {

    return this.y;
  }

  @Override
  public void setY(double y) {

    this.y = y;
    updateStyle();
  }

  @Override
  public void setPosition(double x, double y) {

    this.x = x;
    this.y = y;
    updateStyle();
  }

  @Override
  public double getMinX() {

    return this.minX;
  }

  @Override
  public void setMinX(double minX) {

    this.minX = clipZero(minX);
  }

  @Override
  public double getMaxX() {

    return this.maxX;
  }

  @Override
  public void setMaxX(double maxX) {

    this.maxX = clipMax(maxX);
  }

  @Override
  public double getMinY() {

    return this.minY;
  }

  @Override
  public void setMinY(double minY) {

    this.minY = clipZero(minY);
  }

  @Override
  public double getMaxY() {

    return this.maxY;
  }

  @Override
  public void setMaxY(double maxY) {

    this.maxY = clipMax(maxY);
  }

  @Override
  public boolean isMaximized() {

    return (this.sizing == UiWindowSizing.MAXIMIZED);
  }

  @Override
  public void setMaximized(boolean maximized) {

    if (maximized) {
      if (this.sizing == UiWindowSizing.MINIMIZED) {
        this.minimizeButton.setHidden(false);
      }
      this.sizing = UiWindowSizing.MAXIMIZED;
      this.maximizeButton.setHidden(true);
      this.normalizeButton.setHidden(false);
      updateStyle();
    } else if (this.sizing == UiWindowSizing.MAXIMIZED) {
      this.sizing = UiWindowSizing.NORMAL;
      this.maximizeButton.setHidden(false);
      this.normalizeButton.setHidden(true);
      updateStyle();
    }
  }

  @Override
  public boolean isMinimized() {

    return (this.sizing == UiWindowSizing.MINIMIZED);
  }

  @Override
  public void setMinimized(boolean minimized) {

    if (minimized) {
      if (this.sizing == UiWindowSizing.MAXIMIZED) {
        this.maximizeButton.setHidden(false);
      }
      this.sizing = UiWindowSizing.MINIMIZED;
      this.content.setVisible(false);
      this.minimizeButton.setHidden(true);
      this.normalizeButton.setHidden(false);
      this.contentPane.setHidden(true);
      updateStyle();
    } else if (this.sizing == UiWindowSizing.MINIMIZED) {
      this.sizing = UiWindowSizing.NORMAL;
      this.content.setVisible(true);
      this.minimizeButton.setHidden(false);
      this.normalizeButton.setHidden(true);
      this.contentPane.setHidden(false);
      updateStyle();
    }
  }

  @Override
  protected final void setVisibleNative(boolean visible) {

    if (this.visible == visible) {
      return;
    }
    if (visible) {
      doOpen();
    } else {
      doClose();
    }
    this.visible = visible;
  }

  /**
   * Called from {@link #setVisibleNative(boolean)} to natively {@link #open() open} this window. Will be overridden to
   * be fully functional.
   */
  protected void doOpen() {

    updateStyle();
    this.body.appendChild(this.widget);
  }

  /**
   * Called from {@link #setVisibleNative(boolean)} to natively {@link #close() close} this window. Will be overridden
   * to be fully functional.
   */
  protected void doClose() {

    this.body.removeChild(this.widget);
    setParent(null);
  }

  /**
   * Updates the style attribute with position, size, z-index, etc.
   */
  protected void updateStyle() {

    StringBuilder sb = new StringBuilder();
    if (this.sizing == UiWindowSizing.MAXIMIZED) {
      sb.append("left:0;top:0;width:100%;height:100%;");
    } else {
      sb.append("left:");
      if (this.x == -1) {
        this.x = (Window.current().getInnerWidth() - this.width) / 2.0;
      }
      if (this.x < 0) {
        this.x = 0;
      }
      sb.append(this.x);
      sb.append("px;top:");
      if (this.y == -1) {
        this.y = (Window.current().getInnerHeight() - this.height) / 2.0;
      }
      if (this.y < 0) {
        this.y = 0;
      }
      sb.append(this.y);
      sb.append("px;width:");
      sb.append(this.width);
      sb.append("px;");
      if (this.sizing == UiWindowSizing.NORMAL) {
        sb.append("height:");
        sb.append(this.height);
        sb.append("px;");
      }
    }
    sb.append("z-index:");
    sb.append(this.z);
    String style = sb.toString();
    this.widget.setAttribute(ATR_STYLE, style);
  }

  /**
   * @param e {@link Event} send when the minimize button is clicked.
   */
  protected void onMinimize(Event e) {

    setMinimized(true);
  }

  /**
   * @param e {@link Event} send when the maximize button is clicked.
   */
  protected void onMaximize(Event e) {

    setMaximized(true);
  }

  /**
   * @param e {@link Event} send when the normalize button is clicked.
   */
  protected void onNormalize(Event e) {

    if (this.sizing == UiWindowSizing.MINIMIZED) {
      setMinimized(false);
    } else if (this.sizing == UiWindowSizing.MAXIMIZED) {
      setMaximized(false);
    }
  }

  /**
   * @param e {@link Event} send when the close button is clicked.
   */
  protected void onClose(Event e) {

    close();
  }

  private void onPointerUp(Event e) {

    this.window.getDocument().removeEventListener(EVENT_TYPE_POINTERMOVE, this.pointerMoveListener);
  }

  private void onPointerMove(Event e) {

    MouseEvent me = e.cast();
    int oldX = this.clientX;
    this.clientX = me.getClientX();
    int movementX = this.clientX - oldX;
    int oldY = this.clientY;
    this.clientY = me.getClientY();
    int movementY = this.clientY - oldY;
    if ((movementX == 0) && (movementY == 0)) {
      return;
    }
    if (this.direction == null) {
      double max = this.window.getInnerWidth() - 20;
      if (max > this.maxX) {
        max = this.maxX;
      }
      this.x = clip(this.x + movementX, this.minX, max);
      max = this.window.getInnerHeight() - 20;
      if (max > this.maxY) {
        max = this.maxY;
      }
      this.y = clip(this.y + movementY, this.minY, max);
    } else {
      if (this.direction.isLeft()) {
        double newX = clip(this.x + movementX, this.minX, this.maxX);
        this.width = this.width - (newX - this.x);
        this.x = newX;
      } else if (this.direction.isRight()) {
        double max = this.window.getInnerWidth() - this.x;
        if (max > this.maxWidth) {
          max = this.maxWidth;
        }
        this.width = clip(this.width + movementX, this.minWidth, max);
      }
      if (this.direction.isUp()) {
        double newY = clip(this.y + movementY, this.minY, this.maxY);
        this.height = this.height - (newY - this.y);
        this.y = newY;
      } else if (this.direction.isDown()) {
        double max = this.window.getInnerHeight() - this.y;
        if (max > this.maxHeight) {
          max = this.maxHeight;
        }
        this.height = clip(this.height + movementY, this.minHeight, max);
      }

    }
    updateStyle();
  }

  /**
   * @param e {@link Event} send when the close button is clicked.
   * @param dir the {@link Direction} to resize or {@code null} for move.
   */
  protected void onPointerDown(Event e, Direction dir) {

    if ((dir == null) && !this.movable) {
      return;
    }
    if ((dir != null) && !this.resizable) {
      return;
    }
    e.preventDefault();
    MouseEvent me = e.cast();
    this.clientX = me.getClientX();
    this.clientY = me.getClientY();
    this.direction = dir;
    this.window.getDocument().addEventListener(EVENT_TYPE_POINTERMOVE, this.pointerMoveListener);
  }

  private static double clipSize(double size) {

    if (size < 100) {
      return 100;
    }
    return size;
  }

  private static double clipZero(double pos) {

    if (pos < 0) {
      return 0;
    }
    return pos;
  }

  private static double clipMax(double max) {

    if ((max < 0) || (max > Integer.MAX_VALUE)) {
      return Integer.MAX_VALUE;
    }
    return max;
  }

  private static double clip(double d, double min, double max) {

    if (d > max) {
      return max;
    }
    if (d < min) {
      return min;
    }
    return d;
  }
}
