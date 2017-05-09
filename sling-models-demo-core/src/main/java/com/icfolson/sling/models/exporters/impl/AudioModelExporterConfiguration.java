package com.icfolson.sling.models.exporters.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ObjectClassDefinition(name = "Audio Model Exporter Configuration")
@Retention(RetentionPolicy.RUNTIME)
public @interface AudioModelExporterConfiguration {

    @AttributeDefinition(name = "Access Key")
    String accessKey() default "";

    @AttributeDefinition(name = "Secret Key")
    String secretKey() default "";
}