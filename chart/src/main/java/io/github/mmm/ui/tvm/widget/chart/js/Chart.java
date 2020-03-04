/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart.js;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;
import org.teavm.jso.dom.html.HTMLCanvasElement;

/**
 * {@link JSObject} to wrap {@code Chart} from {@code Chart.js}.
 */
public abstract class Chart implements JSObject {

  @JSProperty
  public abstract String getType();

  @JSProperty
  public abstract void setType(String type);

  @JSProperty
  public abstract ChartConfig getConfig();

  @JSProperty
  public abstract void setConfig(ChartConfig config);

  public abstract void update();

  @JSBody(params = { "canvas", "config" }, script = "return new Chart(canvas.getContext('2d'), config);")
  public static native Chart of(HTMLCanvasElement canvas, ChartConfig config);

}
