/**
 * Copyright (C) 2018 toop.eu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.toop.demoui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import eu.toop.demoui.endpoints.DemoUIToopInterfaceDC;
import eu.toop.demoui.endpoints.DemoUIToopInterfaceDP;
import eu.toop.demoui.view.*;
import eu.toop.iface.ToopInterfaceManager;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@SuppressWarnings("javadoc")
@Theme("DCUITheme")
@PreserveOnRefresh
public class DCUI extends UI {

  private Navigator navigator;

  @Override
  protected void init(final VaadinRequest vaadinRequest) {
    getPage().setTitle("TOOP Demo User Interface");

    ToopInterfaceManager.setInterfaceDC(new DemoUIToopInterfaceDC(this));
    ToopInterfaceManager.setInterfaceDP(new DemoUIToopInterfaceDP(this));

    navigator = new Navigator(this, this);
    navigator.addView("", new HomeView ());
    navigator.addView(StartView.class.getName(), new HomeView());
  }

  @WebServlet(urlPatterns = { "/ui/*", "/VAADIN/*" }, asyncSupported = true)
  @VaadinServletConfiguration(ui = DCUI.class, productionMode = false)
  public static class DCUIServlet extends VaadinServlet {
  }
}
