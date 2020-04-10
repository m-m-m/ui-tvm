/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart;

import io.github.mmm.ui.api.datatype.UiPoint;
import io.github.mmm.ui.api.datatype.chart.UiDataSet;
import io.github.mmm.ui.api.widget.chart.UiLineChart;
import io.github.mmm.ui.tvm.widget.chart.js.ChartData;
import io.github.mmm.ui.tvm.widget.chart.js.ChartDataset;
import io.github.mmm.ui.tvm.widget.chart.js.ChartPoint;

/**
 * Implementation of {@link UiLineChart} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmLineChart extends TvmChart<UiPoint[]> implements UiLineChart {

  /**
   * The constructor.
   */
  public TvmLineChart() {

    super();
  }

  @Override
  protected String getChartType() {

    return "line";
  }

  @Override
  protected ChartData convert(UiDataSet<UiPoint[]>[] dataSets) {

    ChartDataset[] chartDatasets = new ChartDataset[dataSets.length];
    int i = 0;
    for (UiDataSet<UiPoint[]> dataSet : dataSets) {
      chartDatasets[i] = convert(dataSet, i);
      i++;
    }
    ChartData data = ChartData.of(chartDatasets);
    return data;
  }

  private ChartDataset convert(UiDataSet<UiPoint[]> dataSet, int dataSetIndex) {

    UiPoint[] points = dataSet.getData();
    ChartPoint[] data = new ChartPoint[points.length];
    for (int i = 0; i < points.length; i++) {
      UiPoint point = points[i];
      data[i] = ChartPoint.of((float) point.getX(), (float) point.getY());
    }
    ChartDataset chartDataset = ChartDataset.of(dataSet.getTitle(), data, dataSet.getColor(dataSetIndex).toString(),
        false);
    return chartDataset;
  }

}
