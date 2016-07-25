package com.icfolson.aem.circuit.models.enums

import groovy.transform.TupleConstructor

@TupleConstructor
enum AudienceStatus {
    ASLEEP("asleep"),
    AWAKE("awake"),
    COMPLAINING_ABOUT_WIFI("complaining about WiFi")

    String displayName
}