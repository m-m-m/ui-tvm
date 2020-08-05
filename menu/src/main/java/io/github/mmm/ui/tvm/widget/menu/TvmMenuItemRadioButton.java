/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import io.github.mmm.ui.api.UiToggleGroup;
import io.github.mmm.ui.api.widget.composite.UiComposite;
import io.github.mmm.ui.api.widget.menu.UiMenuItemRadioButton;
import io.github.mmm.ui.tvm.TvmToggleGroup;
import io.github.mmm.ui.tvm.widget.input.TvmHtmlInput;

/**
 * Implementation of {@link UiMenuItemRadioButton} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmMenuItemRadioButton extends TvmAbstractInputMenuItem implements UiMenuItemRadioButton {

  private TvmToggleGroup toggleGroup;

  /**
   * The constructor.
   */
  public TvmMenuItemRadioButton() {

    super(TvmHtmlInput.TYPE_RADIO, "ui-radioitem");
  }

  @Override
  protected void setParent(UiComposite<?> parent) {

    super.setParent(parent);
    if (parent instanceof TvmMenu) {
      setToggleGroup(((TvmMenu) parent).getToggleGroup());
    }
  }

  @Override
  public UiToggleGroup getToggleGroup() {

    return this.toggleGroup;
  }

  @Override
  public void setToggleGroup(UiToggleGroup group) {

    this.toggleGroup = (TvmToggleGroup) group;
    this.widget.setName(this.toggleGroup.getGroup());
  }

}
