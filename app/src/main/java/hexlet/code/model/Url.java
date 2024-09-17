package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Url {
    private Long id;
    private String name;
    private Timestamp crated_at;

    public Url(String name, Timestamp crated_at) {
        this.name = name;
        this.crated_at = crated_at;
    }
}
