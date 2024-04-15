package gui.pages.boardMenu;

import gui.pages.main.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BoardSettingsPage extends BasePage {

    public SelenideElement changeBackground = $(".js-change-background").as("changeBackground");
    public SelenideElement copyBoard = $(".js-copy-board").as("copyBoard");
    public SelenideElement closeBoard = $(".js-close-board").as("closeBoard");

    public ClosingBoardPage closeBoard() {
        closeBoard.scrollTo().click();
        return page(ClosingBoardPage.class);
    }

    public BackgroundSettingsPage changeBackground() {
        changeBackground.shouldBe(visible).click();
        return page(BackgroundSettingsPage.class);
    }
}
