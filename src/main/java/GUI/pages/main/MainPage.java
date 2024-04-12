package GUI.pages.main;

import GUI.pages.boardMenu.BoardPage;
import GUI.pages.createNewBoard.NewBoardPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import configuration.TestConfiguration;

public class MainPage extends BasePage {

    public SelenideElement createNewTableButton = $(".board-tile.mod-add").as("createNewTableButton");

    public NewBoardPage createNewBoard() {
        createNewTableButton.shouldBe(visible).click();
        return page(NewBoardPage.class);
    }

    public BoardPage openBoard(String name) {
        findBoardWithName(name).shouldBe(visible).click();
        return page(BoardPage.class);
    }

    public SelenideElement findBoardWithName(String name) {
        return $("div[title='" + name + "']").as("board under test");
    }
}
