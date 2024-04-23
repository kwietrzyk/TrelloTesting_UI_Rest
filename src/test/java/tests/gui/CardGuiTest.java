package tests.gui;

import common.enums.BoardListsNames;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import net.bytebuddy.utility.RandomString;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import rest.endpointsobjects.Board;
import rest.endpointsobjects.Card;
import rest.endpointsobjects.ListTrello;
import rest.helpers.BoardManager;
import tests.base.BaseTestGUI;

import java.util.List;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardGuiTest extends BaseTestGUI {

    private Board board;
    private final static String BOARD_NAME = RandomString.make();
    private final static String CARD_NAME = "TestCard";
    private ListTrello toDoList;
    private ListTrello ongoingList;
    private ListTrello doneList;
    private final int numberOfDefaultCards = 2;

    @BeforeEach
    public void createTestBoard() {
        board = BoardManager.createBoard(BOARD_NAME);
        toDoList = board.getList(BoardListsNames.TODO.getPolishLabel());
        ongoingList = board.getList(BoardListsNames.ONGOING.getPolishLabel());
        doneList = board.getList(BoardListsNames.DONE.getPolishLabel());
        for (ListTrello list : board.getLists()) {
            list.createMultipleCards(numberOfDefaultCards);
        }
    }

    @Test
    @DisplayName("TC: Move card to different list")
    @Description("Board is created and verified by REST request")
    @Tag("gui")
    @Tag("list")
    public void shouldMoveCardToDifferentList() {
        List<String> toDoCards = toDoList.getCardsNames();
        String destinationList = ongoingList.getListDto().name;
        String destinationList2 = doneList.getListDto().name;
        mainpage.openBoard(BOARD_NAME)
                .moveCardToList(toDoCards.get(0), destinationList)
                .moveCardToList(toDoCards.get(1), destinationList2);
        assertTrue(restHelper.isCardOnList(toDoCards.get(0), board.getList(destinationList)));
        assertTrue(restHelper.isCardOnList(toDoCards.get(1), board.getList(destinationList2)));
    }

    @Test
    @DisplayName("TC: Change card name")
    @Tag("gui")
    @Tag("card")
    public void shouldChangeCardName() {
        String cardToRename = board.getList(BoardListsNames.getRandomDefaultName()).getCardsNames().get(0);
        mainpage.openBoard(BOARD_NAME)
                .goToCard(cardToRename)
                .changeCardNameTo(CARD_NAME)
                .closeCardWindow()
                .findCard(CARD_NAME)
                .shouldBe(visible);
    }

    @Test
    @DisplayName("TC: Delete cards")
    @Tag("gui")
    @Tag("card")
    public void shouldDeleteCards() {
        String cardToDelete = board.getList(BoardListsNames.getRandomDefaultName()).getCardsNames().get(1);
        mainpage.openBoard(BOARD_NAME)
                .goToCard(cardToDelete)
                .deleteCard()
                .findCard(cardToDelete)
                .shouldNot(exist);
    }

    @Test
    @DisplayName("TC: Add card description")
    @Tag("gui")
    @Tag("card")
    public void shouldAddDescriptionToCard() {
        Card cardToUpdate = board.getList(BoardListsNames.getRandomDefaultName()).getCards().get(1);
        String description = RandomString.make(100);
        mainpage.openBoard(BOARD_NAME)
                .goToCard(cardToUpdate.getCardDto().name)
                .addDescription(description)
                .closeCardWindow();
        restHelper.getCard(cardToUpdate.getCardDto().id).then()
                .body("desc", equalTo(description));
    }

    @Test
    @DisplayName("TC: Add comments to card")
    @Tag("gui")
    @Tag("card")
    public void shouldAddCommentsToCard() {
        Card cardToUpdate = board.getList(BoardListsNames.getRandomDefaultName()).getCards().get(1);
        mainpage.openBoard(BOARD_NAME)
                .goToCard(cardToUpdate.getCardDto().name)
                .addComment(RandomString.make(20))
                .addComment(RandomString.make(50))
                .addComment(RandomString.make(100))
                .closeCardWindow();
        restHelper.getCard(cardToUpdate.getCardDto().id).then()
                .body("badges.comments", is(3));
    }
}
