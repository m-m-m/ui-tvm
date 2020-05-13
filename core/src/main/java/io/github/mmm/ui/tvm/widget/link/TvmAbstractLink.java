/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.link;

import org.teavm.jso.dom.html.HTMLAnchorElement;

import io.github.mmm.ui.api.widget.link.UiAbstractLink;
import io.github.mmm.ui.tvm.widget.button.TvmAbstractButton;

/**
 * Implementation of {@link UiAbstractLink} for TeaVM.
 *
 * @since 1.0.0
 */
public abstract class TvmAbstractLink extends TvmAbstractButton<HTMLAnchorElement> implements UiAbstractLink {

  /**
   * The constructor.
   */
  public TvmAbstractLink() {

    super(newAnchor());
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
