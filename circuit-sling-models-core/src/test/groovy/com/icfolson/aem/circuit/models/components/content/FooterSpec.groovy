package com.icfolson.aem.circuit.models.components.content

import com.icfolson.aem.circuit.models.enums.AudienceStatus
import com.icfolson.aem.circuit.models.injectors.InheritInjector
import com.icfolson.aem.circuit.models.services.AudienceStatusService
import com.icfolson.aem.prosper.specs.ProsperSpec
import spock.lang.Unroll

@Unroll
class FooterSpec extends ProsperSpec {

    def setupSpec() {
        slingContext.registerInjector(new InheritInjector(), Integer.MAX_VALUE)
        slingContext.registerService(AudienceStatusService, new AudienceStatusService() {

            @Override
            AudienceStatus getAudienceStatus() {
                AudienceStatus.ASLEEP
            }
        })

        slingContext.addModelsForPackage(this.class.package.name)

        pageBuilder.content {
            demo("Demo") {
                "jcr:content"() {
                    footer(copyright: "Copyright Text")
                }
                sling("Sling") {
                    models("Models") {
                        "jcr:content" {
                            "footer"()
                        }
                    }
                }
            }
        }
    }

    def "get audience status"() {
        setup:
        def footer = getResource("/content/demo/jcr:content/footer").adaptTo(Footer)

        expect:
        footer.audienceStatus == AudienceStatus.ASLEEP.displayName
    }

    def "get copyright text with inheritance"() {
        setup:
        def footer = getResource(path).adaptTo(Footer)

        expect:
        footer.copyright == copyrightText

        where:
        path                                            | copyrightText
        "/content/demo/jcr:content/footer"              | "Copyright Text"
        "/content/demo/sling/models/jcr:content/footer" | "Copyright Text"
    }
}
