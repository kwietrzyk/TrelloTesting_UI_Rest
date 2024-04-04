package GUI.pages.boardMenu;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BoardPage {

    public SelenideElement nameDisplay = $("[data-testid = 'board-name-display']").as("nameDisplay");
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
}
