/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.img;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;
import io.github.mmm.ui.widget.img.UiIcon;

/**
 * Implementation of {@link UiIcon} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmIcon extends TvmWidgetHtmlElement<HTMLElement> implements UiIcon {

  private String iconId;

  private String altText;

  private double size;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmIcon(UiContext context) {

    super(context, newIcon(null));
    this.size = 1;
  }

  @Override
  public String getIconId() {

    return this.iconId;
  }

  @Override
  public void setIconId(String iconId) {

    if (this.iconId != null) {
      if (this.iconId.equals(iconId)) {
        return;
      }
      getStyles().remove(this.iconId);
    }
    getStyles().add(iconId);
    this.iconId = iconId;
  }

  @Override
  public String getAltText() {

    return this.altText;
  }

  @Override
  public void setAltText(String altText) {

    boolean hasAltText = isEmpty(altText);
    if (hasAltText) {
      this.widget.removeAttribute(ATR_ARIA_LABEL);
    } else {
      this.widget.setAttribute(ATR_ARIA_LABEL, altText);
    }
    this.widget.setAttribute(ATR_ARIA_HIDDEN, Boolean.toString(!hasAltText));
    this.altText = altText;
  }

  @Override
  public double getSize() {

    return this.size;
  }

  @Override
  public void setSize(double size) {

    this.size = size;
    updateSize();
  }

  private void updateSize() {

    String style = "font-size:" + this.size + "em;";
    this.widget.setAttribute(ATR_STYLE, style);
  }

}
