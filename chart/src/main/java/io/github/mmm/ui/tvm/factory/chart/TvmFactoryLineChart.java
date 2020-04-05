/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.chart;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.widget.chart.TvmLineChart;
import io.github.mmm.ui.widget.chart.UiLineChart;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiLineChart}.
 *
 * @since 1.0.0
 */
public class TvmFactoryLineChart implements UiSingleWidgetFactoryNative<UiLineChart> {

  @Override
  public Class<UiLineChart> getType() {

    return UiLineChart.class;
  }

  @Override
  public UiLineChart create(UiContext context) {

    return new TvmLineChart(context);
  }

}
