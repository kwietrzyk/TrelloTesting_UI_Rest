package endpoints;

import dto.boardDto.main.BoardDto;
import dto.listDto.ListDto;
import helpers.RestHelper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.assertj.core.api.SoftAssertions;

import java.util.*;

import static helpers.RestHelper.getBoard;
import static org.hamcrest.Matchers.equalTo;

@Builder
@Getter
@AllArgsConstructor
public class Board {

    private BoardDto boardDto;
    private List<ListTrello> lists;

    public void addLists(String... listNames) {
        for (String name : listNames) {
            String listId = RestHelper.createNewListAdnFetchId(name, boardDto.getId()); // creates list in app
            ListDto listDto = RestHelper.getListDto(listId);
            lists.add(new ListTrello(listDto));
        }
    }

    public List<ListTrello> getAllLists() {
        return List.copyOf(lists);
    }

    public void removeAllLists() {
        lists.forEach(list -> RestHelper.deleteList(list.getDto().getId()));
        lists.clear();
    }

    public void removeLists(String... listNames) {
        Iterator<ListTrello> iterator = lists.iterator();
        while (iterator.hasNext()) {
            ListTrello list = iterator.next();
            if (Arrays.asList(listNames).contains(list.getDto().getName())) {
                iterator.remove();
                RestHelper.deleteList(list.getDto().getId());
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
            if (listName.equals(list.getDto().getName())) {
                return list;
            }
        }
        throw new IllegalArgumentException(listName + " not found in board " + boardDto.getName());
    }
}
