/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import io.github.mmm.ui.api.UiToggleGroup;
import io.github.mmm.ui.api.event.UiClickEvent;
import io.github.mmm.ui.api.widget.input.UiRadioButton;
import io.github.mmm.ui.tvm.TvmToggleGroup;

/**
 * Implementation of {@link UiRadioButton} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmRadioButton extends TvmBooleanInput implements UiRadioButton {

  static final String TAG_UI_RADIO = "ui-radio";

  private TvmToggleGroup toggleGroup;

  /**
   * The constructor.
   */
  public TvmRadioButton() {

    super(TYPE_RADIO, TAG_UI_RADIO);
  }

  @Override
  public TvmToggleGroup getToggleGroup() {

    return this.toggleGroup;
  }

  @Override
  public void setToggleGroup(UiToggleGroup group) {

    this.toggleGroup = (TvmToggleGroup) group;
    this.widget.setName(this.toggleGroup.getGroup());
  }

  @Override
  public void click() {

    setValueForUser(Boolean.TRUE);
    fireEvent(new UiClickEvent(this, true));
  }

}
