package com.icfolson.sling.models.components.content

import com.day.cq.wcm.api.Page
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

import javax.inject.Inject

@Model(adaptables = Resource)
class SiteContext {

    private static final Integer DEPTH_HOMEPAGE = 2

    @Inject
    private Page currentPage

    Boolean isHomePage() {
        currentPage.depth == DEPTH_HOMEPAGE
    }
}
