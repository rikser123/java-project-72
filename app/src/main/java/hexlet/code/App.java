package hexlet.code;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.sql.SQLException;

import hexlet.code.utils.NamedRoutes;
import io.javalin.Javalin;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.nio.file.Path;
import hexlet.code.repository.BaseRepository;
import hexlet.code.controller.UrlController;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import io.javalin.rendering.template.JavalinJte;
import gg.jte.resolve.DirectoryCodeResolver;

public class App {
    private static TemplateEngine createTemplateEngine() {
        DirectoryCodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src", "main", "jte"));
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    private static String readResourceFile(String fileName) throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    public static Javalin getApp() throws IOException, SQLException {

        var hikariConfig = new HikariConfig();
        var localBase = "jdbc:h2:mem:project";
        var JDBC_DATABASE_URL = System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project");
        hikariConfig.setJdbcUrl(JDBC_DATABASE_URL);

        var dataSource = new HikariDataSource(hikariConfig);
        var scheme = localBase.equals(JDBC_DATABASE_URL) ? "schemaLocal.sql" : "schemeProd.sql";
        var sql = readResourceFile(scheme);

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        app.get(NamedRoutes.root(), UrlController::main);
        app.post(NamedRoutes.createUrls(), UrlController::createUrl);

        return app;
    }

    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        app.start(7070);
    }
}
