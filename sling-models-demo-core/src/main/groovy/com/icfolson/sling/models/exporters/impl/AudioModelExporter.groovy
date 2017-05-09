package com.icfolson.sling.models.exporters.impl

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.polly.AmazonPolly
import com.amazonaws.services.polly.AmazonPollyClientBuilder
import com.amazonaws.services.polly.model.DescribeVoicesRequest
import com.amazonaws.services.polly.model.LanguageCode
import com.amazonaws.services.polly.model.OutputFormat
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest
import com.amazonaws.services.polly.model.Voice
import com.icfolson.sling.models.components.content.AudioExportable
import com.icfolson.sling.models.exporters.AudioExporter
import groovy.transform.Synchronized
import org.apache.sling.models.export.spi.ModelExporter
import org.apache.sling.models.factory.ExportException
import org.osgi.service.component.annotations.Activate
import org.osgi.service.component.annotations.Component
import org.osgi.service.component.annotations.Modified
import org.osgi.service.metatype.annotations.Designate

@Component(service = [ModelExporter, AudioExporter])
@Designate(ocd = AudioModelExporterConfiguration)
class AudioModelExporter implements ModelExporter, AudioExporter {

    private AmazonPolly polly

    private Voice defaultVoice

    @Override
    List<Voice> getVoices() {
        polly.describeVoices(new DescribeVoicesRequest()
            .withLanguageCode(LanguageCode.EnUS))
            .voices
    }

    @Override
    boolean isSupported(Class<?> clazz) {
        clazz == InputStream
    }

    @Override
    <T> T export(Object model, Class<T> clazz, Map<String, String> options) throws ExportException {
        def text

        if (model instanceof AudioExportable) {
            def exportable = model as AudioExportable

            text = exportable.text
        } else {
            text = "model has no exportable text"
        }

        def voiceId = options[VOICE_ID] ?: defaultVoice.id

        def request = new SynthesizeSpeechRequest()
            .withVoiceId(voiceId)
            .withOutputFormat(OutputFormat.Mp3)
            .withText(text)

        polly.synthesizeSpeech(request).audioStream as T
    }

    @Override
    String getName() {
        NAME
    }

    @Activate
    @Modified
    @Synchronized
    void modified(AudioModelExporterConfiguration configuration) {
        def credentials = new BasicAWSCredentials(configuration.accessKey(), configuration.secretKey())

        polly = AmazonPollyClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build()

        defaultVoice = voices[0]
    }
}