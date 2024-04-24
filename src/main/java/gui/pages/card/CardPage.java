package gui.pages.card;

import com.codeborne.selenide.SelenideElement;
import gui.pages.boardMenu.BoardPage;
import gui.pages.main.BasePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardPage extends BasePage {

    SelenideElement name = $(".mod-card-back-title").as("Card Name");
    SelenideElement closeButton = $(".dialog-close-button").as("Close Button");
    SelenideElement archiveButton = $("[title = 'Zarchiwizuj']").as("Archive card");
    SelenideElement deleteButton = $("[title = 'Usuń']").as("Delete card");
    SelenideElement submitDeleteButton = $("[value = 'Skasuj']").as("Submit delete");
    SelenideElement descriptionArea = $("#ak-editor-textarea").as("Description area");
    SelenideElement saveDescButton = $(byText("Zapisz")).as("Save button");
    SelenideElement addComment = $("[data-testid='card-back-new-comment-input-skeleton']").as("Comment");
    SelenideElement commentArea = $(byText("Napisz komentarz...")).parent().as("Comment text area");
    SelenideElement saveCommentButton = $("[data-testid='card-back-comment-save-button']").as("Save button");

    public CardPage changeCardNameTo(String newName) {
        name.shouldBe(visible).click();
        name.clear();
        name.setValue(newName).pressEnter();
        return this;
    }

    public BoardPage deleteCard() {
        archiveCard();
        deleteButton.click();
        submitDeleteButton.click();
        return page(BoardPage.class);
    }

    public CardPage archiveCard() {
        archiveButton.scrollTo().shouldBe(visible).click();
        return this;
    }

    public CardPage addDescription(String description) {
        descriptionArea.setValue(description);
        saveDescButton.click();
        return this;
    }

    public CardPage addComment(String text) {
        addComment.click();
        commentArea.shouldBe(visible).setValue(text);
        saveCommentButton.click();
        return this;
    }

    public BoardPage closeCardWindow() {
        closeButton.click();
        return page(BoardPage.class);
    }

}
