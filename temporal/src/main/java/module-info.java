/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the implementation of the UI framework based on TeaVM.
 * 
 * @provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.temporal {

  requires transitive io.github.mmm.ui.api.temporal;

  requires transitive io.github.mmm.ui.tvm.core;

  provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.temporal.TvmFactoryDateInput, //
      io.github.mmm.ui.tvm.factory.temporal.TvmFactoryDateTimeInput, //
      io.github.mmm.ui.tvm.factory.temporal.TvmFactoryTimeInput;

}
