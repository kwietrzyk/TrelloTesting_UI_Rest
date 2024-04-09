package factories;

import dto.boardDto.main.BoardDto;
import dto.listDto.ListDto;
import endpoints.Board;
import endpoints.ListTrello;
import helpers.RestHelper;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {

    private BoardFactory() {
        throw new IllegalStateException("No instance allowed");
    }

    public static Board createBoard() {
        String name = RandomString.make();
        return createBoard(name);
    }

    public static Board createBoard(String name) {
        String boardId = RestHelper.createNewBoardAndFetchId(name);
        BoardDto dto = RestHelper.getBoardDto(boardId);
        return new Board(dto, getBoardLists(boardId));
    }

    private static List<ListTrello> getBoardLists(String boardId) {
        List<ListTrello> boardLists = new ArrayList<>();
        List<ListDto> listsDto = RestHelper.getAllListsFromBoard(boardId).jsonPath().getList("$", ListDto.class);
        listsDto.forEach(dto -> boardLists.add(new ListTrello(dto)));
        return boardLists;
    }
}
