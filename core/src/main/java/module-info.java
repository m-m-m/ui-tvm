
/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryButton;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryCheckbox;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryFormPanel;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryHorizontalPanel;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryIntegerInput;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryLabel;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryMenu;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryMenuItem;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryPasswordInput;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryRadioButton;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryTab;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryTabPanel;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryTextArea;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryTextInput;
import io.github.mmm.ui.tvm.factory.core.TvmFactoryVerticalPanel;

/**
 * Provides the implementation of the UI framework based on TeaVM.
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.core {

  requires transitive io.github.mmm.ui;

  // TODO bug in JPMS or Eclipse as this is already a transitive dependency of ui
  requires transitive io.github.mmm.value;

  // TODO bug in JPMS or Eclipse as this is already a transitive dependency of ui
  requires transitive io.github.mmm.validation;

  requires transitive teavm.jso;

  requires transitive teavm.jso.apis;

  provides UiSingleWidgetFactoryNative with //
      TvmFactoryButton, //
      TvmFactoryCheckbox, //
      TvmFactoryFormPanel, //
      TvmFactoryLabel, //
      TvmFactoryHorizontalPanel, //
      TvmFactoryIntegerInput, //
      TvmFactoryMenu, //
      TvmFactoryMenuItem, //
      // TvmFactoryMenuItemCheckbox, //
      // TvmFactoryMenuItemRadioButton, //
      // TvmFactoryMenuItemSeparator, //
      TvmFactoryPasswordInput, //
      // TvmFactoryPopup, //
      TvmFactoryRadioButton, //
      TvmFactoryTab, //
      TvmFactoryTabPanel, //
      TvmFactoryTextInput, //
      TvmFactoryTextArea, //
      TvmFactoryVerticalPanel //
  // TvmFactoryWindow
  ;

  exports io.github.mmm.ui.tvm;

  exports io.github.mmm.ui.tvm.widget;

}
