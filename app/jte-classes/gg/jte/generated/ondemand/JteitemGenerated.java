package gg.jte.generated.ondemand;
import hexlet.code.dto.UrlItemPage;
import hexlet.code.utils.NamedRoutes;
public final class JteitemGenerated {
	public static final String JTE_NAME = "item.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,7,7,9,9,9,12,12,15,15,15,20,20,20,24,24,24,28,28,28,34,34,34,34,34,34,34,34,34,35,35,35,35,35,35,35,35,35,49,49,51,51,51,52,52,52,53,53,53,54,54,54,55,55,55,56,56,56,58,58,64,64,64,65,65,65,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlItemPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    ");
				if (page.getMessage() != null) {
					jteOutput.writeContent("\r\n        <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-success\" role=\"alert\">\r\n            <p class=\"m-0\">");
					jteOutput.setContext("p", null);
					jteOutput.writeUserContent(page.getMessage());
					jteOutput.writeContent("</p>\r\n            <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n        </div>\r\n    ");
				}
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
				jteOutput.writeContent("</td>\r\n                </tr>\r\n                </tbody>\r\n            </table>\r\n\r\n            <h2 class=\"mt-5\">Проверки</h2>\r\n            <form method=\"post\"");
				var __jte_html_attribute_0 = NamedRoutes.urlItemChecks(page.getUrl().getId().toString());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\r\n                <input type=\"hidden\"");
				var __jte_html_attribute_1 = page.getUrl().getName();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" name=\"url\"/>\r\n                <button type=\"submit\" class=\"btn btn-primary\">Запустить проверку</button>\r\n            </form>\r\n\r\n            <table class=\"table table-bordered table-hover mt-3\">\r\n                <thead>\r\n                <tr><th class=\"col-1\">ID</th>\r\n                    <th class=\"col-1\">Код ответа</th>\r\n                    <th>title</th>\r\n                    <th>h1</th>\r\n                    <th>description</th>\r\n                    <th class=\"col-2\">Дата проверки</th>\r\n                </tr></thead>\r\n                <tbody>\r\n                ");
				for (var check : page.getChecks()) {
					jteOutput.writeContent("\r\n                    <tr>\r\n                        <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getId());
					jteOutput.writeContent("</td>\r\n                        <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getStatusCode());
					jteOutput.writeContent("</td>\r\n                        <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getTitle());
					jteOutput.writeContent("</td>\r\n                        <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getH1());
					jteOutput.writeContent("</td>\r\n                        <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getDescription());
					jteOutput.writeContent("</td>\r\n                        <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getCreated_at().toString());
					jteOutput.writeContent("</td>\r\n                    </tr>\r\n                 ");
				}
				jteOutput.writeContent("\r\n                </tbody>\r\n            </table>\r\n        </div>\r\n\r\n    </section>\r\n");
			}
		});
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlItemPage page = (UrlItemPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
