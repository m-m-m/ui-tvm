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
import io.github.mmm.ui.api.attribute.AttributeWritePositionRange;
import io.github.mmm.ui.api.attribute.AttributeWriteSizeRange;
import io.github.mmm.ui.api.datatype.UiWindowSizing;
import io.github.mmm.ui.api.widget.window.UiChildWindow;
import io.github.mmm.ui.spi.window.UiWindowPositionAndSize;

/**
 * Abstract base implementation of {@link TvmAbstractWindow} for windows inside the browser content (body).
 *
 * @since 1.0.0
 */
public abstract class TvmChildWindow extends TvmAbstractWindow<HTMLElement> implements UiChildWindow {

  private static final String STYLE_RESIZABLE = "resizable";

  private static final String STYLE_MOVABLE = "movable";

  private static EventListener<KeyboardEvent> keyboardListener;

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

  private final TvmWindowPositionAndSize positionAndSize;

  private String title;

  /** The z-index. */
  protected int z;

  private UiWindowSizing sizing;

  private boolean resizable;

  private boolean movable;

  private boolean closable;

  /**
   * The constructor.
   */
  public TvmChildWindow() {

    this(Window.current());
  }

  /**
   * The constructor.
   *
   * @param window the browser {@link Window}.
   */
  public TvmChildWindow(Window window) {

    super(newElement("ui-window"));
    this.positionAndSize = new TvmWindowPositionAndSize(window);
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
    this.titleElement.addEventListener(EVENT_TYPE_POINTERDOWN, e -> this.positionAndSize.onPointerDown(e, null), true);
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
    this.widget.addEventListener(EVENT_TYPE_CLICK, this::onClick);
    this.sizing = UiWindowSizing.NORMAL;
    setResizable(true);
    setMovable(true);
    setClosable(true);
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
    border.addEventListener(EVENT_TYPE_POINTERDOWN, e -> this.positionAndSize.onPointerDown(e, dir), true);
    return border;
  }

