/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm;

import java.util.Locale;

import org.teavm.jso.JSBody;

import io.github.mmm.ui.api.UiApplication;

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

  private static String contextPath = "/";

  private static boolean useAnchor = true;

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

  /**
   * @return the context path of this application. So if your application is running in the root context this should be
   *         "/" what is the default. Otherwise you can {@link #setContextPath(String) set} the context path according
   *         to your app (e.g. "/my-cool-app/" or even "/my-cool-app/index.html").
   */
  public static String getContextPath() {

    return contextPath;
  }

  /**
   * @param contextPath new value of {@link #getContextPath()}.
   */
  protected static void setContextPath(String contextPath) {

    if (!contextPath.startsWith("/")) {
      contextPath = "/" + contextPath;
    }
    if (!contextPath.endsWith("/")) {
      contextPath = contextPath + "/";
    }
    TvmApplication.contextPath = contextPath;
  }

  /**
   * @return {@code true} to use the anchor (the URL part starting with the hash sign '#') for state management and
   *         {@code UiPlace}s.
   */
  public static boolean isUseAnchor() {

    return useAnchor;
  }

  /**
   * @param useAnchor new value of {@link #isUseAnchor()}.
   */
  public static void setUseAnchor(boolean useAnchor) {

    TvmApplication.useAnchor = useAnchor;
  }
}
