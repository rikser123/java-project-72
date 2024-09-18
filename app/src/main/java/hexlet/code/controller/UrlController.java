package hexlet.code.controller;

import com.mashape.unirest.http.Unirest;
import hexlet.code.dto.ListPage;
import hexlet.code.dto.UrlItemPage;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.utils.NamedRoutes;
import io.javalin.http.Context;
import hexlet.code.dto.MainPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.mashape.unirest.http.exceptions.UnirestException;
import hexlet.code.utils.Response;

import hexlet.code.repository.UrlRepository;

public class UrlController {
    public static void main(Context ctx) {
        String error = ctx.consumeSessionAttribute("error");
        var page = new MainPage(error);
        ctx.render("index.jte", model("page", page));
    }

    public static void list(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var urlIds = urls.stream().map(url -> url.getId()).toArray();
        var urlChecks = UrlCheckRepository.getEntitiesByUrl(urlIds);
        var urlChecksByUrlId = urlChecks.stream().collect(Collectors.groupingBy(item -> item.getUrlId()));

        var entries = urlChecksByUrlId.entrySet();
        var result = new HashMap<Long, UrlCheck>();
        for (var entry: entries) {
            var key = entry.getKey();
            var firstValue = entry.getValue().get(0);
            result.put(key, firstValue);
        }
        String type = ctx.consumeSessionAttribute("type");
        String message = ctx.consumeSessionAttribute("message");
        var page = new ListPage(urls, message, type, result);
        ctx.render("list.jte", model("page", page));
    }

    public static void listItem(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);
        var url = UrlRepository.find(id).get();
        var checks = UrlCheckRepository.getEntities(url.getId());
        String message = ctx.consumeSessionAttribute("message");
        var page = new UrlItemPage(url, checks, message);
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

        } catch (Exception e) {
            ctx.sessionAttribute("error", "Некорректный URL");
            ctx.redirect(NamedRoutes.root());
        }
    }

    public static void createSiteCheck(Context ctx) throws UnirestException, SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        try {
            var url = ctx.formParam("url");
            var responseString = Unirest.get(url).asString();

            var response = new Response(responseString);
            var title = response.getTitle();
            var statusCode = response.getStatusCode();
            var h1 = response.getH1();
            var description = response.getDescription();
            var urlCheck = new UrlCheck(statusCode, title, h1, description, id);
            UrlCheckRepository.save(urlCheck);

            ctx.sessionAttribute("message", "Страница успешно проверена");
            ctx.redirect(NamedRoutes.urlItem(id.toString()));
        } catch (Exception e) {
            ctx.sessionAttribute("message", "Некорректный адрес");
            ctx.redirect(NamedRoutes.urlItem(id.toString()));
        }

    }
}
