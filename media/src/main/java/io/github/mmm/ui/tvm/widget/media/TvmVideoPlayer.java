/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.ui.tvm.widget.media;

import org.teavm.jso.dom.html.HTMLVideoElement;

import io.github.mmm.ui.api.widget.img.UiAbstractImage;
import io.github.mmm.ui.api.widget.img.UiImage;
import io.github.mmm.ui.api.widget.media.UiVideoPlayer;

/**
 * Implementation of {@link UiVideoPlayer} using TeaVM.
 *
 * @since 1.0.0
 */
public class TvmVideoPlayer extends TvmMediaWidget<HTMLVideoElement> implements UiVideoPlayer {

  private UiAbstractImage image;

  /**
   * The constructor.
   */
  public TvmVideoPlayer() {

    super(newVideo());
  }

  @Override
  public UiAbstractImage getImage() {

    return this.image;
  }

  @Override
  public void setImage(UiAbstractImage image) {

    if (image instanceof UiImage) {
      this.widget.setPoster(((UiImage) image).getUrl());
    } else {
      // handle UiIcon?
      this.widget.setPoster("");
    }
    this.image = image;
  }

}
