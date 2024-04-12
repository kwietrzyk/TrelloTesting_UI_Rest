package tests.gui;

import net.bytebuddy.utility.RandomString;
import tests.base.BaseTestGUI;
import com.codeborne.selenide.SelenideElement;
import enums.BoardListsNames;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.*;
import java.util.List;
import static com.codeborne.selenide.Condition.visible;
import static helpers.RestHelper.*;

public class ListsGuiTest extends BaseTestGUI {

    private String boardName = RandomString.make();
    private String boardId;

    @BeforeEach
    public void createTestBoard() {
        boardId = createNewBoardAndFetchId(boardName);
    }

    @AfterEach
    public void deleteTestBoard() {
        deleteBoard(boardId);
    }

    @Test
    @DisplayName("TC: Update lists names")
    @Description("Board is created and verified by REST request")
    @Tag("gui")
    @Tag("list")
    public void shouldUpdateExistingBoard() {
        mainpage.openBoard(boardName);
        for (BoardListsNames name : BoardListsNames.getDefaultNames()){
            boardPage.translateDefaultNameToEnglish(name);
        }
        boardPage.addNewList(BoardListsNames.ONHOLD.getEnglishLabel());
        assertThatBoardContainsEnglishListNames(boardId);
    }

//    @Test
//    @DisplayName("TC: Update lists content")
//    @Description("Board is created, verified and deleted by REST actions to speed up stable actions")
//    @Tag("gui")
//    @Tag("list")
//    public void shouldUpdateListContent() {
//        fillCurrentListsWithCards();
//    }

    // Test: moveListToDifferentPosition - drag & drop

    private void fillCurrentListsWithCards() {
        List<Object> allLists = getAllListsFromBoard(boardId).jsonPath().getList("");
        allLists.stream().forEach(System.out::println); // brac stad ID do znalezienia lokatora, np [data-list-id='660d57f18c23fe64f0fe962a'] [data-testid=list-add-card-button]
        mainpage.findBoardWithName(boardName).click();
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
        for (BoardListsNames name : BoardListsNames.values()) {
            softAssert.assertThat(lists.contains(name.getEnglishLabel()));
        }
        softAssert.assertAll();
    }
}
