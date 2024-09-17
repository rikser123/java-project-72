package gg.jte.generated.ondemand;
import hexlet.code.dto.UrlItemPage;
public final class JteitemGenerated {
	public static final String JTE_NAME = "item.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,5,5,8,8,8,13,13,13,17,17,17,21,21,21,47,47,47,48,48,48,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlItemPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <section>\r\n        <div class=\"container-lg mt-5\">\r\n            <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\r\n            <table class=\"table table-bordered table-hover mt-3\">\r\n                <tbody>\r\n                <tr>\r\n                    <td>ID</td>\r\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\r\n                </tr>\r\n                <tr>\r\n                    <td>Имя</td>\r\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\r\n                </tr>\r\n                <tr>\r\n                    <td>Дата создания</td>\r\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getCrated_at().toString());
				jteOutput.writeContent("</td>\r\n                </tr>\r\n                </tbody>\r\n            </table>\r\n\r\n            <h2 class=\"mt-5\">Проверки</h2>\r\n            <form method=\"post\" action=\"/urls/2/checks\">\r\n                <button type=\"submit\" class=\"btn btn-primary\">Запустить проверку</button>\r\n            </form>\r\n\r\n            <table class=\"table table-bordered table-hover mt-3\">\r\n                <thead>\r\n                <tr><th class=\"col-1\">ID</th>\r\n                    <th class=\"col-1\">Код ответа</th>\r\n                    <th>title</th>\r\n                    <th>h1</th>\r\n                    <th>description</th>\r\n                    <th class=\"col-2\">Дата проверки</th>\r\n                </tr></thead>\r\n                <tbody>\r\n\r\n                </tbody>\r\n            </table>\r\n        </div>\r\n\r\n    </section>\r\n");
			}
		});
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlItemPage page = (UrlItemPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
