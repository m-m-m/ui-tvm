/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart;

import io.github.mmm.ui.api.datatype.chart.UiDataSeries;
import io.github.mmm.ui.api.datatype.chart.UiDataSet;
import io.github.mmm.ui.api.widget.chart.UiBarChart;
import io.github.mmm.ui.tvm.widget.chart.js.ChartData;
import io.github.mmm.ui.tvm.widget.chart.js.ChartDataset;

/**
 * Implementation of {@link UiBarChart} for TeaVM.
 *
 * @since 1.0.0
 */
public abstract class TvmBarChart extends TvmChart<UiDataSeries> implements UiBarChart {

  private String[] labels;

  /**
   * The constructor.
   */
  public TvmBarChart() {

    super();
  }

  @Override
  public void setSeriesLabels(String... labels) {

    this.labels = labels;
    if (this.chart != null) {
      this.chart.getConfig().getData().setLabels(labels);
      this.chart.update();
    }
  }

  @Override
  protected ChartData convert(UiDataSet<UiDataSeries>[] dataSets) {

    ChartDataset[] chartDatasets = new ChartDataset[dataSets.length];
    int i = 0;
    for (UiDataSet<UiDataSeries> dataSet : dataSets) {
      chartDatasets[i] = convert(dataSet, i);
      i++;
    }
    return ChartData.of(chartDatasets, this.labels);
  }

  private ChartDataset convert(UiDataSet<UiDataSeries> dataSet, int dataSetIndex) {

    UiDataSeries series = dataSet.getData();
    int count = series.getCount();
    float[] data = new float[count];
    for (int i = 0; i < count; i++) {
      data[i] = series.get(i);
    }
    ChartDataset chartDataset = ChartDataset.of(dataSet.getTitle(), data, dataSet.getColor(dataSetIndex).toString());
    return chartDataset;
  }

}
