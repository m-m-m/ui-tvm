/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart.js;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

/**
 * {@link JSObject} to wrap {@code Point} from {@code Chart.js}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("javadoc")
public abstract class ChartPoint implements JSObject {

  @JSProperty
  public abstract float getX();

  @JSProperty
  public abstract float getY();

  /**
   * @param x the {@link #getX() x-coordinate}.
   * @param y the {@link #getY() y-coordinate}.
   * @return the new {@link ChartPoint}.
   */
  @JSBody(params = { "x", "y" }, script = "return {x:x,y:y};")
  public static native ChartPoint of(float x, float y);

}
