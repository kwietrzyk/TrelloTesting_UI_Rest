package endpoints;

import dto.listDto.ListDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListTrello {
    private ListDto dto;
    private List<Card> card = new ArrayList<>();

    public ListTrello(ListDto listDto) {
        this.dto = listDto;
    }

}
