package hexlet.code.dto;

import java.util.List;
import hexlet.code.model.Url;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListPage {
    private List<Url> urls;
    private String message;
    private String type;
}
