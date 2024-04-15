package gui.pages.createNewBoard;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class NewBoardPage extends NewBoardBasePage {

    public SelenideElement startWithTemplate = $("button[data-testId = create-from-template-button]").as("startWithTemplate");

    public NewBoardPage withName(String name) {
        title.shouldBe(visible).setValue(name);
        return this;
    }

    public NewBoardPage submit() {
        submit.click();
        sleep(2000);
        return this;
    }
}
