/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * Provides the implementation of the UI framework based on TeaVM.
 *
 * @provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative
 * @provides io.github.mmm.ui.api.factory.UiToggleGroupFactory
 * @provides io.github.mmm.ui.api.UiDispatcher
 * @provides io.github.mmm.ui.api.UiScreen
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.core {

  requires transitive io.github.mmm.ui.spi.core;

  requires transitive io.github.mmm.ui.api.form;

  requires transitive org.teavm.jso;

  requires transitive org.teavm.jso.apis;

  provides io.github.mmm.ui.api.UiScreen with //
      io.github.mmm.ui.tvm.TvmScreen;

  provides io.github.mmm.ui.api.UiDispatcher with //
      io.github.mmm.ui.tvm.TvmDispatcher;

  provides io.github.mmm.ui.api.factory.UiToggleGroupFactory with //
      io.github.mmm.ui.tvm.TvmToggleGroupFactory;

  provides io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative with //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryBorderPanel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryButton, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryButtonPanel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryCheckbox, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryComboBoxDataList, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryExternalLink, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryGridPanel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryHorizontalPanel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryIcon, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryImage, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryInputContainer, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryInternalLink, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryLabel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryPasswordInput, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryRadioButton, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryRadioChoice, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryScrollPanel, //
      io.github.mmm.ui.tvm.factory.core.TvmFactorySlot, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryText, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryTextInput, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryTextArea, //
      io.github.mmm.ui.tvm.factory.core.TvmFactoryVerticalPanel //
  ;

  exports io.github.mmm.ui.tvm;

  exports io.github.mmm.ui.tvm.widget;

  exports io.github.mmm.ui.tvm.widget.button;

  exports io.github.mmm.ui.tvm.widget.input;

  exports io.github.mmm.ui.tvm.widget.composite;

  exports io.github.mmm.ui.tvm.widget.link;

  exports io.github.mmm.ui.tvm.widget.panel;

}
