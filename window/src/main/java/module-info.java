
/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;

/**
 * Provides the implementation of the UI framework based on TeaVM.
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.window {

  requires transitive io.github.mmm.ui.tvm.core;

  provides UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.window.TvmFactoryPopup, //
      io.github.mmm.ui.tvm.factory.window.TvmFactoryWindow //
  ;

}
