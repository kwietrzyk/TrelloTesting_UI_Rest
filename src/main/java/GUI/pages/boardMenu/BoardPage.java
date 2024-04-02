package GUI.pages.boardMenu;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BoardPage {
    public SelenideElement settings = $("[aria-label = 'Pokaż menu']").as("settingsButton");
    public SelenideElement copyBoard = $(".js-copy-board").as("copyBoard");
    public SelenideElement closeBoard = $(".js-close-board").as("closeBoard");
}
