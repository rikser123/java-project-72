package hexlet.code;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.sql.SQLException;

import io.javalin.Javalin;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import hexlet.code.repository.BaseRepository;

public class App {
    private static String readResourceFile(String fileName) throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static Javalin getApp() throws IOException, SQLException {

        var hikariConfig = new HikariConfig();
        var JDBC_DATABASE_URL = System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project");
        hikariConfig.setJdbcUrl(JDBC_DATABASE_URL);

        var dataSource = new HikariDataSource(hikariConfig);
        var sql = readResourceFile("schema.sql");

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.result("Hello, World!");
        });

        return app;
    }

    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        app.start(7070);
    }
}
