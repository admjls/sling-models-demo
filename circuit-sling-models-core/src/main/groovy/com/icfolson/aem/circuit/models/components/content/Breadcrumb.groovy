package com.icfolson.aem.circuit.models.components.content

import com.citytechinc.cq.component.annotations.Component
import com.day.cq.wcm.api.Page
import com.icfolson.aem.library.core.constants.ComponentConstants
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

import javax.inject.Inject

@Component(value = "Breadcrumb", group = ComponentConstants.GROUP_HIDDEN, noDecoration = true)
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
