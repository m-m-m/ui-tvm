/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the implementation of the UI framework based on TeaVM.
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.window {

  requires transitive io.github.mmm.ui.api.window;

  requires transitive io.github.mmm.ui.tvm.core;

  provides io.github.mmm.ui.api.notifier.UiNotifier with //
      io.github.mmm.ui.tvm.notifier.TvmNotifier;

  provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.window.TvmFactoryMainWindow, //
      io.github.mmm.ui.tvm.factory.window.TvmFactoryPopup, //
      io.github.mmm.ui.tvm.factory.window.TvmFactoryWindow //
  ;

}
