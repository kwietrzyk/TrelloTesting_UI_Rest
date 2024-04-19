package rest.endpointsobjects;

import gui.dto.cardDto.CardDto;
import gui.dto.listDto.ListDto;
import lombok.Getter;

import java.util.Map;

@Getter
public class Card extends Endpoint {
    private final CardDto cardDto;
    private ListTrello parent;

    public Card(CardDto cardDto, ListTrello parent) {
        this.cardDto = cardDto;
        this.parent = parent;
    }

    public void moveToList(ListTrello dstList) {
        restHelper.moveCard(this, dstList);
        parent.removeCard(this);
        parent = dstList;
        parent.addCard(this);
    }

    public void changeNameTo(String newName) {
        Map<String, String> queryParams = Map.of("name", newName);
        restHelper.updateCard(this, queryParams);
        this.cardDto.name = newName;
    }

    public void addCommentToCard(String comment) {
        restHelper.addCommentToCard(this, comment);
    }
}
