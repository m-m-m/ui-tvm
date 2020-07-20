/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.navigation.menu;

import io.github.mmm.ui.api.widget.navigation.UiMenuItemCheckbox;
import io.github.mmm.ui.tvm.widget.input.TvmHtmlInput;

/**
 * Implementation of {@link UiMenuItemCheckbox} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenuItemCheckbox extends TvmAbstractInputMenuItem implements UiMenuItemCheckbox {

  /**
   * The constructor.
   */
  public TvmMenuItemCheckbox() {

    super(TvmHtmlInput.TYPE_CHECKBOX, "ui-checkitem");
  }

}
