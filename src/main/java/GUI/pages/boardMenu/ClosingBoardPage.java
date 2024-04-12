package GUI.pages.boardMenu;

import GUI.pages.createNewBoard.NewBoardBasePage;
import GUI.pages.main.BasePage;
import GUI.pages.main.MainPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ClosingBoardPage extends BasePage {

    public SelenideElement closingSubmitButton = $("[data-testid=close-board-confirm-button]").as("closingSubmitButton");
    public SelenideElement reopenClosedBoard = $("[data-testid=workspace-chooser-trigger-button]").as("reopenClosedBoard");
    public SelenideElement deletePermanently = $("[data-testid=close-board-delete-board-button]").as("deletePermanently");
    public SelenideElement submitPermanentDelete = $("[data-testid=close-board-delete-board-confirm-button]").as("submitPermanentDelete");

    public MainPage deletePermanently() {
        closingSubmitButton.click();
        deletePermanently.click();
        submitPermanentDelete.click();
        return page(MainPage.class);
    }
}
