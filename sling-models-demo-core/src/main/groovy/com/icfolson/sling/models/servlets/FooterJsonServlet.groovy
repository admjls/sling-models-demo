package com.icfolson.sling.models.servlets

import com.google.common.net.MediaType
import com.icfolson.sling.models.components.content.Footer
import groovy.json.JsonBuilder
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet

import javax.servlet.ServletException

@SlingServlet(resourceTypes = "sling-models-demo/components/content/footer", methods = "GET",
    selectors = "properties", extensions = "json")
class FooterJsonServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws
        ServletException, IOException {
        response.setContentType(MediaType.JSON_UTF_8.withoutParameters().toString())

        def footer = request.resource.adaptTo(Footer)

        new JsonBuilder(footer).writeTo(response.writer)
    }
}
