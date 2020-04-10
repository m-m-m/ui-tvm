/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.menu;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.menu.UiMenu;
import io.github.mmm.ui.tvm.widget.menu.TvmMenu;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiMenu}.
 *
 * @since 1.0.0
 */
public class TvmFactoryMenu implements UiSingleWidgetFactoryNative<UiMenu> {

  @Override
  public Class<UiMenu> getType() {

    return UiMenu.class;
  }

  @Override
  public UiMenu create() {

    return new TvmMenu();
  }

}
