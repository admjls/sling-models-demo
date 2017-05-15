package com.icfolson.sling.models.servlets

import com.day.cq.wcm.api.NameConstants
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.net.MediaType
import com.icfolson.sling.models.components.content.TopNavigation
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
        response.contentType = MediaType.JSON_UTF_8.withoutParameters().toString()

        MAPPER.writeValue(response.outputStream, request.adaptTo(TopNavigation))
    }
}
