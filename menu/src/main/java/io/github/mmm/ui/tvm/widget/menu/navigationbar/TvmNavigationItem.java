/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu.navigationbar;

import io.github.mmm.ui.api.widget.menu.UiMenuItem;
import io.github.mmm.ui.tvm.widget.link.TvmAbstractLink;

/**
 *
 */
public class TvmNavigationItem extends TvmAbstractLink implements UiMenuItem {

  /**
   * The constructor.
   */
  public TvmNavigationItem() {

    super();
    getStyles().add(STYLE_NAV);
  }

}
