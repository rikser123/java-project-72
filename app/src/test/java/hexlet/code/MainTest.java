package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import java.sql.SQLException;
import java.io.IOException;

public class MainTest {
    Javalin app;

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");

        });
    }

    @Test
    public void testListPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Сайты");
        });
    }

    @Test
    public void createWrongUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=sys";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertFalse(response.body().string().contains("sys"));
        });
    }

    @Test
    public void createWrightUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://java-page-analyzer-ru.hexlet.app/";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://java-page-analyzer-ru.hexlet.app");


            var response2 = client.get("/urls");
            assertThat(response2.code()).isEqualTo(200);
            assertThat(response2.body().string()).contains("https://java-page-analyzer-ru.hexlet.app");
        });
    }
}
