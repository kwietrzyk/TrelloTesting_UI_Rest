package tests.gui;

import net.bytebuddy.utility.RandomString;
import rest.endpointsobjects.Board;
import rest.helpers.BoardManager;
import tests.base.BaseTestGUI;
import common.enums.BoardListsNames;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

public class ListsGuiTest extends BaseTestGUI {

    private String boardName = RandomString.make();
    private Board board;

    @BeforeEach
    public void createTestBoard() {
        board = BoardManager.createBoard(boardName);
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
        assertThatBoardContainsEnglishListNames(board);
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
        List<Object> allLists = restHelper.getAllListsFromBoard(board.getBoardDto().getId()).jsonPath().getList("");
        allLists.stream().forEach(System.out::println); // brac stad ID do znalezienia lokatora, np [data-list-id='660d57f18c23fe64f0fe962a'] [data-testid=list-add-card-button]
        mainpage.findBoardWithName(boardName).click();
    }

    private void removeList(String name) {

    }

    private void addNewCard(String name) {

    }

    private void removeCard(String name) {

    }

    @Step("Verification that board {board.boardDto.name} contains English lists names")
    private void assertThatBoardContainsEnglishListNames(Board board) {
        List<String> lists = restHelper.getAllListsNames(board);
        SoftAssertions softAssert = new SoftAssertions();
        for (BoardListsNames name : BoardListsNames.values()) {
            softAssert.assertThat(lists.contains(name.getEnglishLabel()));
        }
        softAssert.assertAll();
    }
}
