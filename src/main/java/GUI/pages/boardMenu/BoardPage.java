package GUI.pages.boardMenu;

import GUI.pages.main.BasePage;
import GUI.pages.main.MainPage;
import com.codeborne.selenide.SelenideElement;
import enums.BoardListsNames;
import io.qameta.allure.Step;
import io.qameta.allure.model.Label;

import static com.codeborne.selenide.Condition.editable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BoardPage extends BasePage {

    public SelenideElement nameDisplay = $("[data-testid = 'board-name-display']").as("displayName");
    public SelenideElement nameInput = $("[data-testid = 'board-name-input']").as("nameInput");
    public SelenideElement settings = $("[aria-label = 'Pokaż menu']").as("settingsButton");
    public SelenideElement addNewListButton = $("button[data-testid='list-composer-button']").as("addNewListButton");
    public SelenideElement insertListName = $("[placeholder='Wprowadź tytuł listy']").as("listName");
    public SelenideElement submitListName = $("[data-testid='list-composer-add-list-button']").as("submitListName");
    public SelenideElement addCardButton = $("[data-testid=list-add-card-button]").as("addCardButton");

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
}
