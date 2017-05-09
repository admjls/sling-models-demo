package com.icfolson.sling.models.components.content;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.factory.ModelFactory;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class FooterModel {

    @Inject
    @Optional
    private String copyright;

    @Self
    private Resource resource;

    @Inject
    private ModelFactory modelFactory;

    public String getCopyright() {
        return modelFactory.createModel(resource, Footer.class).getCopyright();
    }

    /*
    public String getCopyright() {
        return copyright;
    }
    */
}
