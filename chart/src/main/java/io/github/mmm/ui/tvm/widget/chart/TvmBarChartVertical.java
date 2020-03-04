/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.widget.chart.UiBarChartHorizontal;
import io.github.mmm.ui.widget.chart.UiBarChartVertical;

/**
 * Implementation of {@link UiBarChartHorizontal} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmBarChartVertical extends TvmBarChart implements UiBarChartVertical {

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmBarChartVertical(UiContext context) {

    super(context);
  }

  @Override
  protected String getChartType() {

    return "bar";
  }

}
