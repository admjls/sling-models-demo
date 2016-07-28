package com.icfolson.aem.circuit.models.annotations

import groovy.transform.AnnotationCollector
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

import static org.apache.sling.models.annotations.DefaultInjectionStrategy.OPTIONAL

/**
 * Composite annotation to standardize model behavior across multiple components.
 */
@Model(adaptables = [Resource], defaultInjectionStrategy = OPTIONAL)
@AnnotationCollector
@interface DemoComponent {

}