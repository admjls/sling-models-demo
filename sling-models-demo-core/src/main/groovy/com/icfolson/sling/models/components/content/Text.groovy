package com.icfolson.sling.models.components.content

import com.citytechinc.cq.component.annotations.Component
import com.citytechinc.cq.component.annotations.DialogField
import com.citytechinc.cq.component.annotations.widgets.TextArea
import com.icfolson.sling.models.exporters.AudioExporter
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Exporter
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.Optional

import javax.inject.Inject

@Component(value = "Text")
@Model(adaptables = Resource, resourceType = Text.RESOURCE_TYPE)
@Exporter(name = AudioExporter.NAME, extensions = "mp3")
class Text implements AudioExportable {

    public static final String RESOURCE_TYPE = "sling-models-demo/components/content/text"

    @Inject
    @Delegate
    AudioExporter audioExporter

    @DialogField(fieldLabel = "Text")
    @TextArea
    @Inject
    @Optional
    private String text

    @Override
    String getText() {
        text
    }
}
