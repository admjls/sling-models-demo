package com.icfolson.sling.models.exporters.impl

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.polly.AmazonPolly
import com.amazonaws.services.polly.AmazonPollyClientBuilder
import com.amazonaws.services.polly.model.DescribeVoicesRequest
import com.amazonaws.services.polly.model.OutputFormat
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest
import com.amazonaws.services.polly.model.Voice
import com.google.common.base.Charsets
import com.google.common.io.ByteStreams
import com.icfolson.sling.models.components.content.Describable
import groovy.transform.Synchronized
import org.apache.sling.models.export.spi.ModelExporter
import org.apache.sling.models.factory.ExportException
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Modified
import org.osgi.service.metatype.annotations.Designate

@Component(service = ModelExporter)
@Designate(ocd = AudioDescriptionModelExporterConfiguration)
class AudioDescriptionModelExporter implements ModelExporter {

    private AmazonPolly polly

    private Voice voice

    @Override
    boolean isSupported(Class<?> clazz) {
        clazz == InputStream || clazz == String
    }

    @Override
    <T> T export(Object model, Class<T> clazz, Map<String, String> options) throws ExportException {
        def request = new SynthesizeSpeechRequest().withVoiceId(voice.id)
            .withOutputFormat(OutputFormat.Mp3)

        if (model instanceof Describable) {
            def describable = model as Describable

            request.setText(describable.description)
        } else {
            request.setText("model has no description")
        }

        def stream = polly.synthesizeSpeech(request).audioStream

        (clazz == String ? new String(ByteStreams.toByteArray(stream), Charsets.UTF_8) : stream) as T
    }

    @Override
    String getName() {
        "audio-description"
    }

    @Activate
    @Modified
    @Synchronized
    void modified(AudioDescriptionModelExporterConfiguration configuration) {
        def credentials = new BasicAWSCredentials(configuration.accessKey(), configuration.secretKey())

        polly = AmazonPollyClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build()

        def describeVoicesRequest = new DescribeVoicesRequest()
        def describeVoicesResult = polly.describeVoices(describeVoicesRequest)

        voice = describeVoicesResult.voices[0]
    }
}