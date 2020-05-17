/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the implementation of UI chart widgets for TeaVM.
 * 
 * @provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.format {

  requires transitive io.github.mmm.ui.api.format;

  requires transitive io.github.mmm.ui.tvm.core;

  provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative with //
      // io.github.mmm.ui.tvm.factory.format.TvmFactorySourceCodeViewer, //
      io.github.mmm.ui.tvm.factory.format.TvmFactoryHtmlEditor //
  ;

}
