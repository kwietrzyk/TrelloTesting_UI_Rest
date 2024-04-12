package endpointsObjects;

import dto.listDto.ListDto;
import enums.BoardListsNames;
import factories.BoardFactory;
import helpers.RestHelper;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListTrello {
    private final ListDto listDto;
    private Board parent;
    private List<Card> cards = new ArrayList<>();

    public ListTrello(ListDto listDto) {
        this.listDto = listDto;
        for (Board board : BoardFactory.getCreatedBoards()) {
            if (board.getBoardDto().getId().equals(listDto.getIdBoard())) {
                parent = board;
            }
        }
    }

    public void moveToBoard(Board dstBoard) {
        parent.getLists().remove(this);
        this.parent = dstBoard;
        dstBoard.getLists().add(this);
        RestHelper.moveList(this.listDto.getId(), parent.getBoardDto().getId());
    }

    public void translateDefaultNameToEnglish() {
        for (BoardListsNames name : BoardListsNames.values()) {
            if (name.getPolishLabel().equals(listDto.getName())) {
                String englishName = name.getEnglishLabel();
                updateName(englishName);
                return;
            }
        }
        System.out.println("List name is not one of default");
    }

    public void updateName(String newName) {
        listDto.setName(newName);
        String fieldName = "name";
        RestHelper.updateListField(listDto.getId(), fieldName, newName);
    }

}
