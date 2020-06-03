/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu.navigationbar;

import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.ui.api.widget.menu.UiMenuBar;
import io.github.mmm.ui.api.widget.menu.UiNavigationBar;

/**
 * Implementation of {@link UiMenuBar} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmNavigationBar extends TvmAbstractMenuWithItems<HTMLElement> implements UiNavigationBar {

  /**
   * The constructor.
   */
  public TvmNavigationBar() {

    super(newNav());
    getStyles().add(STYLE);
  }

}
