package hexlet.code.controller;

import hexlet.code.dto.ListPage;
import hexlet.code.dto.UrlItemPage;
import hexlet.code.utils.NamedRoutes;
import io.javalin.http.Context;
import hexlet.code.dto.MainPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import java.net.URI;
import java.sql.SQLException;

import hexlet.code.repository.UrlRepository;

public class UrlController {
    public static void main(Context ctx) {
        String error = ctx.consumeSessionAttribute("error");
        var page = new MainPage(error);
        ctx.render("index.jte", model("page", page));
    }

    public static void list(Context ctx) throws SQLException {
        var entities = UrlRepository.getEntities();
        String type = ctx.consumeSessionAttribute("type");
        String message = ctx.consumeSessionAttribute("message");
        var page = new ListPage(entities, message, type);
        ctx.render("list.jte", model("page", page));
    }

    public static void listItem(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        var url = UrlRepository.find(id);
        var page = new UrlItemPage(url.get());
        ctx.render("item.jte", model("page", page));
    }

    public static void createUrl(Context ctx) {
        var url = ctx.formParam("url");
        try {
            var parcedUrl = new URI(url).toURL();
            var result = parcedUrl.getProtocol() + "://" + parcedUrl.getHost();
            if (parcedUrl.getPort() != -1) {
                result += ":" + parcedUrl.getPort();
            }
            var existedItem = UrlRepository.findByName(result);
            if (!existedItem.isEmpty()) {
                ctx.sessionAttribute("type", "normal");
                ctx.sessionAttribute("message", "Страница уже существует");
            } else {
                UrlRepository.save(result);
                ctx.sessionAttribute("type", "success");
                ctx.sessionAttribute("message", "Страница успешно добавлена");
            }
            ctx.redirect(NamedRoutes.createUrls());

        } catch(Exception e) {
            System.out.println("sys");
            ctx.sessionAttribute("error", "Некорректный URL");
            ctx.redirect(NamedRoutes.root());
        }
    }
}
