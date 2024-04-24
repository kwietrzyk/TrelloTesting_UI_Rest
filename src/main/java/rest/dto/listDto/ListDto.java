package rest.dto.listDto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListDto implements Serializable {
    public String id;
    public String name;
    public boolean closed;
    public String color;
    public String idBoard;
    public int pos;
    public boolean subscribed;
    public String softLimit;

    @Override
    public String toString() {
        return "ListDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
