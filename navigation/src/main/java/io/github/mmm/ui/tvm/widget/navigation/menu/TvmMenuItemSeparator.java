/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.navigation.menu;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.navigation.UiMenuItem;
import io.github.mmm.ui.api.widget.navigation.UiMenuItemSeparator;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;

/**
 * Implementation of {@link UiMenuItem} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenuItemSeparator extends TvmWidgetHtmlElement<HTMLElement> implements UiMenuItemSeparator {

  /**
   * The constructor.
   */
  public TvmMenuItemSeparator() {

    super(newHr());
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    // nothing to do...
  }

}
