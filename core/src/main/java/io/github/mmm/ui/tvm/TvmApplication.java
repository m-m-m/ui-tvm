/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm;

import java.util.Locale;

import org.teavm.jso.JSBody;

import io.github.mmm.ui.spi.UiApplication;

/**
 * Base class for you main application to build your client with TeaVM.<br>
 * Example:
 *
 * <pre>
 * public class MyTvmApp extends {@link TvmApplication} {
 *
 *   protected void start() {
 *     MyApp myApp = new MyApp();
 *     myApp.run();
 *   }
 *
 *   public static void main(String[] args) {
 *     new MyTvmApp().run();
 *   }
 * }
 * </pre>
 *
 * @since 1.0.0
 */
public abstract class TvmApplication implements UiApplication {

  /**
   * The constructor.
   */
  public TvmApplication() {

    super();
    if (Locale.getDefault() == null) {
      Locale.setDefault(new Locale(getLanguage()));
    }
  }

  @JSBody(script = "return navigator.language || navigator.userLanguage;")
  private static native String getLanguage();
}
