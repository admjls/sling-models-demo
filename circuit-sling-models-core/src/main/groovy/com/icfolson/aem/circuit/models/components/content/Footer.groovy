package com.icfolson.aem.circuit.models.components.content

import com.citytechinc.cq.component.annotations.Component
import com.citytechinc.cq.component.annotations.DialogField
import com.citytechinc.cq.component.annotations.widgets.TextField
import com.icfolson.aem.library.core.constants.ComponentConstants
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.Optional

import javax.inject.Inject

@Component(value = "Footer", group = ComponentConstants.GROUP_HIDDEN)
@Model(adaptables = Resource)
class Footer {

    @DialogField(fieldLabel = "Copyright", required = true)
    @TextField
    @Inject
    @Optional
    String copyright

    String getYear() {
        Calendar.instance.get(Calendar.YEAR) as String
    }
}
