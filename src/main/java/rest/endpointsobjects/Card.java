package rest.endpointsobjects;

import rest.dto.cardDto.CardDto;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
public class Card extends Endpoint implements Serializable {
    private final CardDto cardDto;
    private ListTrello parent;

    public Card(CardDto cardDto, ListTrello parent) {
        this.cardDto = cardDto;
        this.parent = parent;
    }

    public void moveToList(ListTrello dstList) {
        restHelper.moveCard(this, dstList);
        parent.cards.remove(this);      // old parent list
        parent = dstList;                  // new parent list
        parent.cards.add(this);
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
