package hexlet.code.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import hexlet.code.model.Url;
import java.sql.Timestamp;
import java.util.Date;

import java.sql.SQLException;
import java.sql.Statement;

public class UrlRepository extends BaseRepository {
    // BEGIN
    public static void save(String name) throws SQLException {
        var sql = "INSERT INTO urls(name, created_at) VALUES (?, ?)";

        try (var conn = dataSource.getConnection()) {
            var smtp = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            smtp.setString(1, name);
            var date = new Date();
            var timestamp = new Timestamp(date.getTime());
            smtp.setTimestamp(2, timestamp);
            smtp.executeUpdate();
        }
    }

    public static Optional<Url> findByName(String name) throws SQLException {
        var sql = "SELECT * FROM urls WHERE name = ?";
        try (var conn = dataSource.getConnection()) {
            var smtp = conn.prepareStatement(sql);
            smtp.setString(1, name);
            var resultSet = smtp.executeQuery();

            if (resultSet.next()) {
                var createdAt = resultSet.getTimestamp("created_at");
                var url = new Url(name, createdAt);
                return Optional.of(url);
            }

            return Optional.empty();
        }
    }

    public static Optional<Url> find(Long id) throws SQLException {
        var sql = "SELECT * FROM urls WHERE id = ?";
        try (var conn = dataSource.getConnection()) {
            var smtp = conn.prepareStatement(sql);
            smtp.setLong(1, id);
            var resultSet = smtp.executeQuery();

            if (resultSet.next()) {
                var createdAt = resultSet.getTimestamp("created_at");
                var name = resultSet.getString("name");
                var url = new Url(name, createdAt);
                url.setId(id);
                return Optional.of(url);
            }

            return Optional.empty();
        }
    }


    public static List<Url> getEntities() throws SQLException {
        var result = new ArrayList<Url>();
        var sql = "SELECT * FROM urls";

        try (var conn = dataSource.getConnection()) {
            var smtp = conn.createStatement();
            var resultSet = smtp.executeQuery(sql);

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at");
                var url = new Url(name, createdAt);
                url.setId(id);
                result.add(url);
            }
        }

        return result;
    }
}
