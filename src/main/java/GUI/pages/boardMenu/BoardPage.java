package GUI.pages.boardMenu;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BoardPage {
    public SelenideElement settings = $("[aria-label = 'Pokaż menu']");
    public SelenideElement copyBoard = $(".js-copy-board");
    public SelenideElement closeBoard = $(".js-close-board");
}
