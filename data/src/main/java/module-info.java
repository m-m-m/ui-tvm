/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the implementation of UI data widgets for TeaVM.
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.data {

  requires transitive io.github.mmm.ui.api.data;

  requires transitive io.github.mmm.ui.tvm.core;

  provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.data.TvmFactoryDataTable //
  ;

}
