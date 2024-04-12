package GUI.pages.main;

import com.codeborne.selenide.SelenideElement;
import com.mifmif.common.regex.Main;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class BasePage {
    public SelenideElement goToMainPage = $("[aria-label='Powrót do strony głównej']").as("goToMainPage");

    public MainPage goToMainPage() {
        goToMainPage.click();
        return page(MainPage.class);
    }
}
