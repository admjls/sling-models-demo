package com.icfolson.sling.models.components.content

import com.citytechinc.cq.component.annotations.Component
import com.day.cq.wcm.api.Page
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

import javax.inject.Inject

import static com.icfolson.aem.library.core.constants.ComponentConstants.GROUP_HIDDEN

@Component(value = "Breadcrumb", group = GROUP_HIDDEN, noDecoration = true)
@Model(adaptables = Resource)
class Breadcrumb {

    @Inject
    Page currentPage

    List<Page> getPages() {
        def pages = []

        def rootPage = currentPage.getAbsoluteParent(1)
        def page = currentPage

        while (page && page.depth >= rootPage.depth) {
            pages.add(page)

            page = page.parent
        }

        pages.reverse()
    }
}
