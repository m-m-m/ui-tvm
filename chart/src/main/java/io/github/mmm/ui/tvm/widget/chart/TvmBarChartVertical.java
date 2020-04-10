/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart;

import io.github.mmm.ui.api.widget.chart.UiBarChartHorizontal;
import io.github.mmm.ui.api.widget.chart.UiBarChartVertical;

/**
 * Implementation of {@link UiBarChartHorizontal} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmBarChartVertical extends TvmBarChart implements UiBarChartVertical {

  /**
   * The constructor.
   */
  public TvmBarChartVertical() {

    super();
  }

  @Override
  protected String getChartType() {

    return "bar";
  }

}
