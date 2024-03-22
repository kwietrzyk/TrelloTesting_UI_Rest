package GUI.pages.createNewBoard;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewBoardBasePage {
    public SelenideElement title = $(".nch-textfield__input");
    public SelenideElement selectVisibility = $(".t3Ou6F9HZxP3VK.css-ufz0vj-control");
    public SelenideElement submit = $("button[data-testId = create-board-submit-button]");
}
