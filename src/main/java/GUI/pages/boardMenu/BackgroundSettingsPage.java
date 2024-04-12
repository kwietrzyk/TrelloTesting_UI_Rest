package GUI.pages.boardMenu;

import GUI.pages.main.BasePage;
import com.codeborne.selenide.SelenideElement;
import enums.BoardBackgroundColors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BackgroundSettingsPage extends BasePage {

    public SelenideElement photos = $(".board-backgrounds-photos-tile").as("photos");
    public SelenideElement colors = $(".board-backgrounds-gradients-tile").as("photos");
    public SelenideElement backgroundBox = $(".board-background-select", 10).as("backgroundBox");
    public SelenideElement photoMenuClosingButton = $(".board-menu-header-close-button").as("closingButton");

    public SelenideElement chooseColorElement(BoardBackgroundColors color) {
        return $(String.format("[data-testid='board-background-select-color-%s']", color.getLabel())).as("color");
    }

    public BackgroundSettingsPage toImage() {
        photos.shouldBe(visible).click();
        backgroundBox.scrollTo().click();
        return this;
    }

    public BackgroundSettingsPage toColor(BoardBackgroundColors color) {
        colors.shouldBe(visible).click();
        chooseColorElement(color).click();
        return this;
    }

    public BoardPage closeBackgroundSettingPage() {
        photoMenuClosingButton.shouldBe(visible).click();
        refresh();
        sleep(1500);
        return page(BoardPage.class);
    }
}
