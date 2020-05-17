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
module io.github.mmm.ui.tvm.number {

  requires transitive io.github.mmm.ui.api.number;

  requires transitive io.github.mmm.ui.tvm.core;

  provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.number.TvmFactoryBigDecimalInput, //
      io.github.mmm.ui.tvm.factory.number.TvmFactoryBigIntegerInput, //
      io.github.mmm.ui.tvm.factory.number.TvmFactoryDoubleInput, //
      io.github.mmm.ui.tvm.factory.number.TvmFactoryIntegerInput, //
      io.github.mmm.ui.tvm.factory.number.TvmFactoryIntegerSlider, //
      io.github.mmm.ui.tvm.factory.number.TvmFactoryLongInput, //
      io.github.mmm.ui.tvm.factory.number.TvmFactoryLongSlider //
  ;

}
