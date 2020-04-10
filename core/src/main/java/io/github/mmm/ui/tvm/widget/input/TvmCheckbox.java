/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import io.github.mmm.ui.api.widget.input.UiCheckbox;

/**
 * Implementation of {@link UiCheckbox} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmCheckbox extends TvmBooleanInput implements UiCheckbox {

  /**
   * The constructor.
   */
  public TvmCheckbox() {

    super(TYPE_CHECKBOX, "ui-checkbox");
  }

}
