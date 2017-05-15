package com.icfolson.sling.models.servlets

import com.google.common.io.ByteStreams
import com.google.common.net.MediaType
import com.icfolson.sling.models.exporters.AudioExporter
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Reference
import org.apache.felix.scr.annotations.sling.SlingServlet
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.servlets.SlingSafeMethodsServlet
import org.apache.sling.models.factory.ModelFactory

import javax.servlet.ServletException

@SlingServlet(resourceTypes = "sling/servlet/default", methods = "GET", selectors = "audio", extensions = "mp3")
@Slf4j("LOG")
class AudioModelExporterServlet extends SlingSafeMethodsServlet {

    @Reference
    private ModelFactory modelFactory

    @Override
    protected void doGet(SlingHttpServletRequest request,
        SlingHttpServletResponse response) throws ServletException, IOException {
        try {
            def audioStream = modelFactory.exportModelForResource(request.resource, AudioExporter.NAME, InputStream,
                getOptions(request))

            response.contentType = MediaType.MPEG_AUDIO.withoutParameters().toString()

            def outputStream = response.outputStream

            ByteStreams.copy(audioStream, outputStream)

            outputStream.flush()
            audioStream.close()
        } catch (Exception e) {
            LOG.error("error exporting audio for resource = ${request.resource.path}", e)

            throw new ServletException(e)
        }
    }

    private Map<String, String> getOptions(SlingHttpServletRequest request) {
        def selectors = request.requestPathInfo.selectors
        def options = [:]

        if (selectors.length > 1) {
            options[AudioExporter.VOICE_ID] = selectors[1]
        }

        options
    }
}
