/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the implementation of UI menu widgets based on TeaVM.
 *
 * @provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.menu {

  requires transitive io.github.mmm.ui.api.menu;

  requires transitive io.github.mmm.ui.tvm.core;

  provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.menu.TvmFactoryMenuBar, //
      io.github.mmm.ui.tvm.factory.menu.TvmFactoryNavigationBar //
  ;

}
