/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart;

import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLElement;

import io.github.mmm.base.placement.Direction;
import io.github.mmm.ui.UiContext;
import io.github.mmm.ui.datatype.chart.UiDataSet;
import io.github.mmm.ui.tvm.widget.TvmWidgetHtmlElement;
import io.github.mmm.ui.tvm.widget.chart.js.Chart;
import io.github.mmm.ui.tvm.widget.chart.js.ChartConfig;
import io.github.mmm.ui.tvm.widget.chart.js.ChartData;
import io.github.mmm.ui.tvm.widget.chart.js.ChartOptions;
import io.github.mmm.ui.tvm.widget.chart.js.ChartOptionsLegend;
import io.github.mmm.ui.widget.chart.UiChart;

/**
 * Implementation of {@link io.github.mmm.ui.widget.UiNativeWidget} for TeaVM based on {@link HTMLElement}.
 *
 * @param <D> type of the chart {@link UiDataSet#getData() data}.
 * @since 1.0.0
 */
public abstract class TvmChart<D> extends TvmWidgetHtmlElement<HTMLCanvasElement> implements UiChart<D> {

  private String title;

  private Direction legendPlacement;

  /** The {@link Chart}. May be {@code null} unless initialized. */
  protected Chart chart;

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmChart(UiContext context) {

    super(context, newCanvas());
    this.legendPlacement = Direction.DOWN;
  }

  @Override
  public String getTitle() {

    return this.title;
  }

  @Override
  public void setTitle(String title) {

    this.title = title;
    if (this.chart != null) {
      this.chart.getConfig().getOptions().getTitle().setText(title);
      this.chart.update();
    }
  }

  @Override
  public Direction getLegnedPlacement() {

    return this.legendPlacement;
  }

  @Override
  public void setLegendPlacement(Direction placement) {

    this.legendPlacement = placement;
    if (this.chart != null) {
      ChartOptionsLegend legend = this.chart.getConfig().getOptions().getLegend();
      if (placement == null) {
        // TODO
      } else {
        legend.setPosition(convert(placement));
      }
      this.chart.update();
    }
  }

  private String convert(Direction placement) {

    if (placement == null) {
      return null;
    }
    switch (placement) {
      case UP:
      case UP_LEFT:
      case UP_RIGHT:
        return "top";
      case DOWN:
      case DOWN_LEFT:
      case DOWN_RIGHT:
        return "bottom";
      case LEFT:
        return "left";
      case RIGHT:
        return "right";
    }
    return null;
  }

  /**
   * @return the chart type for {@code Chart.js}.
   */
  protected abstract String getChartType();

  @SuppressWarnings("unchecked")
  @Override
  public void setData(UiDataSet<D>... dataSets) {

    ChartData data = convert(dataSets);
    if (this.chart == null) {
      ChartOptions options = ChartOptions.of(this.title);
      options.getLegend().setPosition(convert(this.legendPlacement));
      ChartConfig config = ChartConfig.of(getChartType(), data, options);
      this.chart = Chart.of(this.widget, config);
    } else {
      this.chart.getConfig().setData(data);
      this.chart.update();
    }
  }

  /**
   * @param dataSets the {@link UiDataSet}s.
   * @return the converted {@link ChartData}.
   */
  protected abstract ChartData convert(UiDataSet<D>[] dataSets);

  @Override
  protected void setEnabledNative(boolean enabled) {

  }

}
