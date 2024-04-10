package factories;

import dto.boardDto.main.BoardDto;
import dto.listDto.ListDto;
import endpointsObjects.Board;
import endpointsObjects.ListTrello;
import helpers.RestHelper;
import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static helpers.RestHelper.createNewBoardWithQueryMapAndFetchId;

public class BoardFactory {

    private BoardFactory() {
        throw new IllegalStateException("No instance allowed");
    }
    private static final List<Board> createdBoards = new ArrayList<>();

    public static Board createBoard() {
        String name = RandomString.make();
        return createBoard(name);
    }

    public static List<Board> getCreatedBoards() {
        return List.copyOf(createdBoards);
    }

    public static Board createBoard(String name) {
        Map<String, String> queryMap = BoardQueryFactory.createPostQueryMap(name);
        String boardId = RestHelper.createNewBoardWithQueryMapAndFetchId(queryMap);
        BoardDto dto = RestHelper.getBoardDto(boardId);
        List<ListTrello> lists = getBoardLists(boardId);
        Board board = new Board(dto, lists);
        createdBoards.add(board);
        return board;
    }

    private static List<ListTrello> getBoardLists(String boardId) {
        List<ListTrello> boardLists = new ArrayList<>();
        List<ListDto> listsDto = RestHelper.getAllListsFromBoard(boardId).jsonPath().getList("$", ListDto.class);
        listsDto.forEach(dto -> boardLists.add(new ListTrello(dto)));
        return boardLists;
    }
}
