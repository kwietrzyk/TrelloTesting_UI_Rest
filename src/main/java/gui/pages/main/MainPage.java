package gui.pages.main;

import gui.pages.boardMenu.BoardPage;
import gui.pages.createNewBoard.NewBoardPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

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
