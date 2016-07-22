package com.icfolson.aem.circuit.models.components.content

import com.citytechinc.cq.component.annotations.Component
import com.citytechinc.cq.component.annotations.DialogField
import com.citytechinc.cq.component.annotations.widgets.PathField
import com.day.cq.wcm.api.Page
import com.fasterxml.jackson.annotation.JsonIgnore
import com.icfolson.aem.library.core.constants.ComponentConstants
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

import javax.inject.Inject

@Component(value = "Top Navigation", group = ComponentConstants.GROUP_HIDDEN,
    actions = ["text:Top Navigation", "-", "edit"])
@Model(adaptables = [Resource, SlingHttpServletRequest])
class TopNavigation {

    @Inject
    @JsonIgnore
    Page currentPage

    @DialogField(fieldLabel = "Search Page", required = true)
    @PathField(rootPath = "/content/demo")
    String searchPagePath

    @JsonIgnore
    Page getBrandPage() {
        currentPage.getAbsoluteParent(1)
    }
    
    List<TopNavigationLink> getLinks() {
        buildLinks(currentPage.getAbsoluteParent(1))
    }

    private List<TopNavigationLink> buildLinks(Page page) {
        page.listChildren().collect { childPage ->
            def title = childPage.navigationTitle ?: childPage.title
            def active = currentPage.path.startsWith(childPage.path)
            def links = buildLinks(childPage)

            new TopNavigationLink(childPage.path, title, active, links)
        }
    }
}
