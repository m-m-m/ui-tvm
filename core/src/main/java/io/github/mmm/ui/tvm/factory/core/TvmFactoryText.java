/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.widget.TvmText;
import io.github.mmm.ui.widget.UiText;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiText}.
 *
 * @since 1.0.0
 */
public class TvmFactoryText implements UiSingleWidgetFactoryNative<UiText> {

  @Override
  public Class<UiText> getType() {

    return UiText.class;
  }

  @Override
  public UiText create(UiContext context) {

    return new TvmText(context);
  }

}
