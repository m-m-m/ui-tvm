/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.input;

import io.github.mmm.ui.api.widget.input.UiTextInput;

/**
 * Implementation of {@link UiTextInput} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmTextInput extends TvmStringInput implements UiTextInput {

  /**
   * The constructor.
   */
  public TvmTextInput() {

    super("text");
  }

}
