/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart.js;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

/**
 * {@link JSObject} to wrap {@code data} from {@code Chart.js}.
 */
@SuppressWarnings("javadoc")
public abstract class ChartData implements JSObject {

  @JSProperty
  public abstract String[] getLabels();

  @JSProperty
  public abstract void setLabels(String[] labels);

  @JSProperty
  public abstract ChartDataset[] getDatasets();

  @JSProperty
  public abstract void setDatasets(ChartDataset[] datasets);

  @JSBody(params = { "datasets" }, script = "return {datasets:datasets};")
  public static native ChartData of(ChartDataset[] datasets);

  @JSBody(params = { "datasets", "labels" }, script = "return {datasets:datasets,labels:labels};")
  public static native ChartData of(ChartDataset[] datasets, String[] labels);

}
