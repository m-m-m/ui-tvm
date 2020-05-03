/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the implementation of controller management for TeaVM.
 */
module io.github.mmm.ui.tvm.controller {

  requires transitive io.github.mmm.ui.spi.controller;

  requires transitive io.github.mmm.ui.tvm.core;

  provides io.github.mmm.ui.api.controller.UiNavigationManager with //
      io.github.mmm.ui.tvm.controller.TvmNavigationManager;

}
