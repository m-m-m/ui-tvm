/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.controller;

import org.teavm.jso.browser.History;
import org.teavm.jso.browser.Location;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.events.Event;

import io.github.mmm.ui.api.controller.AbstractUiController;
import io.github.mmm.ui.api.controller.UiPlace;
import io.github.mmm.ui.api.widget.UiWidget;
import io.github.mmm.ui.spi.controller.AbstractUiNavigationManagerImpl;
import io.github.mmm.ui.spi.controller.UiNavigationMode;
import io.github.mmm.ui.tvm.TvmApplication;

/**
 * Implementation of {@link io.github.mmm.ui.api.controller.UiNavigationManager} for JavaFx.
 *
 * @since 1.0.0
 */
public class TvmNavigationManager extends AbstractUiNavigationManagerImpl {

  private final Window window;

  private final History history;

  private String contextPath;

  /**
   * The constructor.
   */
  public TvmNavigationManager() {

    this(Window.current());
  }

  /**
   * The constructor.
   *
   * @param window the {@link Window} to use.
   */
  public TvmNavigationManager(Window window) {

    super();
    this.window = window;
    this.history = window.getHistory();
    this.window.addEventListener("popstate", this::onPopState);
  }

  @Override
  protected void doInit() {

    onLocationChange(true);
  }

  private void onPopState(Event e) {

    onLocationChange(false);
  }

  private void onLocationChange(boolean fallback) {

    Location location = this.window.getLocation();
    String anchor = location.getHash();
    String uri;
    if (isUseAnchor()) {
      if (anchor.startsWith("#")) {
        anchor = anchor.substring(1);
      }
      uri = anchor;
    } else {
      String pathName = location.getPathName();
      String ctx = getContextPath();
      if (pathName.startsWith(ctx)) {
        pathName = pathName.substring(ctx.length());
      }
      if (pathName.startsWith("/")) {
        pathName = pathName.substring(1);
      }
      String search = location.getSearch();
      uri = pathName + search + anchor;
    }
    UiPlace place = UiPlace.HOME;
    if (!uri.isEmpty()) {
      System.out.println(uri);
      UiPlace uriPlace = UiPlace.parse(uri);
      if (fallback) {
        AbstractUiController<UiWidget> controller = getController(uriPlace.getId());
        if (controller != null) {
          place = uriPlace;
        }
      } else {
        place = uriPlace;
      }
    }
    navigateTo(place, UiNavigationMode.NONE, false);
  }

  /**
   * @return the context path of this application.
   */
  public String getContextPath() {

    if (this.contextPath == null) {
      return TvmApplication.getContextPath();
    }
    return this.contextPath;
  }

  /**
   * @param contextPath the context path of this application.
   */
  public void setContextPath(String contextPath) {

    if (!contextPath.startsWith("/")) {
      contextPath = "/" + contextPath;
    }
    if (!contextPath.endsWith("/")) {
      contextPath = contextPath + "/";
    }
    this.contextPath = contextPath;
  }

  /**
   * @return {@link TvmApplication#isUseAnchor()}.
   */
  public boolean isUseAnchor() {

    return TvmApplication.isUseAnchor();
  }

  @Override
  protected void addHistory(UiPlace place) {

    super.addHistory(place);
    String url = createUrl(place);
    String title = getTitle(place);
    this.history.pushState(new TvmHistoryState(), title, url);
  }

  @Override
  protected void replaceHistory(UiPlace place) {

    super.replaceHistory(place);
    String url = createUrl(place);
    String title = getTitle(place);
    this.history.replaceState(new TvmHistoryState(), title, url);
  }

  private String createUrl(UiPlace place) {

    StringBuilder sb = new StringBuilder();
    if (isUseAnchor()) {
      sb.append(UiPlace.SEPARATOR_ANCHOR);
    } else {
      sb.append(getContextPath());
    }
    sb.append(place.toString());
    return sb.toString();
  }

  private String getTitle(UiPlace place) {

    AbstractUiController<UiWidget> controller = getController(place.getId());
    if (controller != null) {
      return controller.getTitle();
    }
    return "";
  }

  @Override
  public UiPlace navigateBack() {

    this.history.back();
    return super.navigateBack();
  }

  @Override
  public UiPlace navigateForward() {

    this.history.forward();
    return super.navigateForward();
  }

}
