/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.img;

import org.teavm.jso.dom.html.HTMLImageElement;

import io.github.mmm.ui.api.datatype.UiSize;
import io.github.mmm.ui.api.widget.img.UiImage;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;

/**
 * Implementation of {@link UiImage} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmImage extends TvmWidgetHtmlElement<HTMLImageElement> implements UiImage {

  private String url;

  private String altText;

  private UiSize width;

  private UiSize height;

  /**
   * The constructor.
   */
  public TvmImage() {

    super(newImage());
    this.width = UiSize.AUTO;
    this.height = UiSize.AUTO;
  }

  @Override
  public String getUrl() {

    return this.url;
  }

  @Override
  public void setUrl(String url) {

    this.widget.setSrc(url);
    this.url = url;
  }

  @Override
  public String getAltText() {

    return this.altText;
  }

  @Override
  public void setAltText(String altText) {

    this.widget.setAlt(altText);
    this.altText = altText;
  }

  @Override
  public UiSize getWidth() {

    return this.width;
  }

  @Override
  public double getWidthInPixel() {

    return this.widget.getWidth();
  }

  @Override
  public void setWidth(UiSize width) {

    width = UiSize.getSafe(width);
    this.width = width;
    updateSize();
  }

  @Override
  public UiSize getHeight() {

    return this.height;
  }

  @Override
  public double getHeightInPixel() {

    return this.widget.getHeight();
  }

  @Override
  public void setHeight(UiSize height) {

    height = UiSize.getSafe(height);
    this.height = height;
    updateSize();
  }

  private void updateSize() {

    StringBuilder sb = new StringBuilder();
    if (!this.width.isAuto()) {
      sb.append("width:");
      sb.append(this.width);
      sb.append(';');
    }
    if (!this.height.isAuto()) {
      sb.append("height:");
      sb.append(this.height);
      sb.append(';');
    }
    String style = sb.toString();
    this.widget.setAttribute(ATR_STYLE, style);
  }

}
