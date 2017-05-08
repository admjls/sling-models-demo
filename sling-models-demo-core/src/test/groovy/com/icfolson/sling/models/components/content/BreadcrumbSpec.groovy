package com.icfolson.sling.models.components.content

import com.icfolson.sling.models.injectors.ComponentInjector
import com.icfolson.aem.prosper.annotations.ModelSpec
import com.icfolson.aem.prosper.specs.ProsperSpec

@ModelSpec
class BreadcrumbSpec extends ProsperSpec {

    def setupSpec() {
        slingContext.registerInjector(new ComponentInjector(), Integer.MAX_VALUE)

        pageBuilder.content {
            demo("Demo") {
                sling("Sling") {
                    models("Models") {
                        "jcr:content" {
                            "breadcrumb"()
                        }
                    }
                }
            }
        }
    }

    def "get links"() {
        setup:
        def breadcrumb = getResource("/content/demo/sling/models/jcr:content/breadcrumb").adaptTo(Breadcrumb)

        expect:
        breadcrumb.pages.size() == 3
        breadcrumb.pages*.title == ["Demo", "Sling", "Models"]
    }
}
