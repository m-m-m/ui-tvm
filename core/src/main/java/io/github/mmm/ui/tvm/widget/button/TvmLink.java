/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.button;

import org.teavm.jso.dom.html.HTMLAnchorElement;

import io.github.mmm.ui.api.widget.button.UiLink;

/**
 * Implementation of {@link UiLink} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmLink extends TvmAbstractButton<HTMLAnchorElement> implements UiLink {

  private String href;

  /**
   * The constructor.
   */
  public TvmLink() {

    super(newAnchor());
  }

  @Override
  public String getHref() {

    return this.href;
  }

  @Override
  public void setHref(String href) {

    this.widget.setHref(href);
    this.href = href;
    if (isAbsolute(href)) {
      this.widget.setTarget("_blank");
    } else {
      this.widget.setTarget("_self");
    }
  }

  private boolean isAbsolute(String url) {

    return url.startsWith("https://") || url.startsWith("http://") || url.startsWith("file:") || url.startsWith("ftp:");
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    if (enabled) {
      getStyles().remove(STYLE_DISABLED);
    } else {
      getStyles().add(STYLE_DISABLED);
    }
  }

}
