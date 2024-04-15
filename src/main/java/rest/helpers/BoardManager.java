package rest.helpers;

import gui.dto.boardDto.main.BoardDto;
import gui.dto.listDto.ListDto;
import net.bytebuddy.utility.RandomString;
import rest.endpointsobjects.Board;
import rest.endpointsobjects.ListTrello;
import rest.factories.BoardQueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardManager {

    private static final RestInternalHelper restHelper = new RestInternalHelper();
    private static final List<Board> existingBoards = new ArrayList<>();

    public static List<Board> getExistingBoards() {
        return List.copyOf(existingBoards);
    }

    public static Board createBoard() {
        return BoardFactory.createBoard();
    }

    public static Board createBoard(String name) {
        return BoardFactory.createBoard(name);
    }

    public static Board createBoardFromQuery(Map<String, String> queryMap) {
        return BoardFactory.createBoard(queryMap);
    }

    public static void addBoard(Board board) {
        existingBoards.add(board);
    }

    public static void deleteBoard(Board board) {
        existingBoards.remove(board);
        restHelper.deleteBoard(board.getBoardDto().getId());
    }

    static class BoardFactory {

        private static Board createBoard() {
            String name = RandomString.make();
            return createBoard(name);
        }

        private static Board createBoard(String name) {
            Map<String, String> queryMap = BoardQueryFactory.createPostQueryMap(name);
            return createBoard(queryMap);
        }

        private static Board createBoard(Map<String, String> queryMap) {
            BoardDto dto = restHelper.createNewBoardWithQueryMapAsDto(queryMap);
            List<ListTrello> lists = getBoardLists(dto.getId());
            Board board = new Board(dto, lists);
            BoardManager.addBoard(board);
            return board;
        }

        private static List<ListTrello> getBoardLists(String boardId) {
            List<ListTrello> boardLists = new ArrayList<>();
            List<ListDto> listsDto = restHelper.getAllListsFromBoard(boardId).jsonPath().getList("$", ListDto.class);
            listsDto.forEach(dto -> boardLists.add(new ListTrello(dto)));
            return boardLists;
        }
    }
}
