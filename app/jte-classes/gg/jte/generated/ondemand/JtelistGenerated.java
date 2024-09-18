package gg.jte.generated.ondemand;
import hexlet.code.utils.NamedRoutes;
import hexlet.code.dto.ListPage;
public final class JtelistGenerated {
	public static final String JTE_NAME = "list.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,8,8,9,9,11,11,11,14,14,16,16,16,19,19,20,20,34,34,36,36,36,37,37,37,37,37,37,37,37,37,37,37,37,39,39,40,40,40,41,41,44,44,45,45,45,46,46,49,49,55,55,55,56,56,56,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, ListPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <main class=\"flex-grow-1\">\r\n        ");
				if (page.getType() != null) {
					jteOutput.writeContent("\r\n            ");
					if (page.getType() == "success") {
						jteOutput.writeContent("\r\n                <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-success\" role=\"alert\">\r\n                    <p class=\"m-0\">");
						jteOutput.setContext("p", null);
						jteOutput.writeUserContent(page.getMessage());
						jteOutput.writeContent("</p>\r\n                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n                </div>\r\n             ");
					} else {
						jteOutput.writeContent("\r\n                <div class=\"rounded-0 m-0 alert alert-dismissible fade show alert-info\" role=\"alert\">\r\n                    <p class=\"m-0\">");
						jteOutput.setContext("p", null);
						jteOutput.writeUserContent(page.getMessage());
						jteOutput.writeContent("</p>\r\n                    <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\r\n                </div>\r\n             ");
					}
					jteOutput.writeContent("\r\n        ");
				}
				jteOutput.writeContent("\r\n        <section>\r\n            <div class=\"container-lg mt-5\">\r\n                <h1>Сайты</h1>\r\n                <table class=\"table table-bordered table-hover mt-3\">\r\n                    <thead>\r\n                    <tr>\r\n                        <th class=\"col-1\">ID</th>\r\n                        <th>Имя</th>\r\n                        <th class=\"col-2\">Последняя проверка</th>\r\n                        <th class=\"col-1\">Код ответа</th>\r\n                    </tr>\r\n                    </thead>\r\n                    <tbody>\r\n                    ");
				for (var url: page.getUrls()) {
					jteOutput.writeContent("\r\n                        <tr>\r\n                            <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("</td>\r\n                            <td><a");
					var __jte_html_attribute_0 = NamedRoutes.urlItem(url.getId().toString());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a></td>\r\n                            <td>\r\n                                ");
					if (page.getChecks().get(url.getId()) != null) {
						jteOutput.writeContent("\r\n                                    ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getChecks().get(url.getId()).getCreated_at().toString());
						jteOutput.writeContent("\r\n                                 ");
					}
					jteOutput.writeContent("\r\n                            </td>\r\n                            <td>\r\n                                ");
					if (page.getChecks().get(url.getId()) != null) {
						jteOutput.writeContent("\r\n                                    ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getChecks().get(url.getId()).getStatusCode().toString());
						jteOutput.writeContent("\r\n                                ");
					}
					jteOutput.writeContent("\r\n                            </td>\r\n                        </tr>\r\n                    ");
				}
				jteOutput.writeContent("\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n        </section>\r\n    </main>\r\n");
			}
		});
		jteOutput.writeContent("\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		ListPage page = (ListPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
