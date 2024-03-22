package GUI.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public SelenideElement createNewTableButton = $(".board-tile.mod-add");

    public SelenideElement myNewTableBoard = $("div[title='MyNewTable']");
    public SelenideElement allBoards = $("[data-testid=open-boards-link]");
}
