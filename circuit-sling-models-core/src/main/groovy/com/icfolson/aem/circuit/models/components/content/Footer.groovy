package com.icfolson.aem.circuit.models.components.content

import com.citytechinc.cq.component.annotations.Component
import com.citytechinc.cq.component.annotations.DialogField
import com.citytechinc.cq.component.annotations.widgets.TextField
import com.icfolson.aem.circuit.models.annotations.Inherit
import com.icfolson.aem.circuit.models.services.AudienceStatusService
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.Optional

import javax.inject.Inject

import static com.icfolson.aem.library.core.constants.ComponentConstants.GROUP_HIDDEN

@Component(value = "Footer", group = GROUP_HIDDEN)
@Model(adaptables = Resource)
class Footer {

    @Inject
    private AudienceStatusService audienceStatusService

    @DialogField(fieldLabel = "Copyright", required = true)
    @TextField
    // @Inject
    @Inherit
    @Optional
    String copyright

    String getAudienceStatus() {
        audienceStatusService.audienceStatus.displayName
    }

    String getYear() {
        Calendar.instance.get(Calendar.YEAR) as String
    }
}
