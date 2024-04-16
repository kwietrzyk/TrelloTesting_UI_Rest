package rest.endpointsobjects;

import gui.dto.cardDto.CardDto;
import gui.dto.listDto.ListDto;

public class Card extends Endpoint {
    private final CardDto cardDto;
    private ListTrello parent;

    public Card(CardDto cardDto, ListTrello parent) {
        this.cardDto = cardDto;
        this.parent = parent;
    }
}
