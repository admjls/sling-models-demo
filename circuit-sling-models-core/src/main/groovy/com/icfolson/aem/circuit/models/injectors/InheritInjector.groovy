package com.icfolson.aem.circuit.models.injectors

import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap
import com.icfolson.aem.circuit.models.annotations.Inherit
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.spi.DisposalCallbackRegistry
import org.apache.sling.models.spi.Injector
import org.apache.sling.models.spi.injectorspecific.AbstractInjectAnnotationProcessor2
import org.apache.sling.models.spi.injectorspecific.InjectAnnotationProcessor2
import org.apache.sling.models.spi.injectorspecific.StaticInjectAnnotationProcessorFactory
import org.osgi.framework.Constants

import java.lang.reflect.AnnotatedElement
import java.lang.reflect.Type

@Component
@Service(Injector)
@Property(name = Constants.SERVICE_RANKING, intValue = 5000)
@Slf4j("LOG")
class InheritInjector implements StaticInjectAnnotationProcessorFactory, Injector {

    @Override
    String getName() {
        Inherit.NAME
    }

    @Override
    Object getValue(Object adaptable, String name, Type declaredType, AnnotatedElement element,
        DisposalCallbackRegistry callbackRegistry) {
        def value = null

        if (element.getAnnotation(Inherit)) {
            def resource = null

            if (adaptable instanceof Resource) {
                resource = adaptable as Resource
            } else if (adaptable instanceof SlingHttpServletRequest) {
                resource = (adaptable as SlingHttpServletRequest).resource
            }

            if (resource) {
                def valueMap = new HierarchyNodeInheritanceValueMap(resource)

                value = valueMap.getInherited(name, declaredType as Class)
            }
        }

        value
    }

    @Override
    InjectAnnotationProcessor2 createAnnotationProcessor(AnnotatedElement element) {
        def annotation = element.getAnnotation(Inherit)

        annotation ? new InheritAnnotationProcessor() : null
    }

    private static class InheritAnnotationProcessor extends AbstractInjectAnnotationProcessor2 {

    }
}
