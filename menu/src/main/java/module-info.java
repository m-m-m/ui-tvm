
/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;

/**
 * Provides the implementation of the UI framework based on TeaVM.
 *
 * @provides UiSingleWidgetFactoryNative
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.menu {

  requires transitive io.github.mmm.ui.api.menu;

  requires transitive io.github.mmm.ui.tvm.core;

  provides UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.menu.TvmFactoryMenuBar, //
      io.github.mmm.ui.tvm.factory.menu.TvmFactoryNavigationBar //
  ;

}
