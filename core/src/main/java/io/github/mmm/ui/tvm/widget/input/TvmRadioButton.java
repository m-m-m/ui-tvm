/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import io.github.mmm.ui.api.UiToggleGroup;
import io.github.mmm.ui.api.widget.input.UiRadioButton;

/**
 * Implementation of {@link UiRadioButton} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmRadioButton extends TvmBooleanInput implements UiRadioButton {

  static final String TAG_UI_RADIO = "ui-radio";

  /**
   * The constructor.
   */
  public TvmRadioButton() {

    super(TYPE_RADIO, TAG_UI_RADIO);
  }

  @Override
  public void setToggleGroup(UiToggleGroup group) {

    this.widget.setName(group.toString());
  }

}
