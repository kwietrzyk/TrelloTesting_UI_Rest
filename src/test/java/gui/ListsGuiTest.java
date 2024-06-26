package gui;

import net.bytebuddy.utility.RandomString;
import rest.endpointsobjects.Board;
import rest.endpointsobjects.ListTrello;
import rest.helpers.BoardManager;
import base.BaseTestGUI;
import common.enums.BoardListsNames;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ListsGuiTest extends BaseTestGUI {

    private final static String BOARD_NAME = RandomString.make();
    private final static String CARD_NAME = "TestCard";
    private Board board;

    @BeforeEach
    public void createTestBoard() {
        board = BoardManager.createBoard(BOARD_NAME);
    }

    @Test
    @DisplayName("TC: Update lists names")
    @Description("Board is created and verified by REST request")
    @Tag("gui")
    @Tag("list")
    public void shouldUpdateLists() {
        mainpage.openBoard(BOARD_NAME);
        for (BoardListsNames name : BoardListsNames.getDefaultNames()){
            boardPage.translateDefaultNameToEnglish(name);
        }
        boardPage.addNewList(BoardListsNames.ONHOLD.getEnglishLabel());
        assertBoardContainsEnglishListNames(board);
    }

    @Test
    @DisplayName("TC: Create card on list")
    @Description("Board is created and verified by REST request")
    @Tag("gui")
    @Tag("list")
    public void shouldCreateCardOnList() {
        final String list = BoardListsNames.getRandomDefaultName();
        mainpage.openBoard(BOARD_NAME)
                .addCardToList(list)
                .withCardName(CARD_NAME)
                .findCard(CARD_NAME)
                .shouldBe(visible);
        assertTrue(restHelper.isCardOnList(CARD_NAME, board.getList(list)));
    }

    @Test
    @DisplayName("TC: Add cards to list")
    @Description("Board is created and verified by REST request")
    @Tag("gui")
    @Tag("list")
    public void shouldAddMultipleCardsToList() {
        Map<String, Integer> expectedNumbers = createMapOfCardsOnDefaultLists();
        mainpage.openBoard(BOARD_NAME);
        for (Map.Entry<String, Integer> entry : expectedNumbers.entrySet()) {
            boardPage.addMultipleCardsToList(entry.getKey(), entry.getValue());
        }
        assertCardsOnLists(expectedNumbers);
    }

    private Map<String, Integer> createMapOfCardsOnDefaultLists() {
        Map<String, Integer> expectedNumbers = new HashMap<>();
        for (BoardListsNames name : BoardListsNames.getDefaultNames()) {
            expectedNumbers.put(name.getPolishLabel(), new Random().nextInt(1, 8));
        }
        return expectedNumbers;
    }

    @Step("Verification that board {board.boardDto.name} contains English lists names")
    private void assertBoardContainsEnglishListNames(Board board) {
        List<String> lists = restHelper.getAllListsNamesFromBoard(board);
        SoftAssertions softAssert = new SoftAssertions();
        for (BoardListsNames name : BoardListsNames.values()) {
            softAssert.assertThat(lists.contains(name.getEnglishLabel()));
        }
        softAssert.assertAll();
    }

    @Step("Verification that expected number of cards are on the lists")
    private void assertCardsOnLists(Map<String, Integer> expectedNumbers) {
        for (ListTrello list : board.getLists()) {
            int numberOfCards = restHelper.getAllCardsFromList(list).jsonPath().getList("").size();
            assertEquals(expectedNumbers.get(list.getListDto().getName()), numberOfCards);
        }
    }
}
