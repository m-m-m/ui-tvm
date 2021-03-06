/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.panel.UiScrollPanel;
import io.github.mmm.ui.tvm.widget.panel.TvmScrollPanel;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiScrollPanel}.
 *
 * @since 1.0.0
 */
public class TvmFactoryScrollPanel implements UiSingleWidgetFactoryNative<UiScrollPanel> {

  @Override
  public Class<UiScrollPanel> getType() {

    return UiScrollPanel.class;
  }

  @Override
  public UiScrollPanel create() {

    return new TvmScrollPanel();
  }

}
