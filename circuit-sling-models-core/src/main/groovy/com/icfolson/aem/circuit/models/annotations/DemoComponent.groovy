package com.icfolson.aem.circuit.models.annotations

import groovy.transform.AnnotationCollector
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model

@Model(adaptables = Resource, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@AnnotationCollector
@interface DemoComponent {

}