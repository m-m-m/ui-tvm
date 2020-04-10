/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.panel.UiVerticalPanel;
import io.github.mmm.ui.tvm.widget.panel.TvmVerticalPanel;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiVerticalPanel}.
 *
 * @since 1.0.0
 */
public class TvmFactoryVerticalPanel implements UiSingleWidgetFactoryNative<UiVerticalPanel> {

  @Override
  public Class<UiVerticalPanel> getType() {

    return UiVerticalPanel.class;
  }

  @Override
  public UiVerticalPanel create() {

    return new TvmVerticalPanel();
  }

}
