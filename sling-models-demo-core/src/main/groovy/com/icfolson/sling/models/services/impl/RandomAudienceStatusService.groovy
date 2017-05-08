package com.icfolson.sling.models.services.impl

import com.icfolson.sling.models.enums.AudienceStatus
import com.icfolson.sling.models.services.AudienceStatusService
import org.apache.felix.scr.annotations.Component
import org.apache.felix.scr.annotations.Service

import java.util.concurrent.ThreadLocalRandom

@Component
@Service(AudienceStatusService)
class RandomAudienceStatusService implements AudienceStatusService {

    @Override
    AudienceStatus getAudienceStatus() {
        def values = AudienceStatus.values()
        def index = ThreadLocalRandom.current().nextInt(values.size())

        values[index]
    }
}
