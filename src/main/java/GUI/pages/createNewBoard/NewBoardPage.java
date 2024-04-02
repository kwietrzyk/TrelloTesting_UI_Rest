package GUI.pages.createNewBoard;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewBoardPage extends NewBoardBasePage {
    public SelenideElement startWithTemplate = $("button[data-testId = create-from-template-button]").as("startWithTemplate");

}
