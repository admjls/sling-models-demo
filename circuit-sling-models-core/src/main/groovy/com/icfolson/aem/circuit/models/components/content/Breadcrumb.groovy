package com.icfolson.aem.circuit.models.components.content

import com.icfolson.aem.circuit.models.annotations.CircuitDemoComponent
import com.icfolson.aem.library.api.link.NavigationLink
import com.icfolson.aem.library.api.page.PageDecorator
import com.icfolson.aem.library.core.constants.ComponentConstants

import javax.inject.Inject

@CircuitDemoComponent(value = "Breadcrumb", group = ComponentConstants.GROUP_HIDDEN, noDecoration = true)
class Breadcrumb {

    @Inject
    PageDecorator currentPage

    List<NavigationLink> getLinks() {
        def links = []

        def rootPage = currentPage.getAbsoluteParent(1)

        if (rootPage) {
            def page = currentPage

            while (page && page.depth >= rootPage.depth) {
                links.add(page.getNavigationLink(currentPage.path == page.path))

                page = page.parent
            }
        }

        links.reverse()
    }
}
