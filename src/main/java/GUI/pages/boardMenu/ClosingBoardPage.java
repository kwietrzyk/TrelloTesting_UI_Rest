package GUI.pages.boardMenu;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ClosingBoardPage {
    public SelenideElement closingSubmitButton = $("[data-testid=close-board-confirm-button]");
    public SelenideElement reopenClosedBoard = $("[data-testid=workspace-chooser-trigger-button]");
    public SelenideElement deletePermanently = $("[data-testid=close-board-delete-board-button]");
    public SelenideElement submitPermanentDelete = $("[data-testid=close-board-delete-board-confirm-button]");

}
