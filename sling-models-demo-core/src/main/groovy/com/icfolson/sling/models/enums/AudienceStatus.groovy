package com.icfolson.sling.models.enums

import groovy.transform.TupleConstructor

@TupleConstructor
enum AudienceStatus {
    ASLEEP("asleep"),
    AWAKE("awake"),
    COMPLAINING_ABOUT_WIFI("complaining about WiFi")

    String displayName
}