/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.widget.chart.UiBarChartHorizontal;

/**
 * Implementation of {@link UiBarChartHorizontal} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmBarChartHorizontal extends TvmBarChart implements UiBarChartHorizontal {

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmBarChartHorizontal(UiContext context) {

    super(context);
  }

  @Override
  protected String getChartType() {

    return "horizontalBar";
  }

}
