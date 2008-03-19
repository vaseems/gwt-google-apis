/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.event.CopyrightListener;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.CopyrightCollectionImpl;
import com.google.gwt.maps.client.impl.JsUtil;

/**
 * Manages copyright messages displayed on maps of custom map type. If you don't
 * implement custom map types, then you don't need to use this class. A
 * copyright collection contains information about which copyright to display
 * for which region on the map at which zoom level. This is important for map
 * types that display heterogeneous data such as the satellite map type.
 * 
 * @see TileLayer#TileLayer(CopyrightCollection, int, int)
 */
public final class CopyrightCollection {

  static CopyrightCollection createPeer(JavaScriptObject jsoPeer) {
    return new CopyrightCollection(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  /**
   * Creates an empty copyright collection.
   */
  public CopyrightCollection() {
    jsoPeer = CopyrightCollectionImpl.impl.construct();
  }

  /**
   * Creates an empty copyright collection with the given prefix. Each copyright
   * produced from this collection will have the given prefix.
   * 
   * @param prefix the prefix for every copyright
   */
  public CopyrightCollection(String prefix) {
    jsoPeer = CopyrightCollectionImpl.impl.construct(prefix);
  }

  private CopyrightCollection(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Adds a copyright to this collection.
   * 
   * @param copyright the copyright to be added
   */
  public void addCopyright(Copyright copyright) {
    CopyrightCollectionImpl.impl.addCopyright(jsoPeer, copyright);
  }

  // TODO: Implement copyright listener for 'newcopyright' event. See issue 95.
  public void addCopyrightListener(CopyrightListener listener) {
    throw new UnsupportedOperationException();
  }

  public void clearCopyrightListeners() {
  }

  /**
   * Gets the copyright notice for the given viewport.
   * 
   * @param bounds the viewport's geographical bounds
   * @param zoomLevel the viewport's zoom level
   * @return the copyright notice for the given viewport
   */
  public String getCopyrightNotice(LatLngBounds bounds, int zoomLevel) {
    return CopyrightCollectionImpl.impl.getCopyrightNotice(jsoPeer, bounds,
        zoomLevel).toString();
  }

  /**
   * Returns the copyrights that should be displayed for the given viewport.
   * 
   * @param bounds the viewport's geographical bounds
   * @param zoomLevel the viewport's zoom level
   * @return the copyrights for the given viewport
   */
  public String[] getCopyrights(LatLngBounds bounds, int zoomLevel) {
    JSList<String> list = CopyrightCollectionImpl.impl.getCopyrights(jsoPeer, bounds,
        zoomLevel);
    String[] copyrights = new String[list.size()];
    JsUtil.toArray(list, copyrights);
    return copyrights;
  }

  public void removeCopyrightListener(CopyrightListener listener) {
  }
}
