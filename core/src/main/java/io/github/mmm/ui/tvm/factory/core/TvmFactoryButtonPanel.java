/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.panel.UiButtonPanel;
import io.github.mmm.ui.tvm.widget.panel.TvmButtonPanel;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiButtonPanel}.
 *
 * @since 1.0.0
 */
public class TvmFactoryButtonPanel implements UiSingleWidgetFactoryNative<UiButtonPanel> {

  @Override
  public Class<UiButtonPanel> getType() {

    return UiButtonPanel.class;
  }

  @Override
  public UiButtonPanel create() {

    return new TvmButtonPanel();
  }

}
