package com.icfolson.sling.models.components.content

import com.icfolson.aem.prosper.annotations.ModelSpec
import com.icfolson.aem.prosper.specs.ProsperSpec
import com.icfolson.sling.models.enums.AudienceStatus
import com.icfolson.sling.models.injectors.ComponentInjector
import com.icfolson.sling.models.injectors.InheritInjector
import com.icfolson.sling.models.services.AudienceStatusService
import org.apache.sling.models.factory.MissingElementsException
import org.apache.sling.models.factory.ModelFactory
import spock.lang.Ignore
import spock.lang.Unroll

@Unroll
@ModelSpec
class FooterSpec extends ProsperSpec {

    def setupSpec() {
        slingContext.registerInjector(new ComponentInjector(), Integer.MAX_VALUE)
        slingContext.registerInjector(new InheritInjector(), Integer.MAX_VALUE)
        slingContext.registerService(AudienceStatusService, new AudienceStatusService() {

            @Override
            AudienceStatus getAudienceStatus() {
                AudienceStatus.ASLEEP
            }
        })

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
            circuit("Circuit") {
                "jcr:content"() {
                    footer()
                }
            }
        }
    }

    def "get audience status"() {
        setup:
        def footer = getResource("/content/demo/jcr:content/footer").adaptTo(Footer)

        expect:
        footer.audienceStatus == AudienceStatus.ASLEEP
    }

    def "get copyright text"() {
        setup:
        def footer = getResource("/content/demo/jcr:content/footer").adaptTo(Footer)

        expect:
        footer.copyright == "Copyright Text"
    }

    @Ignore
    def "get copyright text with inheritance"() {
        setup:
        def footer = getResource("/content/demo/sling/models/jcr:content/footer").adaptTo(Footer)

        expect:
        footer.copyright == "Copyright Text"
    }

    @Ignore
    def "model factory throws exception for missing required field"() {
        setup:
        def modelFactory = slingContext.getService(ModelFactory)
        def resource = getResource("/content/circuit/jcr:content/footer")

        when:
        modelFactory.createModel(resource, Footer)

        then:
        def e = thrown(MissingElementsException)

        e.missingElements.size() == 1
    }
}
