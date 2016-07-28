package com.icfolson.aem.circuit.models.servlets

import com.google.common.net.MediaType
import com.icfolson.aem.circuit.models.components.content.Footer
import groovy.json.JsonBuilder
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet

import javax.servlet.ServletException

@SlingServlet(resourceTypes = "circuit-sling-models/components/content/footer", methods = "GET", selectors = "footer",
    extensions = "json")
class FooterJsonServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws
        ServletException, IOException {
        response.setContentType(MediaType.JSON_UTF_8.withoutParameters().toString())

        def footer = request.resource.adaptTo(Footer)

        new JsonBuilder(footer).writeTo(response.writer)
    }
}
