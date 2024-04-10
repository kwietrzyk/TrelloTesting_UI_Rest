package endpointsObjects;

import dto.boardDto.main.BoardDto;
import dto.listDto.ListDto;
import helpers.RestHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Builder
@Getter
@AllArgsConstructor
public class Board {

    private BoardDto boardDto;
    private final List<ListTrello> lists;

    public ListTrello createList(String name) {
        String listId = RestHelper.createNewListAdnFetchId(name, boardDto.getId()); // creates list in app
        ListDto listDto = RestHelper.getListDto(listId);
        ListTrello list = new ListTrello(listDto);
        lists.add(list);
        return list;
    }

    public void removeAllLists() {
        lists.forEach(list -> RestHelper.deleteList(list.getListDto().getId()));
        lists.clear();
    }

    public void removeLists(String... listNames) {
        Iterator<ListTrello> iterator = lists.iterator();
        while (iterator.hasNext()) {
            ListTrello list = iterator.next();
            if (Arrays.asList(listNames).contains(list.getListDto().getName())) {
                iterator.remove();
                RestHelper.deleteList(list.getListDto().getId());
            }
        }
    }

    public void updateBoard(Map<String, String> query) {
        RestHelper.updateBoard(boardDto.getId(), query);
    }

    public void updateBoard(String name) {
        boardDto.setName(name);
        RestHelper.updateBoard(boardDto);
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
