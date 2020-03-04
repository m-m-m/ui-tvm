
/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
import io.github.mmm.ui.factory.UiSingleWidgetFactoryNative;
import io.github.mmm.ui.tvm.factory.chart.TvmFactoryBarChartHorizontal;
import io.github.mmm.ui.tvm.factory.chart.TvmFactoryBarChartVertical;
import io.github.mmm.ui.tvm.factory.chart.TvmFactoryPieChart;

/**
 * Provides the implementation of UI chart widgets based on TeaVM.
 */
@SuppressWarnings("rawtypes") //
module io.github.mmm.ui.tvm.chart {

  requires transitive io.github.mmm.ui.tvm.core;

  provides UiSingleWidgetFactoryNative with //
      TvmFactoryBarChartHorizontal, //
      TvmFactoryBarChartVertical, //
      TvmFactoryPieChart//
  ;

}
