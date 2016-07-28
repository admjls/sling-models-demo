package com.icfolson.aem.circuit.models.components.content

import com.citytechinc.cq.component.annotations.Component
import com.citytechinc.cq.component.annotations.DialogField
import com.citytechinc.cq.component.annotations.widgets.TextField
import com.google.common.base.Objects
import com.icfolson.aem.circuit.models.services.AudienceStatusService
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

import javax.inject.Inject
import java.time.LocalDate

import static com.icfolson.aem.library.core.constants.ComponentConstants.GROUP_HIDDEN

@Component(value = "Footer", group = GROUP_HIDDEN)
@Model(adaptables = Resource)
class Footer {

    @Inject
    private AudienceStatusService audienceStatusService

    @DialogField(fieldLabel = "Copyright Text", required = true)
    @TextField
    @Inject
    // @Inherit
    // @Optional
    String copyright

    String getAudienceStatus() {
        audienceStatusService.audienceStatus.displayName
    }

    String getYear() {
        LocalDate.now().year as String
    }

    @Override
    String toString() {
        Objects.toStringHelper(this)
            .add("copyright", copyright)
            .add("year", year)
            .add("audienceStatus", audienceStatus)
            .toString()
    }
}
