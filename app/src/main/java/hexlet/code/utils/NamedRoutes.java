package hexlet.code.utils;

public class NamedRoutes {
    public static String root() {
        return "/";
    }

    public static String createUrls() {
        return "/urls";
    }

    public static String urlItem(String id) {
        return "/urls/" + id;
    }
}
