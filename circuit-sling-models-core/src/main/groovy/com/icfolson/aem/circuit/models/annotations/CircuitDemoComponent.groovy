package com.icfolson.aem.circuit.models.annotations

import com.citytechinc.cq.component.annotations.Component
import groovy.transform.AnnotationCollector
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

@AnnotationCollector
@Component(group = "CIRCUIT Demo")
@Model(adaptables = Resource)
@interface CircuitDemoComponent {

}
