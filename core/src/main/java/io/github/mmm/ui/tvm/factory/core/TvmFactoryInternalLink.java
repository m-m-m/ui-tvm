/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.core;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.link.UiInternalLink;
import io.github.mmm.ui.tvm.widget.link.TvmInternalLink;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiInternalLink}.
 *
 * @since 1.0.0
 */
public class TvmFactoryInternalLink implements UiSingleWidgetFactoryNative<UiInternalLink> {

  @Override
  public Class<UiInternalLink> getType() {

    return UiInternalLink.class;
  }

  @Override
  public UiInternalLink create() {

    return new TvmInternalLink();
  }

}
