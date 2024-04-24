package rest.endpointsobjects;

import org.apache.commons.lang3.SerializationUtils;
import rest.dto.boardDto.main.BoardDto;
import rest.dto.listDto.ListDto;

import java.io.Serializable;
import java.util.*;

public class Board extends Endpoint implements Serializable {

    private BoardDto boardDto;
    final List<ListTrello> lists = new ArrayList<>(); // must be accessible from ListTrello class for move() method

    public BoardDto getBoardDto() {
        return SerializationUtils.clone(boardDto);
    }

    public List<ListTrello> getLists() {
        return List.copyOf(lists);
    }

    public Board(BoardDto boardDto) {
        this.boardDto = boardDto;
        assignBoardLists();
    }

    public void createList(String name) {
        String listId = restHelper.createNewListAndFetchId(name, boardDto.getId());
        ListDto listDto = restHelper.getListDto(listId);
        lists.add(new ListTrello(listDto));
    }

    public ListTrello getList(String listName) {
        for (ListTrello list : lists) {
            if (listName.equals(list.getListDto().getName())) {
                return SerializationUtils.clone(list);
            }
        }
        throw new IllegalArgumentException("No list with name " + listName);
    }

    public void removeAllLists() {
        lists.forEach(list -> restHelper.deleteList(list));
        lists.clear();
    }

    public void removeLists(String... listNames) {
        Iterator<ListTrello> iterator = lists.iterator();
        while (iterator.hasNext()) {
            ListTrello list = iterator.next();
            if (Arrays.asList(listNames).contains(list.getListDto().getName())) {
                iterator.remove();
                restHelper.deleteList(list);
            }
        }
    }

    public void updateBoard(Map<String, String> query) {
        restHelper.updateBoard(boardDto.getId(), query);
    }

    public void updateBoard(String name) {
        boardDto.setName(name);
        restHelper.updateBoard(boardDto);
    }

    public void updateBoard(BoardDto dto) {
        restHelper.updateBoard(dto);
    }

    private void assignBoardLists() {
        List<ListDto> listsDto = restHelper.getAllListsFromBoard(boardDto.getId()).jsonPath().getList("$", ListDto.class);
        listsDto.forEach(dto -> lists.add(new ListTrello(dto)));
    }
}
