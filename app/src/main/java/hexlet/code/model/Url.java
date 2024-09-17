package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;


@AllArgsConstructor
@Getter
public class Url {
    private Long id;
    private String name;
    private Timestamp crated_at;
}
