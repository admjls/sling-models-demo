package com.icfolson.sling.models.enums

import groovy.transform.TupleConstructor

@TupleConstructor
enum AudienceStatus {
    ASLEEP("asleep", "alert-danger"),
    AWAKE("awake", "alert-success")

    String displayName

    String cssClass
}