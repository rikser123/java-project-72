package hexlet.code.controller;

import hexlet.code.utils.NamedRoutes;
import io.javalin.http.Context;
import hexlet.code.dto.MainPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import java.net.URI;
import  java.net.URISyntaxException;
import  java.net.MalformedURLException;

public class UrlController {
    public static void main(Context ctx) {
        String error = ctx.consumeSessionAttribute("error");
        var page = new MainPage(error);
        ctx.render("index.jte", model("page", page));
    }

    public static void createUrl(Context ctx) {
        var url = ctx.formParam("url");
        try {
            var parcedUrl = new URI(url).toURL();
            System.out.println(parcedUrl);
        } catch(Exception e) {
            System.out.println("sys");
            ctx.sessionAttribute("error", "Некорректный URL");
            ctx.redirect(NamedRoutes.root());
        }
    }
}
