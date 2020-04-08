/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.format;

import org.teavm.jso.dom.html.HTMLTextAreaElement;

import io.github.mmm.ui.api.UiContext;
import io.github.mmm.ui.api.widget.format.UiHtmlEditor;
import io.github.mmm.ui.tvm.widget.input.TvmInput;

/**
 * Implementation of {@link UiHtmlEditor} for TeaVM.
 *
 * @since 1.0.0
 */
public class TvmHtmlEditor extends TvmInput<String, HTMLTextAreaElement> implements UiHtmlEditor {
  // TODO choose OSS JS Editor solution (Squire, Quill)

  /**
   * The constructor.
   *
   * @param context the {@link #getContext() context}.
   */
  public TvmHtmlEditor(UiContext context) {

    super(context, newTextArea());
  }

  @Override
  public String getValueOrThrow() {

    return this.widget.getValue();
  }

  @Override
  protected void setValueNative(String value) {

    this.widget.setValue(value);
  }

  @Override
  protected void setEnabledNative(boolean enabled) {

    this.widget.setDisabled(!enabled);
  }

}
