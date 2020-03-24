/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.widget.img.TvmIcon;
import io.github.mmm.ui.widget.img.UiIcon;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiIcon}.
 *
 * @since 1.0.0
 */
public class TvmFactoryIcon implements UiSingleWidgetFactoryNative<UiIcon> {

  @Override
  public Class<UiIcon> getType() {

    return UiIcon.class;
  }

  @Override
  public UiIcon create(UiContext context) {

    return new TvmIcon(context);
  }

}
