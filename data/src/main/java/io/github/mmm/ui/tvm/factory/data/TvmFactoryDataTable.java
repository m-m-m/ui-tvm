/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.factory.data;

import io.github.mmm.ui.api.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.api.widget.data.UiDataTable;
import io.github.mmm.ui.tvm.widget.data.TvmDataTable;

/**
 * {@link UiSingleWidgetFactoryNative} for {@link UiDataTable}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class TvmFactoryDataTable implements UiSingleWidgetFactoryNative<UiDataTable> {

  @Override
  public Class<UiDataTable> getType() {

    return UiDataTable.class;
  }

  @Override
  public UiDataTable create() {

    return new TvmDataTable();
  }

}
