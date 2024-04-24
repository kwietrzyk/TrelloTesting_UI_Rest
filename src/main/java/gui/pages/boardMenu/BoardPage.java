package gui.pages.boardMenu;

import gui.pages.card.CardPage;
import gui.pages.list.ListPage;
import gui.pages.main.BasePage;
import com.codeborne.selenide.SelenideElement;
import common.enums.BoardListsNames;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BoardPage extends BasePage {

    public SelenideElement nameDisplay = $("[data-testid = 'board-name-display']").as("displayName");
    public SelenideElement nameInput = $("[data-testid = 'board-name-input']").as("nameInput");
    public SelenideElement settings = $("[aria-label = 'Pokaż menu']").as("settingsButton");

    public SelenideElement addNewListButton = $("button[data-testid='list-composer-button']").as("addNewListButton");
    public SelenideElement insertListName = $("[placeholder='Wprowadź tytuł listy']").as("listName");
    public SelenideElement submitListName = $("[data-testid='list-composer-add-list-button']").as("submitListName");

    public SelenideElement findListByName(String name) {
        return $x(String.format("//h2[contains(text(), '%s')]", name));
    }
    public SelenideElement findTextAreaByName(String name) {
        return $x(String.format("//textarea[contains(text(), '%s')]", name));
    }

    public BoardSettingsPage openBoardSettings() {
        settings.shouldBe(visible).click();
        return page(BoardSettingsPage.class);
    }

    public BoardPage changeName() {
        nameDisplay.shouldBe(editable).click();
        return this;
    }

    public BoardPage withValue(String newName) {
        executeJavaScript(String.format("arguments[0].value = '';", newName), nameInput);
        nameInput.setValue(newName);
        return this;
    }

    @Step("Translate lists names to English for list {name}")
    public BoardPage translateDefaultNameToEnglish(BoardListsNames name) {
        findListByName(name.getPolishLabel()).shouldBe(visible).click();
        SelenideElement textArea = findTextAreaByName(name.getPolishLabel());
        executeJavaScript("arguments[0].value = '';", textArea);
        textArea.shouldBe(visible).setValue(name.getEnglishLabel());
        return this;
    }

    @Step("Add new list")
    public BoardPage addNewList(String name) {
        addNewListButton.click();
        insertListName.setValue(name).pressEnter();
        return this;
    }

    @Step("Add {numberOfCards} cards to list {listName}")
    public BoardPage addMultipleCardsToList(String listName, int numberOfCards) {
        addCardToList(listName);
        for (int i = 0; i < numberOfCards; i++) {
            String cardName = "Card " + (i+1);
            page(ListPage.class).withCardName(cardName)
                    .findCard(cardName)
                    .shouldBe(visible);
        }
        sleep(1000);
        return this;
    }

    @Step("Add card to list {listName}")
    public ListPage addCardToList(String listName) {
        SelenideElement listBlock = findListBlock(listName);
        return page(ListPage.class).addCard(listBlock);
    }

    @Step("Move card {cardName} to list {dstListName}")
    public BoardPage moveCardToList(String cardName, String dstListName) {
        SelenideElement card = findCard(cardName).shouldBe(visible);
        SelenideElement dstList = findListBlock(dstListName).shouldBe(visible);
        actions().dragAndDrop(card, dstList).perform();
        sleep(500);
        return this;
    }

    @Step("Go to card")
    public CardPage goToCard(String name) {
        findCard(name).shouldBe(visible).click();
        return page(CardPage.class);
    }

    public SelenideElement findCard(String cardName) {
        return $(byText(cardName));
    }

    private SelenideElement findListBlock(String listName) {
        return $(byText(listName)).parent().parent().parent();
    }



}
