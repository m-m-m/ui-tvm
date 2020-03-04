/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart;

import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.datatype.chart.UiDataSet;
import io.github.mmm.ui.tvm.widget.chart.js.ChartData;
import io.github.mmm.ui.tvm.widget.chart.js.ChartDataset;
import io.github.mmm.ui.widget.chart.UiPieChart;

/**
 * Implementation of {@link UiPieChart} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmPieChart extends TvmChart<Number> implements UiPieChart {

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmPieChart(UiContext context) {

    super(context);
  }

  @Override
  protected String getChartType() {

    return "pie";
  }

  @Override
  protected ChartData convert(UiDataSet<Number>[] dataSets) {

    float[] data = new float[dataSets.length];
    String[] labels = new String[dataSets.length];
    String[] colors = new String[dataSets.length];
    int i = 0;
    for (UiDataSet<Number> dataSet : dataSets) {
      data[i] = dataSet.getData().floatValue();
      labels[i] = dataSet.getTitle();
      colors[i] = dataSet.getColor(i).toString();
      i++;
    }
    ChartDataset chartDataset = ChartDataset.of("Pie", data);
    chartDataset.setBackgroundColor(colors);
    return ChartData.of(new ChartDataset[] { chartDataset }, labels);
  }

}
