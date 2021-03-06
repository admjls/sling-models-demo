package com.icfolson.sling.models.components.content

import com.citytechinc.cq.component.annotations.Component
import com.citytechinc.cq.component.annotations.DialogField
import com.citytechinc.cq.component.annotations.Listener
import com.citytechinc.cq.component.annotations.widgets.TextField
import com.google.common.base.Objects
import com.icfolson.sling.models.enums.AudienceStatus
import com.icfolson.sling.models.services.AudienceStatusService
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.Optional
import org.apache.sling.models.annotations.injectorspecific.Self

import javax.annotation.PostConstruct
import javax.inject.Inject
import java.time.LocalDate

import static com.icfolson.aem.library.core.constants.ComponentConstants.EVENT_AFTER_EDIT
import static com.icfolson.aem.library.core.constants.ComponentConstants.GROUP_HIDDEN
import static com.icfolson.aem.library.core.constants.ComponentConstants.REFRESH_PAGE

@Component(value = "Footer",
    group = GROUP_HIDDEN,
    listeners = [
        @Listener(name = EVENT_AFTER_EDIT, value = REFRESH_PAGE)
    ])
@Model(adaptables = Resource)
class Footer {

    public static final String RESOURCE_TYPE = "sling-models-demo/components/content/footer"

    @Inject
    private AudienceStatusService audienceStatusService

    @Self
    @Delegate
    private SiteContext siteContext

    @DialogField(fieldLabel = "Copyright Text", required = true)
    @TextField
    @Inject
    // @Inherit
    @Optional
    String copyright

    AudienceStatus audienceStatus

    @PostConstruct
    void init() {
        audienceStatus = audienceStatusService.audienceStatus
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
