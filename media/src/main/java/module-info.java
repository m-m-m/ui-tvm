/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the implementation of UI media widgets based on TeaVM.
 * 
 * @provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.media {

  requires transitive io.github.mmm.ui.api.media;

  requires transitive io.github.mmm.ui.tvm.core;

  provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.media.TvmFactoryAudioPlayer, //
      io.github.mmm.ui.tvm.factory.media.TvmFactoryMediaPlayer, //
      io.github.mmm.ui.tvm.factory.media.TvmFactoryVideoPlayer //
  ;

}
