/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Aggregates all UI modules of for TeaVM.
 */
module io.github.mmm.ui.tvm.all {

  requires transitive io.github.mmm.ui.spi.all;

  requires transitive io.github.mmm.ui.tvm.chart;

  requires transitive io.github.mmm.ui.tvm.controller;

  requires transitive io.github.mmm.ui.tvm.form;

  requires transitive io.github.mmm.ui.tvm.format;

  requires transitive io.github.mmm.ui.tvm.media;

  requires transitive io.github.mmm.ui.tvm.menu;

  requires transitive io.github.mmm.ui.tvm.number;

  requires transitive io.github.mmm.ui.tvm.time;

  requires transitive io.github.mmm.ui.tvm.window;

}
