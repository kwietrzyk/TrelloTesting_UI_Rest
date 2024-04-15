package gui.dto.boardDto.main;

import gui.dto.boardDto.nested.LabelNames;
import gui.dto.boardDto.nested.Prefs;
import lombok.*;

@Builder
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    @Override
    public String toString() {
        return "BoardDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", closed=" + closed +
                ", url='" + url + '\'' +
                '}';
    }

    private String id;
    private String name;
    public String desc;
    public Object descData;
    public boolean closed;
    public String idOrganization;
    public Object idEnterprise;
    public boolean pinned;
    public String url;
    public String shortUrl;
    public Prefs prefs;
    public LabelNames labelNames;
}
