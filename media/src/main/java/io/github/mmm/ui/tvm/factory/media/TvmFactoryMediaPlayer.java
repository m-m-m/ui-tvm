/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.media;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.media.UiMediaPlayer;
import io.github.mmm.ui.tvm.widget.media.TvmMediaPlayer;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiMediaPlayer}.
 *
 * @since 1.0.0
 */
public class TvmFactoryMediaPlayer implements UiSingleWidgetFactoryNative<UiMediaPlayer> {

  @Override
  public Class<UiMediaPlayer> getType() {

    return UiMediaPlayer.class;
  }

  @Override
  public UiMediaPlayer create() {

    return new TvmMediaPlayer();
  }

}
