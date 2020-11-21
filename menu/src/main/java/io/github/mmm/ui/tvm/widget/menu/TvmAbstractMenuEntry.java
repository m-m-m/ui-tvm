/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.menu;

import io.github.mmm.ui.api.widget.composite.UiComposite;
import io.github.mmm.ui.api.widget.menu.UiAbstractMenuEntry;
import io.github.mmm.ui.api.widget.menu.UiAdvancedMenu;

/**
 * Interface for all TeaVM based implementations of {@link UiAbstractMenuEntry}.
 *
 * @since 1.0.0
 */
public interface TvmAbstractMenuEntry extends UiAbstractMenuEntry {

  /**
   * @return {@code true} if the current (selected or highlighted) entry within a menu, {@code false} otherwise.
   */
  boolean isCurrent();

  /**
   * @param selected the new value of {@link #isCurrent() current}. When {@link #isCurrent() current} is set to
   *        {@code true} on a {@link TvmMenu}, it will expand, and if set to {@code false} it will collapse.
   *        <b>ATTENTION:</b> Before setting {@link #isCurrent() current} to {@code true} you need to first set
   *        {@link #isCurrent() current} of a potentially previous entry to {@code false}.
   */
  void setCurrent(boolean selected);

  /**
   * @return {@code true} if this entry is the currently active (focused) entry, {@code false} otherwise. There can only
   *         be one active entry at a time for the entire menu-bar tree.
   */
  boolean isActive();

  /**
   * @param active the new value of {@link #isActive() active}. Will not automatically update a potential previously
   *        active entry.
   */
  void setActive(boolean active);

  /**
   * @return {@code true} if expanded (in case of sub-menu), {@code false} otherwise.
   */
  default boolean isExpanded() {

    return false;
  }

  /**
   * @param expanded the new value of {@link #isExpanded() expanded}. Will have no effect if no (sub-)menu.
   */
  default void setExpanded(boolean expanded) {

  }

  /**
   * @return the parent {@link TvmMenu} or {@code null} if this entry does not have a parent (menu).
   */
  @SuppressWarnings("unchecked")
  default TvmAbstractMenu<UiAdvancedMenu> getParentMenu() {

    UiComposite<?> parent = getParent();
    return (TvmAbstractMenu<UiAdvancedMenu>) parent;
  }

}
