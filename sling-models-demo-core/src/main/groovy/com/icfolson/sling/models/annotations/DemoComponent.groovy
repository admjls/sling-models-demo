package com.icfolson.sling.models.annotations

import groovy.transform.AnnotationCollector
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Exporter
import org.apache.sling.models.annotations.Model

import static org.apache.sling.models.annotations.DefaultInjectionStrategy.OPTIONAL

@Model(adaptables = Resource, defaultInjectionStrategy = OPTIONAL)
@Exporter(name = "jackson", extensions = "json")
@AnnotationCollector
@interface DemoComponent {

}