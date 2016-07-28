package com.icfolson.aem.circuit.models.servlets

import com.fasterxml.jackson.databind.ObjectMapper
import com.icfolson.aem.circuit.models.components.content.Footer
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.apache.sling.models.factory.ModelFactory

import javax.servlet.ServletException

@SlingServlet(resourceTypes = "circuit-sling-models/components/content/footer", methods = "GET", selectors = "footer",
    extensions = "json")
class FooterJsonServlet extends SlingSafeMethodsServlet {

    private static final ObjectMapper MAPPER = new ObjectMapper()

    @Reference
    private ModelFactory modelFactory

    /*
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws
        ServletException, IOException {
        def footer = request.resource.adaptTo(Footer)

        response.setContentType("application/json")

        MAPPER.writeValue(response.outputStream, footer)
    }
    */

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws
        ServletException, IOException {
        try {
            def footer = modelFactory.createModel(request.resource, Footer)

            response.setContentType("application/json")

            MAPPER.writeValue(response.outputStream, footer)
        } catch (Exception e) {
            throw new ServletException(e)
        }
    }
}
