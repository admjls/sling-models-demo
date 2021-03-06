package com.icfolson.sling.models.annotations

import com.citytechinc.cq.component.annotations.Component
import com.icfolson.sling.models.exporters.AudioExporter
import groovy.transform.AnnotationCollector
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Exporter
import org.apache.sling.models.annotations.Model

@Component(group = "Sling Models Demo")
@Model(adaptables = Resource)
@Exporter(name = AudioExporter.NAME, extensions = "mp3")
@AnnotationCollector
@interface ExportableComponent {

}