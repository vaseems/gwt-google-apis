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
package com.google.gwt.visualization.client.visualizations;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.CommonOptions;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.SelectionHelper;
import com.google.gwt.visualization.client.VisualizationWidget;
import com.google.gwt.visualization.client.events.SelectHandler;

/**
 * 
 * Classic Pie Chart visualization.   May be loaded by calling: 
 * <code>
 * google.load("visualization", "1", {packages:["piechart"]});
 * </code>
 * 
 * 
 * @see <a href= 
 *      "http://code.google.com/apis/visualization/documentation/gallery/piechart.html"
 *      >Pie Chart Visualization Reference</a>
 */
public class PieChart extends Visualization<PieChart.Options> 
implements Selectable {
  /**
   * Options for drawing the pie chart.
   * 
   */
  public static class Options extends CommonOptions {
    
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }

    public final native void set3D(boolean enable3D) /*-{
      this.is3D = enable3D;
    }-*/;
  }
  
  /**
   * Specifies where to put the legend in the visualization.
   */
  public static class PieLegendPosition extends LegendPosition {
    public static final PieLegendPosition LABEL = 
      new PieLegendPosition("label");
    
    protected PieLegendPosition(String name) {
      super(name);
    }
  }

  public static native PieChart create(Element parent) /*-{
    return new $wnd.google.visualization.PieChart(parent);
  }-*/;
  
  public static VisualizationWidget<PieChart, Options> createWidget() {
    Element div = DOM.createDiv();
    PieChart viz = create(div);
    return new VisualizationWidget<PieChart, Options>(div, viz);
  }
  
  public static VisualizationWidget<PieChart, Options> 
  createWidget(AbstractDataTable data, Options options) {
    Element div = DOM.createDiv();
    PieChart viz = create(div);
    return new VisualizationWidget<PieChart, Options>(div, viz, data, 
        options);
  }

  protected PieChart() {
  }

  public final void addSelectHandler(SelectHandler handler) {
    SelectionHelper.addSelectHandler(this, handler);
  }

  public final Selection getSelection() {
    return SelectionHelper.getSelection(this);
  }

  public final void setSelection(Selection sel) {
    SelectionHelper.setSelection(this, sel);
  }
}