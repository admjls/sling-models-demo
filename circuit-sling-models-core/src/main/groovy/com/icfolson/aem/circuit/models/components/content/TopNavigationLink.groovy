package com.icfolson.aem.circuit.models.components.content

import groovy.transform.TupleConstructor

@TupleConstructor
class TopNavigationLink {

    String path

    String title

    boolean active

    List<TopNavigationLink> links = []
}
