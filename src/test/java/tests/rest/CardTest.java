package tests.rest;

import common.enums.BoardListsNames;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import rest.endpointsobjects.Board;
import rest.endpointsobjects.Card;
import rest.endpointsobjects.ListTrello;
import rest.helpers.BoardManager;
import tests.base.BaseTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardTest extends BaseTest {

    // In this class actions are done on endpoint objects and verification is done by restHelper methods

    private Board board;
    private ListTrello toDoList;
    private ListTrello ongoingList;
    private ListTrello doneList;
    private final int numberOfDefaultCards = 2;

    @BeforeEach
    public void createTestBoard() {
        final String boardName = RandomString.make();
        board = BoardManager.createBoard(boardName);
        toDoList = board.getList(BoardListsNames.TODO.getPolishLabel());
        ongoingList = board.getList(BoardListsNames.ONGOING.getPolishLabel());
        doneList = board.getList(BoardListsNames.DONE.getPolishLabel());
        for (ListTrello list : board.getLists()) {
            list.createMultipleCards(numberOfDefaultCards);
        }
    }

    @Test
    @DisplayName("TC: Move card to different list")
    @Tag("rest")
    @Tag("card")
    public void shouldMoveCardToDifferentList() {
        final String cardToMove1 = toDoList.getCardsNames().get(0);
        final String cardToMove2 = toDoList.getCardsNames().get(1);
        toDoList.getCard(cardToMove1).moveToList(ongoingList);
        toDoList.getCard(cardToMove2).moveToList(doneList);

        SoftAssertions softAssert = new SoftAssertions();
        softAssert.assertThat(restHelper.getAllCardsFromList(toDoList).jsonPath().getList("").size() == numberOfDefaultCards - 2);
        softAssert.assertThat(restHelper.getAllCardsFromList(ongoingList).jsonPath().getList("").size() == numberOfDefaultCards + 1);
        softAssert.assertThat(restHelper.getAllCardsFromList(doneList).jsonPath().getList("").size() == numberOfDefaultCards + 1);
        softAssert.assertAll();
    }

    @Test
    @DisplayName("TC: Change cards name")
    @Tag("rest")
    @Tag("card")
    public void shouldChangeCardsName() {
        final ListTrello randomList = board.getList(BoardListsNames.getRandomDefaultName());
        final String newName = "Changed Name";
        final List<String> expectedCardNames = Collections.nCopies(numberOfDefaultCards, newName);
        for (Card card : randomList.getCards()) {
            card.changeNameTo(newName);
        }
        final List<String> cardNames = restHelper.getAllCardsFromList(randomList).jsonPath().getList("name");
        assertTrue(cardNames.equals(expectedCardNames));
    }

    @Test
    @DisplayName("TC: Delete cards")
    @Tag("rest")
    @Tag("card")
    public void shouldDeleteCards() {
        for (ListTrello list : board.getLists()) {
            String cardToRemove = list.getCards().get(new Random().nextInt(0,numberOfDefaultCards)).getCardDto().name;
            list.removeCards(cardToRemove);
        }
        for (ListTrello list : board.getLists()) {
            assertTrue(restHelper.getAllCardsFromList(list).jsonPath().getList("").size() == numberOfDefaultCards - 1 );
        }
    }

    @Test
    @DisplayName("TC: Add comments to card")
    @Tag("rest")
    @Tag("card")
    public void shouldAddCommentsToCard() {
        final ListTrello randomList = board.getList(BoardListsNames.getRandomDefaultName());
        Card cardToComment = randomList.getCards().get(1);
        cardToComment.addCommentToCard("This is important comment");
        cardToComment.addCommentToCard("My first task");
        restHelper.getCard(cardToComment.getCardDto().id).then()
                .body("badges.comments", is(2));
    }
}
