/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import io.github.mmm.ui.api.widget.menu.UiAbstractMenu;
import io.github.mmm.ui.api.widget.menu.UiAbstractMenuEntry;

/**
 * Interface for all TeaVM based implementations of {@link UiAbstractMenuEntry}.
 *
 * @param <E> type of the {@link UiAbstractMenuEntry menu entries} in this menu.
 * @since 1.0.0
 */
public interface TvmAbstractMenu<E extends UiAbstractMenuEntry> extends UiAbstractMenu<E> {

  /**
   * @return the currently selected {@link TvmAbstractMenuEntry entry} ({@link #getChild(int) child}, or {@code null} if
   *         no current entry is selected from this menu or menu-bar.
   */
  TvmAbstractMenuEntry getCurrentEntry();

  /**
   * @param currentEntry the new value of {@link #getCurrentEntry()}.
   */
  void setCurrentEntry(TvmAbstractMenuEntry currentEntry);

}
