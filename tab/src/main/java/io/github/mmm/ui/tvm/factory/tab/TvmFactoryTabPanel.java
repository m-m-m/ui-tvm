/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.tab;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.tab.UiTabPanel;
import io.github.mmm.ui.tvm.widget.tab.TvmTabPanel;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiTabPanel}.
 *
 * @since 1.0.0
 */
public class TvmFactoryTabPanel implements UiSingleWidgetFactoryNative<UiTabPanel> {

  @Override
  public Class<UiTabPanel> getType() {

    return UiTabPanel.class;
  }

  @Override
  public UiTabPanel create() {

    return new TvmTabPanel();
  }

}
