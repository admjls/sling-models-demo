package com.icfolson.sling.models.components.content

import com.icfolson.sling.models.injectors.ComponentInjector
import com.icfolson.aem.prosper.annotations.ModelSpec
import com.icfolson.aem.prosper.specs.ProsperSpec
import spock.lang.Unroll

@Unroll
@ModelSpec
class TopNavigationSpec extends ProsperSpec {

    def setupSpec() {
        slingContext.registerInjector(new ComponentInjector(), Integer.MAX_VALUE)

        pageBuilder.content {
            demo("Demo") {
                sling("Sling")
                injectors("Injectors")
                annotations("Annotations")
            }
            circuit("Circuit")
        }
    }

    def "get brand page"() {
        setup:
        def topNavigation = getResource(path).adaptTo(TopNavigation)

        expect:
        topNavigation.brandPage.path == "/content/demo"

        where:
        path << ["/content/demo/jcr:content", "/content/demo/sling/jcr:content", "/content/demo/injectors/jcr:content"]
    }

    def "get links"() {
        setup:
        def topNavigation = getResource(path).adaptTo(TopNavigation)

        expect:
        topNavigation.links.size() == size
        topNavigation.links*.title == titles

        where:
        path                              | size | titles
        "/content/demo/jcr:content"       | 3    | ["Sling", "Injectors", "Annotations"]
        "/content/demo/sling/jcr:content" | 3    | ["Sling", "Injectors", "Annotations"]
        "/content/circuit/jcr:content"    | 0    | []
    }
}
