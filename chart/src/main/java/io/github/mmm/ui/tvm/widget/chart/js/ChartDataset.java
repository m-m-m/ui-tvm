/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart.js;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

/**
 *
 */
public abstract class ChartDataset implements JSObject {

  @JSProperty
  public abstract String getLabel();

  @JSProperty
  public abstract void setLabel(String label);

  @JSProperty
  public abstract String[] getBackgroundColor();

  @JSProperty
  public abstract void setBackgroundColor(String[] backgroundColor);

  @JSProperty
  public abstract float[] getData();

  @JSProperty
  public abstract void setData(float[] data);

  @JSBody(params = { "label", "data" }, script = "return {label:label,data:data};")
  public static native ChartDataset of(String label, float[] data);

  @JSBody(params = { "label", "data", "color" }, script = "return {label:label,data:data,backgroundColor:color};")
  public static native ChartDataset of(String label, float[] data, String color);

}
