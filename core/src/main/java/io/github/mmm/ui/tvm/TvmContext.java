/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm;

import io.github.mmm.ui.api.UiDispatcher;
import io.github.mmm.ui.api.UiNotifier;
import io.github.mmm.ui.api.UiScreen;
import io.github.mmm.ui.api.UiToggleGroup;
import io.github.mmm.ui.api.widget.window.UiMainWindow;
import io.github.mmm.ui.spi.AbstractUiContext;
import io.github.mmm.ui.tvm.widget.TvmMainWindow;

/**
 * Implementation of {@link io.github.mmm.ui.api.UiContext} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmContext extends AbstractUiContext {

  private final TvmMainWindow mainWindow;

  private final TvmScreen screen;

  private final TvmDispatcher dispatcher;

  /**
   * The constructor.
   */
  public TvmContext() {

    // super(new TvmWidgetFactoryNative(), null, null);
    this.mainWindow = new TvmMainWindow(this);
    this.screen = new TvmScreen();
    this.dispatcher = new TvmDispatcher();
    // root = document.documentElement;
    // root.style.setProperty('--bg', '#000000');
  }

  @Override
  public UiMainWindow getMainWindow() {

    return this.mainWindow;
  }

  @Override
  public UiScreen getScreen() {

    return this.screen;
  }

  @Override
  public UiDispatcher getDispatcher() {

    return this.dispatcher;
  }

  @Override
  public UiToggleGroup createToggleGroup() {

    return new TvmToggleGroup();
  }

  @Override
  protected UiNotifier createDefaultNotifier() {

    return new TvmNotifier(this);
  }

}
