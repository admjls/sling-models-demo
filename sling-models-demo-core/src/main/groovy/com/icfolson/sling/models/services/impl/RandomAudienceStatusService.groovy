package com.icfolson.sling.models.services.impl

import com.icfolson.sling.models.enums.AudienceStatus
import com.icfolson.sling.models.services.AudienceStatusService
import org.osgi.service.component.annotations.Component

import java.util.concurrent.ThreadLocalRandom

@Component(service = AudienceStatusService, immediate = true)
class RandomAudienceStatusService implements AudienceStatusService {

    @Override
    AudienceStatus getAudienceStatus() {
        def values = AudienceStatus.values()
        def index = ThreadLocalRandom.current().nextInt(values.size())

        values[index]
    }
}