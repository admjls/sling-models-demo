package com.icfolson.aem.circuit.models.components.content

import com.citytechinc.cq.component.annotations.DialogField
import com.citytechinc.cq.component.annotations.widgets.PathField
import com.fasterxml.jackson.annotation.JsonIgnore
import com.icfolson.aem.circuit.models.annotations.CircuitDemoComponent
import com.icfolson.aem.library.api.link.NavigationLink
import com.icfolson.aem.library.api.page.PageDecorator
import com.icfolson.aem.library.api.page.enums.TitleType
import com.icfolson.aem.library.core.components.AbstractComponent
import com.icfolson.aem.library.core.constants.ComponentConstants
import com.icfolson.aem.library.core.link.builders.factory.LinkBuilderFactory

import javax.inject.Inject

@CircuitDemoComponent(value = "Top Navigation", group = ComponentConstants.GROUP_HIDDEN,
    actions = ["text:Top Navigation", "-", "edit"])
class TopNavigation extends AbstractComponent {

    @Inject
    @JsonIgnore
    PageDecorator currentPage

    @DialogField(fieldLabel = "Search Page", required = true)
    @PathField(rootPath = "/content")
    String getSearchPagePath() {
        getAsHrefInherited("searchPagePath").or("")
    }

    @DialogField(fieldLabel = "Catalog Root Page",
        fieldDescription = "Select the catalog root page for mapping product search results.", required = true)
    @PathField(rootPath = "/content")
    PageDecorator getCatalogRootPage() {
        getAsPageInherited("catalogRootPage").orNull()
    }

    NavigationLink getBrandLink() {
        rootPage?.navigationLink
    }

    List<NavigationLink> getLinks() {
        def pages = rootPage ? rootPage.getChildren(true) : []

        pages.collect { page ->
            def linkBuilder = LinkBuilderFactory.forPage(page, TitleType.NAVIGATION_TITLE)

            page.getChildren(true).each { child ->
                linkBuilder.addChild(child.navigationLink)
            }

            linkBuilder.active = currentPage.path.startsWith(page.path)

            linkBuilder.buildNavigationLink()
        }
    }

    private PageDecorator getRootPage() {
        currentPage.getAbsoluteParent(1)
    }
}
