/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart.js;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

/**
 * {@link JSObject} to wrap {@code Chart} from {@code Chart.js}.
 */
public abstract class ChartConfig implements JSObject {

  @JSProperty
  public abstract String getType();

  @JSProperty
  public abstract void setType(String type);

  @JSProperty
  public abstract ChartData getData();

  @JSProperty
  public abstract void setData(ChartData data);

  @JSProperty
  public abstract ChartOptions getOptions();

  @JSProperty
  public abstract void setOptions(ChartOptions options);

  @JSBody(params = { "type", "data", "options" }, script = "return {type:type,data:data,options:options};")
  public static native ChartConfig of(String type, ChartData data, ChartOptions options);

}
