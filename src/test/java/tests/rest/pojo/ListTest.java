package tests.rest.pojo;

import endpointsObjects.Board;
import endpointsObjects.ListTrello;
import factories.BoardFactory;
import helpers.RestHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

import java.util.List;

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

}
