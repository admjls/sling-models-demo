package com.icfolson.aem.circuit.models.servlets

import com.day.cq.wcm.api.NameConstants
import com.fasterxml.jackson.databind.ObjectMapper
import com.icfolson.aem.circuit.models.components.content.TopNavigation
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet

import javax.servlet.ServletException

@SlingServlet(resourceTypes = NameConstants.NT_PAGE, methods = "GET", selectors = "navigation", extensions = "json")
class TopNavigationJsonServlet extends SlingSafeMethodsServlet {

    private static final ObjectMapper MAPPER = new ObjectMapper()

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws
        ServletException, IOException {
        def topNavigation = request.adaptTo(TopNavigation)

        response.setContentType("application/json")

        MAPPER.writeValue(response.outputStream, topNavigation)
    }
}
