package rest.endpointsobjects;

import org.apache.commons.lang3.SerializationUtils;
import rest.dto.cardDto.CardDto;
import rest.dto.listDto.ListDto;
import common.enums.BoardListsNames;
import lombok.Getter;
import rest.helpers.BoardManager;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Getter
public class ListTrello extends Endpoint implements Serializable {
    private final ListDto listDto;
    private Board parent;
    final List<Card> cards = new ArrayList<>(); // must be accessible from Card class for move() method

    ListTrello(ListDto listDto) {
        this.listDto = listDto;
        for (Board board : BoardManager.getExistingBoards()) {
            if (board.getBoardDto().getId().equals(listDto.getIdBoard())) {
                parent = board;
            }
        }
    }

    public void moveToBoard(Board dstBoard) {
        restHelper.moveList(this, dstBoard);
        parent.lists.remove(this);   // old parent board
        parent = dstBoard;              // new parent board
        parent.lists.add(this);

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

    public void createMultipleCards(int expectedNumberOfCards) {
        for (int i = 1; i <= expectedNumberOfCards; i++) {
            String cardName = this.listDto.getName() + " card " + i;
            createCard(cardName);
        }
    }

    public void removeCards(String... cardNames) {
        Iterator<Card> iterator = cards.iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (Arrays.asList(cardNames).contains(card.getCardDto().name)) {
                iterator.remove();
                restHelper.deleteCard(card);
            }
        }
    }

    public List<String> getCardsNames() {
        return cards.stream().map(n -> n.getCardDto().name).toList();
    }

    public Card getCard(String name) {
        for (Card card : cards) {
            if (name.equals(card.getCardDto().name)) {
                return SerializationUtils.clone(card);
            }
        }
        throw new IllegalArgumentException("No card with name " + name);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
