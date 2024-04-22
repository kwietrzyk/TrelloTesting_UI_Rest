package gui.pages;

import com.codeborne.selenide.SelenideElement;
import gui.pages.boardMenu.BoardPage;
import gui.pages.main.BasePage;
import io.qameta.allure.Step;;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ListPage extends BasePage {

    public SelenideElement newCardNameInput = $("[data-testid=list-card-composer-textarea]").as("cardNameInput");
    public SelenideElement submitCardName = $("[data-testid='list-card-composer-add-card-button']").as("submitCardName");

    public ListPage addCard(SelenideElement listBlock) {
        listBlock.$("[data-testid=list-add-card-button]").click();
        return this;
    }

    public BoardPage withCardName(String cardName) {
        newCardNameInput.setValue(cardName).pressEnter();
        return page(BoardPage.class);
    }
}
