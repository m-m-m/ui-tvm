/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.chart.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

/**
 *
 */
public interface ChartOptionsTitle extends JSObject {

  @JSProperty
  boolean isDisplay();

  @JSProperty
  void setDisplay(boolean responsive);

  @JSProperty
  String getText();

  @JSProperty
  void setText(String text);

}
