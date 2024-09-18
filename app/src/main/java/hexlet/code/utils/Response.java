package hexlet.code.utils;

import com.mashape.unirest.http.HttpResponse;

import java.util.regex.Pattern;

public class Response {
    private HttpResponse<String> response;

    public Response(HttpResponse<String> response) {
        this.response = response;
    }

    private String getRegexSearchResult(String regex) {
        return getRegexSearchResult(regex, response.getBody());
    }

    private String getRegexSearchResult(String regex, String str) {
        var pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        var result = "";
        var matcher = pattern.matcher(str);

        if (matcher.find()) {
            result = matcher.group(0);
        }

        return result;
    }

    public Integer getStatusCode() {
        return this.response.getStatus();
    }

    public String getTitle() {
        var result = getRegexSearchResult("<title.*?>.+</title>");

        return result.replaceAll("<.*?>", "");
    }

    public String getH1() {
        var result = getRegexSearchResult("<h1.*?>.+</h1>");

        return result.replaceAll("<.*?>", "");
    }

    public String getDescription() {
        var result = getRegexSearchResult("<meta.*?name=\"description\".*?content=\"(.*?)\".*?>|"
                + "<meta.*?content=\"(.*?)\".*?name=\"description\".*?>");
        var content = getRegexSearchResult("content=\".+\"", result);

        return content.replace("content=\"", "").replace("\"", "").replaceAll("<.*?>", "");
    }
}
