
/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;

/**
 * Provides the implementation of the UI framework based on TeaVM.
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.core {

  requires transitive io.github.mmm.ui.api.core;

  // TODO bug in JPMS or Eclipse as this is already a transitive dependency of ui
  requires transitive io.github.mmm.value;

  // TODO bug in JPMS or Eclipse as this is already a transitive dependency of ui
  requires transitive io.github.mmm.validation;

  requires transitive teavm.jso;

  requires transitive teavm.jso.apis;

  provides UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryButton, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryCheckbox, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryFormPanel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryLabel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryHorizontalPanel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryIcon, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryImage, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryIntegerInput, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryIntegerSlider, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryPasswordInput, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryRadioButton, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryScrollPanel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactorySlot, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryTab, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryTabPanel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryText, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryTextInput, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryTextArea, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryVerticalPanel //
  ;

  exports io.github.mmm.ui.tvm;

  exports io.github.mmm.ui.tvm.widget;

  exports io.github.mmm.ui.tvm.widget.input;

  exports io.github.mmm.ui.tvm.widget.composite;

  exports io.github.mmm.ui.tvm.widget.panel;

}
