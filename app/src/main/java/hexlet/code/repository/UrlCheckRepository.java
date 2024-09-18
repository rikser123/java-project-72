package hexlet.code.repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hexlet.code.model.UrlCheck;

public class UrlCheckRepository extends BaseRepository {
    public static void save(UrlCheck urlCheck) throws SQLException {
        var sql = "INSERT INTO url_checks(status_code, title, h1, description, url_id, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (var conn = dataSource.getConnection()) {
            var smtp = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            smtp.setInt(1, urlCheck.getStatusCode());
            smtp.setString(2, urlCheck.getTitle());
            smtp.setString(3, urlCheck.getH1());
            smtp.setString(4, urlCheck.getDescription());
            smtp.setLong(5, urlCheck.getUrlId());
            var date = new Date();
            var timestamp = new Timestamp(date.getTime());
            smtp.setTimestamp(6, timestamp);
            smtp.executeUpdate();
        }
    }

    public static List<UrlCheck> getEntities(Long urlId) throws SQLException {
        var result = new ArrayList<UrlCheck>();
        var sql = "SELECT * FROM url_checks WHERE url_id = ? ORDER BY url_id DESC";

        try (var conn = dataSource.getConnection()) {
            var smtp = conn.prepareStatement(sql);
            smtp.setLong(1, urlId);
            var resultSet = smtp.executeQuery();

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var statusCode = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");

                var urlCheck = new UrlCheck(statusCode, title, h1, description, urlId);
                urlCheck.setCreated_at(createdAt);
                urlCheck.setId(id);

                result.add(urlCheck);
            }
        }

        return result;
    }
}
