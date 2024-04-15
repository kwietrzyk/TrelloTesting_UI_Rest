package gui.pages.createNewBoard;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TemplatesPage extends NewBoardBasePage {

    public SelenideElement menu = $("[role=menu]").as("menu");
    public SelenideElement keepCards = $(".CpyGgjAzUkQDno > .VhaiZhQslxcjfC").as("keepCards");
}