  @Override
  public HTMLElement getElement() {

    return this.widget;
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
  public AttributeWritePositionRange getPosition() {

    return this.positionAndSize;
  }

  @Override
  public AttributeWriteSizeRange getSize() {

    return this.positionAndSize;
  }

  @Override
  public boolean isResizable() {

    return this.resizable;
  }

  @Override
  public void setResizable(boolean resizable) {

    if (resizable == this.resizable) {
      return;
    }
    if (resizable) {
      getStyles().add(STYLE_RESIZABLE);
    } else {
      getStyles().remove(STYLE_RESIZABLE);
    }
    TvmChildWindow.this.borders.setHidden(!resizable);
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
  public boolean isClosable() {

    return this.closable;
  }

  @Override
  public void setClosable(boolean closable) {

    if (closable == this.closable) {
      return;
    }
    TvmChildWindow.this.closeButton.setHidden(!closable);
    this.closable = closable;
  }

  @Override
  public boolean isMaximized() {

    return (this.sizing == UiWindowSizing.MAXIMIZED);
  }

  @Override
  public void setMaximized(boolean maximized) {

    if (maximized) {
      if (this.sizing == UiWindowSizing.MAXIMIZED) {
        return;
      }
      setSizing(UiWindowSizing.MAXIMIZED);
    } else {
      if (this.sizing != UiWindowSizing.MAXIMIZED) {
        return;
      }
      setSizing(UiWindowSizing.NORMAL);
    }
  }

  @Override
  public boolean isMinimized() {

    return (this.sizing == UiWindowSizing.MINIMIZED);
  }

  @Override
  public void setMinimized(boolean minimized) {

    if (minimized) {
      if (this.sizing == UiWindowSizing.MINIMIZED) {
        return;
      }
      setSizing(UiWindowSizing.MINIMIZED);
    } else {
      if (this.sizing != UiWindowSizing.MINIMIZED) {
        return;
      }
      setSizing(UiWindowSizing.NORMAL);
    }
  }

  private void setSizing(UiWindowSizing sizing) {

    TvmChildWindow.this.contentPane.setHidden(sizing == UiWindowSizing.MINIMIZED);
    TvmChildWindow.this.minimizeButton.setHidden(sizing == UiWindowSizing.MINIMIZED);
    TvmChildWindow.this.maximizeButton.setHidden(sizing == UiWindowSizing.MAXIMIZED);
    TvmChildWindow.this.normalizeButton.setHidden(sizing == UiWindowSizing.NORMAL);
    this.sizing = sizing;
    updateStyle();
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

    this.positionAndSize.centerOnScreen(false);
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
   * Bring this window to the front unless it is a Popup.
   */
  public void bringToFront() {

  }

  @Override
  public void centerOnScreen() {

    this.positionAndSize.centerOnScreen(true);
  }

  /**
   * @param e {@link Event} send when the close button is clicked.
   */
  protected void onClose(Event e) {

    close();
  }

  private void onNormalize(Event e) {

    if (TvmChildWindow.this.sizing == UiWindowSizing.MINIMIZED) {
      setMinimized(false);
    } else if (TvmChildWindow.this.sizing == UiWindowSizing.MAXIMIZED) {
      setMaximized(false);
    }
  }

  private void onMinimize(Event e) {

    setMinimized(true);
  }

  private void onMaximize(Event e) {

    setMaximized(true);
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

  /**
   * Updates the style attribute with position, size, z-index, etc.
   */
  protected void updateStyle() {

    this.positionAndSize.updateStyle();
  }

  private class TvmWindowPositionAndSize extends UiWindowPositionAndSize {

    private final Window window;

    private int clientX;

    private int clientY;

    private Direction direction;

    private EventListener<?> pointerMoveListener;

    /**
     * The constructor.
     *
     * @param window the owning browser {@link Window}.
     */
    public TvmWindowPositionAndSize(Window window) {

      super();
      this.window = window;
      window.getDocument().addEventListener(EVENT_TYPE_POINTERUP, this::onPointerUp);
      this.pointerMoveListener = this::onPointerMove;
    }

    @Override
    public void setX(double x) {

      super.setX(x);
      updateStyle();
    }

    @Override
    public void setY(double y) {

      super.setY(y);
      updateStyle();
    }

    @Override
    public void setPosition(double x, double y) {

      super.setPosition(x, y);
      updateStyle();
    }

    @Override
    public void setWidthInPixel(double width) {

      super.setWidthInPixel(width);
      updateStyle();
    }

    @Override
    public void setHeightInPixel(double height) {

      super.setHeightInPixel(height);
      updateStyle();
    }

    @Override
    public void setSizeInPixel(double width, double height) {

      super.setSizeInPixel(width, height);
      updateStyle();
    }

    @Override
    protected double getScreenWidth() {

      return this.window.getInnerWidth();
    }

    @Override
    protected double getScreenHeight() {

      return this.window.getInnerHeight();
    }

    /**
     * @param e {@link Event} send when the close button is clicked.
     * @param dir the {@link Direction} to resize or {@code null} for move.
     */
    protected void onPointerDown(Event e, Direction dir) {

      bringToFront();
      if ((dir == null) && !TvmChildWindow.this.movable) {
        return;
      }
      if ((dir != null) && !TvmChildWindow.this.resizable) {
        return;
      }
      e.preventDefault();
      MouseEvent me = e.cast();
      this.clientX = me.getClientX();
      this.clientY = me.getClientY();
      this.direction = dir;
      this.window.getDocument().addEventListener(EVENT_TYPE_POINTERMOVE, this.pointerMoveListener);
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
     * Updates the style attribute with position, size, z-index, etc.
     */
    protected void updateStyle() {

      StringBuilder sb = new StringBuilder();
      if (TvmChildWindow.this.sizing == UiWindowSizing.MAXIMIZED) {
        sb.append("left:0;top:0;width:100%;height:100%;");
      } else {
        sb.append("left:");
        sb.append(clipZero(this.x));
        sb.append("px;top:");
        sb.append(clipZero(this.y));
        sb.append("px;width:");
        sb.append(this.width);
        sb.append("px;");
        if (TvmChildWindow.this.sizing == UiWindowSizing.NORMAL) {
          sb.append("height:");
          sb.append(this.height);
          sb.append("px;");
        }
      }
      sb.append("z-index:");
      sb.append(TvmChildWindow.this.z);
      String style = sb.toString();
      TvmChildWindow.this.widget.setAttribute(ATR_STYLE, style);
    }

  }

}
