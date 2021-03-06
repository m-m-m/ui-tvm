/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.media;

import org.teavm.jso.dom.html.HTMLAudioElement;

import io.github.mmm.ui.api.widget.media.UiAudioPlayer;

/**
 * Implementation of {@link UiAudioPlayer} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmAudioPlayer extends TvmMediaWidget<HTMLAudioElement> implements UiAudioPlayer {

  /**
   * The constructor.
   */
  public TvmAudioPlayer() {

    super(newAudio());
  }

}
