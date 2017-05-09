package com.icfolson.sling.models.servlets

import com.google.common.io.ByteStreams
import com.google.common.net.MediaType
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.apache.sling.models.factory.ModelFactory

import javax.servlet.ServletException

@SlingServlet(resourceTypes = "sling/servlet/default", methods = "GET", selectors = "description", extensions = "mp3")
@Slf4j("LOG")
class AudioDescriptionModelExporterServlet extends SlingSafeMethodsServlet {

    @Reference
    private ModelFactory modelFactory

    @Override
    protected void doGet(SlingHttpServletRequest request,
        SlingHttpServletResponse response) throws ServletException, IOException {
        def model = modelFactory.getModelFromResource(request.resource)

        def audioStream = modelFactory.exportModel(model, "audio-description", InputStream, [:])

        response.contentType = MediaType.MPEG_AUDIO.withoutParameters().toString()

        def outputStream = response.outputStream

        ByteStreams.copy(audioStream, outputStream)

        outputStream.flush()
        audioStream.close()
    }
}
