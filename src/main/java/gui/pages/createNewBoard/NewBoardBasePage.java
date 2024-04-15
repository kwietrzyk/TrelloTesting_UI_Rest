package gui.pages.createNewBoard;

import gui.pages.main.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewBoardBasePage extends BasePage {

    public SelenideElement title = $("input[data-testid='create-board-title-input']").as("title");
    public SelenideElement selectVisibility = $(".t3Ou6F9HZxP3VK.css-ufz0vj-control").as("selectVisibility");
    public SelenideElement submit = $("button[data-testId = create-board-submit-button]").as("submit");
}
