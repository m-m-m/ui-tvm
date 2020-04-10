/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.media;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.media.UiAudioPlayer;
import io.github.mmm.ui.tvm.widget.media.TvmAudioPlayer;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiAudioPlayer}.
 *
 * @since 1.0.0
 */
public class TvmFactoryAudioPlayer implements UiSingleWidgetFactoryNative<UiAudioPlayer> {

  @Override
  public Class<UiAudioPlayer> getType() {

    return UiAudioPlayer.class;
  }

  @Override
  public UiAudioPlayer create() {

    return new TvmAudioPlayer();
  }

}
