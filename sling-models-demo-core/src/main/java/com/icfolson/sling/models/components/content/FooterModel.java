package com.icfolson.sling.models.components.content;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class FooterModel {

    @Inject
    @Optional
    private String copyright;

    public String getCopyright() {
        return copyright;
    }
}
