/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.chart;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.chart.UiBarChartHorizontal;
import io.github.mmm.ui.tvm.widget.chart.TvmBarChartHorizontal;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiBarChartHorizontal}.
 *
 * @since 1.0.0
 */
public class TvmFactoryBarChartHorizontal implements UiSingleWidgetFactoryNative<UiBarChartHorizontal> {

  @Override
  public Class<UiBarChartHorizontal> getType() {

    return UiBarChartHorizontal.class;
  }

  @Override
  public UiBarChartHorizontal create(UiContext context) {

    return new TvmBarChartHorizontal(context);
  }

}
