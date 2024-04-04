package GUI.pages.main;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import configuration.TestConfiguration;

public class MainPage {

    public SelenideElement createNewTableButton = $(".board-tile.mod-add").as("createNewTableButton");
    public SelenideElement myNewTableBoard = $("div[title='" + TestConfiguration.MY_NEW_TABLE + "']").as("myNewTableBoard");
    public SelenideElement goToMainPage = $("[aria-label='Powrót do strony głównej']").as("goToMainPage");
}
