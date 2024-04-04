package GUI.pages.boardMenu;

import com.codeborne.selenide.SelenideElement;
import enums.BoardBackgroundColors;

import static com.codeborne.selenide.Selenide.$;

public class BackgroundSettingsPage {

    public SelenideElement photos = $(".board-backgrounds-photos-tile").as("photos");
    public SelenideElement colors = $(".board-backgrounds-gradients-tile").as("photos");
    public SelenideElement backgroundBox = $(".board-background-select", 10).as("backgroundBox");
    public SelenideElement photoMenuClosingButton = $(".board-menu-header-close-button").as("closingButton");

    public SelenideElement chooseColorElement(BoardBackgroundColors color) {
        return $(String.format("[data-testid='board-background-select-color-%s']", color.getLabel())).as("color");
    }
}
