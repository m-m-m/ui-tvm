/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart.js;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

/**
 * Options of {@code Chart.js}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("javadoc")
public abstract class ChartOptions implements JSObject {

  @JSProperty
  public abstract boolean isResponsive();

  @JSProperty
  public abstract void setResponsive(boolean responsive);

  @JSProperty
  public abstract ChartOptionsLegend getLegend();

  @JSProperty
  public abstract void setLegend(ChartOptionsLegend legend);

  @JSProperty
  public abstract ChartOptionsTitle getTitle();

  @JSProperty
  public abstract void setTitle(ChartOptionsTitle title);

  @JSBody(params = {
  "title" }, script = "return {responsive:true,legend:{position:'bottom'},title:{display:true,text:title}};")
  public static native ChartOptions of(String title);

}
