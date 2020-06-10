/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.panel.UiBorderPanel;
import io.github.mmm.ui.tvm.widget.panel.TvmBorderPanel;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiBorderPanel}.
 *
 * @since 1.0.0
 */
public class TvmFactoryBorderPanel implements UiSingleWidgetFactoryNative<UiBorderPanel> {

  @Override
  public Class<UiBorderPanel> getType() {

    return UiBorderPanel.class;
  }

  @Override
  public UiBorderPanel create() {

    return new TvmBorderPanel();
  }

}
