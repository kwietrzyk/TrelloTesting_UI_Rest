package tests.rest.pojo;

import endpointsObjects.Board;
import endpointsObjects.ListTrello;
import enums.BoardListsNames;
import factories.BoardFactory;
import helpers.RestHelper;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListTest extends BaseTest {

    @Test
    @DisplayName("Move list to another board")
    @Tag("rest")
    @Tag("pojo")
    public void shouldMoveListFromOneBoardToAnother() {
        Board board1 = BoardFactory.createBoard("Board 1");
        Board board2 = BoardFactory.createBoard("Board 2");
        String newListName = "My list";
        ListTrello newList = board1.createList(newListName);
        newList.moveToBoard(board2);
        List<String> board1Lists = RestHelper.getAllListsNamesFromBoard(board1.getBoardDto().getId());
        List<String> board2Lists = RestHelper.getAllListsNamesFromBoard(board2.getBoardDto().getId());
        assertTrue(board1Lists.size() == DEFAULT_LISTS_AMOUNT && board2Lists.contains(newListName));
    }

    @Test
    @DisplayName("Translate default list names to english")
    @Tag("rest")
    @Tag("pojo")
    public void shouldChangeListsNames() {
        Board board1 = BoardFactory.createBoard("Board 1");
        for (ListTrello list : board1.getLists()) {
            list.translateDefaultNameToEnglish();
        }
        RestHelper.getAllListsFromBoard(board1.getBoardDto().getId()).then()
                .body("name", hasItem(BoardListsNames.TODO.getEnglishLabel()))
                .body("name", hasItem(BoardListsNames.ONGOING.getEnglishLabel()))
                .body("name", hasItem(BoardListsNames.DONE.getEnglishLabel()));
    }

}
