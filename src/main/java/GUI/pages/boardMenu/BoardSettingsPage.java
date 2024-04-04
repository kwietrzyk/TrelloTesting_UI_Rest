package GUI.pages.boardMenu;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BoardSettingsPage {

    public SelenideElement changeBackground = $(".js-change-background").as("changeBackground");
    public SelenideElement copyBoard = $(".js-copy-board").as("copyBoard");
    public SelenideElement closeBoard = $(".js-close-board").as("closeBoard");
}
