package com.icfolson.sling.models.annotations

import org.apache.sling.models.annotations.Source
import org.apache.sling.models.spi.injectorspecific.InjectAnnotation

import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.FIELD
import static java.lang.annotation.ElementType.METHOD
import static java.lang.annotation.ElementType.PARAMETER
import static java.lang.annotation.RetentionPolicy.RUNTIME

@Target([METHOD, FIELD, PARAMETER])
@Retention(RUNTIME)
@InjectAnnotation
@Source(Inherit.NAME)
@interface Inherit {

    String NAME = "inherit"
}