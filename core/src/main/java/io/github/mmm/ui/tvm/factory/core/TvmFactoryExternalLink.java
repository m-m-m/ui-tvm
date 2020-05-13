/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.link.UiExternalLink;
import io.github.mmm.ui.tvm.widget.link.TvmExternalLink;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiExternalLink}.
 *
 * @since 1.0.0
 */
public class TvmFactoryExternalLink implements UiSingleWidgetFactoryNative<UiExternalLink> {

  @Override
  public Class<UiExternalLink> getType() {

    return UiExternalLink.class;
  }

  @Override
  public UiExternalLink create() {

    return new TvmExternalLink();
  }

}
