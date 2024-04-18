package rest.endpointsobjects;

import gui.dto.boardDto.main.BoardDto;
import gui.dto.listDto.ListDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Builder
@Getter
@AllArgsConstructor
public class Board extends Endpoint {

    private BoardDto boardDto;
    private final List<ListTrello> lists;

    public void createList(String name) {
        String listId = restHelper.createNewListAndFetchId(name, boardDto.getId());
        ListDto listDto = restHelper.getListDto(listId);
        lists.add(new ListTrello(listDto));
    }

    public ListTrello getList(String listName) {
        for (ListTrello list : lists) {
            if (listName.equals(list.getListDto().getName())) {
                return list;
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
        restHelper.updateBoard(boardDto);
    }

    private ListTrello getListByName(String listName) {
        for (ListTrello list : lists) {
            if (listName.equals(list.getListDto().getName())) {
                return list;
            }
        }
        throw new IllegalArgumentException(listName + " not found in board " + boardDto.getName());
    }
}
