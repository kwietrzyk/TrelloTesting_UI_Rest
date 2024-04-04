package tests.gui;

import base.BaseTestGUI;
import com.codeborne.selenide.SelenideElement;
import enums.BoardLists;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.*;
import java.util.List;
import static com.codeborne.selenide.Condition.visible;
import static helpers.RestHelper.*;

public class ListsGuiTest extends BaseTestGUI {

    private String boardId;

    @BeforeEach
    public void createTestBoard() {
        boardId = createNewBoardAndFetchId(MY_NEW_TABLE);
    }

    @AfterEach
    public void deleteTestBoard() {
        deleteBoard(boardId);
    }

    @Test
    @DisplayName("TC4: Update lists names")
    @Description("Board is created, verified and deleted by REST actions to speed up stable actions")
    @Tag("gui")
    @Tag("list")
    public void shouldUpdateExistingBoard() {
        changePolishNamesToEnglish();
        addNewList(BoardLists.ONHOLD.getEnglishLabel());
        assertThatBoardContainsEnglishListNames(boardId);
    }

    @Test
    @DisplayName("TC5: Update lists content")
    @Description("Board is created, verified and deleted by REST actions to speed up stable actions")
    @Tag("gui")
    @Tag("list")
    public void shouldUpdateListContent() {
        fillCurrentListsWithCards();
    }

    private void fillCurrentListsWithCards() {
        List<Object> allLists = getAllListsFromBoard(boardId).jsonPath().getList("");
        allLists.stream().forEach(System.out::println); // brac stad ID do znalezienia lokatora, np [data-list-id='660d57f18c23fe64f0fe962a'] [data-testid=list-add-card-button]
        mainpage.myNewTableBoard.click();
    }

    @Step("Translate lists names to English")
    private void changePolishNamesToEnglish() {
        mainpage.myNewTableBoard.click();
        changeListNameToEnglish(BoardLists.TODO);
        changeListNameToEnglish(BoardLists.ONGOING);
        changeListNameToEnglish(BoardLists.DONE);
        sleep(1000);
    }

    @Step("Translate {name} to English")
    private void changeListNameToEnglish(BoardLists name) {
        String polishName = name.getPolishLabel();
        String englishName = name.getEnglishLabel();

        SelenideElement list = boardPage.findListByName(polishName);
        list.shouldBe(visible).click();
        SelenideElement textArea = boardPage.findTextAreaByName(polishName);
        executeJavaScript("arguments[0].value = '';", textArea);
        textArea.shouldBe(visible).setValue(englishName);
    }

    @Step("Add new list")
    private void addNewList(String name) {
        boardPage.addNewListButton.click();
        boardPage.insertListName.setValue(name).pressEnter();
    }

    private void removeList(String name) {

    }

    private void addNewCard(String name) {

    }

    private void removeCard(String name) {

    }

    @Step("Verification that board {boardId} contains English lists names")
    private void assertThatBoardContainsEnglishListNames(String boardId) {
        List<String> lists = getAllListsNames(boardId);
        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(lists.contains(BoardLists.TODO.getEnglishLabel()));
        softAssert.assertThat(lists.contains(BoardLists.ONGOING.getEnglishLabel()));
        softAssert.assertThat(lists.contains(BoardLists.DONE.getEnglishLabel()));
        softAssert.assertThat(lists.contains(BoardLists.ONHOLD.getEnglishLabel()));
        softAssert.assertAll();
    }
}
