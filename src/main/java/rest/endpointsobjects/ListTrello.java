package rest.endpointsobjects;

import gui.dto.cardDto.CardDto;
import gui.dto.listDto.ListDto;
import common.enums.BoardListsNames;
import lombok.Getter;
import rest.helpers.BoardManager;
import rest.helpers.RestInternalHelper;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListTrello extends Endpoint {
    private final ListDto listDto;
    private Board parent;
    private List<Card> cards = new ArrayList<>();

    public ListTrello(ListDto listDto) {
        this.listDto = listDto;
        for (Board board : BoardManager.getExistingBoards()) {
            if (board.getBoardDto().getId().equals(listDto.getIdBoard())) {
                parent = board;
            }
        }
    }

    public void moveToBoard(Board dstBoard) {
        parent.getLists().remove(this);
        this.parent = dstBoard;
        dstBoard.getLists().add(this);
        restHelper.moveList(this.listDto.getId(), parent.getBoardDto().getId());
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
        restHelper.updateListField(listDto.getId(), fieldName, newName);
    }

    public void createCard(String cardName) {
        String cardId = restHelper.createNewCardAndFetchId(cardName, this);
        CardDto cardDto = restHelper.getCardDto(cardId);
        cards.add(new Card(cardDto, this));
    }
}
