/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.link;

import io.github.mmm.ui.api.widget.link.UiExternalLink;

/**
 * Implementation of {@link UiExternalLink} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmExternalLink extends TvmAbstractLink implements UiExternalLink {

  private String url;

  /**
   * The constructor.
   */
  public TvmExternalLink() {

    super();
    this.widget.setTarget("_blank");
  }

  @Override
  public String getUrl() {

    return this.url;
  }

  @Override
  public void setUrl(String url) {

    assert (isAbsolute(url));
    this.widget.setHref(url);
    this.url = url;
  }

  private boolean isAbsolute(String uri) {

    return uri.startsWith("https://") || uri.startsWith("http://") || uri.startsWith("file:") || uri.startsWith("ftp:");
  }

}
