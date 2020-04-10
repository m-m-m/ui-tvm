/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.chart;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.chart.UiBarChartVertical;
import io.github.mmm.ui.tvm.widget.chart.TvmBarChartVertical;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiBarChartVertical}.
 *
 * @since 1.0.0
 */
public class TvmFactoryBarChartVertical implements UiSingleWidgetFactoryNative<UiBarChartVertical> {

  @Override
  public Class<UiBarChartVertical> getType() {

    return UiBarChartVertical.class;
  }

  @Override
  public UiBarChartVertical create() {

    return new TvmBarChartVertical();
  }

}
