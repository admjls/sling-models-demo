package com.icfolson.sling.models.components.content

import com.citytechinc.cq.component.annotations.DialogField
import com.citytechinc.cq.component.annotations.widgets.TextArea
import com.icfolson.sling.models.annotations.ExportableComponent
import org.apache.sling.models.annotations.Optional

import javax.inject.Inject

@ExportableComponent(value = "Text", resourceType = Text.RESOURCE_TYPE)
class Text implements AudioExportable {

    public static final String RESOURCE_TYPE = "sling-models-demo/components/content/text"

    @DialogField(fieldLabel = "Text")
    @TextArea
    @Inject
    @Optional
    String text
}
