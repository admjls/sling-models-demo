package com.icfolson.aem.circuit.models.injectors

import com.day.cq.wcm.api.Page
import com.day.cq.wcm.api.PageManager
import groovy.util.logging.Slf4j
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Property
import org.apache.felix.scr.annotations.Service
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.api.resource.ValueMap
import org.apache.sling.models.spi.DisposalCallbackRegistry
import org.apache.sling.models.spi.Injector
import org.osgi.framework.Constants

import javax.jcr.Node
import javax.jcr.Session
import java.lang.reflect.AnnotatedElement
import java.lang.reflect.Type

/**
 * Injector for objects derived from the current component context.
 */
@Component
@Service(Injector)
@Property(name = Constants.SERVICE_RANKING, intValue = Integer.MAX_VALUE)
@Slf4j("LOG")
class ComponentInjector implements Injector {

    private static final def INJECTABLES = [Resource, ResourceResolver, ValueMap, Node, Session, Page, PageManager]

    @Override
    String getName() {
        "component"
    }

    @Override
    Object getValue(Object adaptable, String name, Type type, AnnotatedElement element,
        DisposalCallbackRegistry registry) {
        def value = null

        if (type instanceof Class) {
            def clazz = type as Class

            if (INJECTABLES.contains(clazz)) {
                value = getValueForResource(clazz, adaptable)
            }
        }

        value
    }

    private def getValueForResource(Class clazz, Object adaptable) {
        def resource = getResource(adaptable)

        def value = null

        if (resource) {
            if (clazz == Resource) {
                value = resource
            } else if (clazz == ResourceResolver) {
                value = resource.resourceResolver
            } else if (clazz == ValueMap) {
                value = resource.valueMap
            } else if (clazz == Node) {
                value = resource.adaptTo(Node)
            } else if (clazz == Session) {
                value = resource.resourceResolver.adaptTo(Session)
            } else if (clazz == Page) {
                value = resource.resourceResolver.adaptTo(PageManager).getContainingPage(resource)
            } else if (clazz == PageManager) {
                value = resource.resourceResolver.adaptTo(PageManager)
            } else {
                LOG.debug("class = {} is not supported by this injector", clazz.name)
            }

            LOG.debug("injecting class = {} with instance = {}", clazz.name, value)
        }

        value
    }

    private Resource getResource(Object adaptable) {
        def resource = null

        if (adaptable instanceof Resource) {
            resource = adaptable as Resource
        } else if (adaptable instanceof SlingHttpServletRequest) {
            resource = (adaptable as SlingHttpServletRequest).resource
        }

        resource
    }
}
