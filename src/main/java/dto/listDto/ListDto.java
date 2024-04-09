package dto.listDto;

import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListDto {
    private String id;
    private String name;
    private boolean closed;
    private String color;
    private String idBoard;
    private int pos;
    private boolean subscribed;
    private String softLimit;

    @Override
    public String toString() {
        return "ListDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
