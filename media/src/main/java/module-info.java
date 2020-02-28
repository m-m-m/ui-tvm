import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.factory.media.TvmFactoryAudioPlayer;
import io.github.mmm.ui.tvm.factory.media.TvmFactoryMediaPlayer;
import io.github.mmm.ui.tvm.factory.media.TvmFactoryVideoPlayer;

/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the implementation of UI media widgets based on TeaVM.
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.media {

  requires transitive io.github.mmm.ui.tvm.core;

  provides UiSingleWidgetFactoryNative with //
      TvmFactoryAudioPlayer, //
      TvmFactoryMediaPlayer, //
      TvmFactoryVideoPlayer //
  ;

}
