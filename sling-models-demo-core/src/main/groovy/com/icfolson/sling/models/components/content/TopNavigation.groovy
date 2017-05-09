package com.icfolson.sling.models.components.content

import com.citytechinc.cq.component.annotations.Component
import com.day.cq.wcm.api.Page
import com.fasterxml.jackson.annotation.JsonIgnore
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.Model

import javax.inject.Inject

import static com.icfolson.aem.library.core.constants.ComponentConstants.GROUP_HIDDEN

@Component(value = "Top Navigation", group = GROUP_HIDDEN, noDecoration = true)
@Model(adaptables = Resource)
class TopNavigation {

    @Inject
    @JsonIgnore
    Page currentPage

    @JsonIgnore
    Page getBrandPage() {
        currentPage.getAbsoluteParent(1)
    }

    List<TopNavigationLink> getLinks() {
        currentPage.getAbsoluteParent(1).listChildren().collect { childPage ->
            def title = childPage.navigationTitle ?: childPage.title
            def active = currentPage.path.startsWith(childPage.path)

            new TopNavigationLink(childPage.path, title, active)
        }
    }
}
