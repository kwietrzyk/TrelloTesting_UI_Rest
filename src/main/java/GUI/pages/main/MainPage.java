package GUI.pages.main;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    public SelenideElement createNewTableButton = $(".board-tile.mod-add").as("createNewTableButton");
    public SelenideElement myNewTableBoard = $("div[title='MyNewTable']").as("myNewTableBoard");
    public SelenideElement allBoards = $("[data-testid=open-boards-link]").as("allBoards");
    public SelenideElement boards = $(".oTmCsFlPhDLGz2.AQ0dAIzWIJDFUU").as("boards");
}
