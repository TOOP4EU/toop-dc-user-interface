package eu.toop.demoui.pages;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomLayout;
import eu.toop.demoui.view.HomeView;

public class ChooseAuthenticationMethodPage extends CustomLayout {

  public ChooseAuthenticationMethodPage (HomeView view) {
    super ("ChooseAuthenticationMethodPage");

    setHeight ("100%");

    Button nextButton = new Button ("Next");
    addComponent (nextButton, "nextButton");
    nextButton.addClickListener(new Button.ClickListener() {
      public void buttonClick(Button.ClickEvent event) {
        nextButton.setCaption ("clicked");
        view.setCurrentPage (new ChooseCountryPage (view));
      }
    });
  }
}