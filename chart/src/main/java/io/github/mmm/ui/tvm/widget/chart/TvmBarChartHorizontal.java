/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart;

import io.github.mmm.ui.api.widget.chart.UiBarChartHorizontal;

/**
 * Implementation of {@link UiBarChartHorizontal} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmBarChartHorizontal extends TvmBarChart implements UiBarChartHorizontal {

  /**
   * The constructor.
   */
  public TvmBarChartHorizontal() {

    super();
  }

  @Override
  protected String getChartType() {

    return "horizontalBar";
  }

}
