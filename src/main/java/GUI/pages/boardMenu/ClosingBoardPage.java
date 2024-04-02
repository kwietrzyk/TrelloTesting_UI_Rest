package GUI.pages.boardMenu;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ClosingBoardPage {
    public SelenideElement closingSubmitButton = $("[data-testid=close-board-confirm-button]").as("closingSubmitButton");
    public SelenideElement reopenClosedBoard = $("[data-testid=workspace-chooser-trigger-button]").as("reopenClosedBoard");
    public SelenideElement deletePermanently = $("[data-testid=close-board-delete-board-button]").as("deletePermanently");
    public SelenideElement submitPermanentDelete = $("[data-testid=close-board-delete-board-confirm-button]").as("submitPermanentDelete");

}
