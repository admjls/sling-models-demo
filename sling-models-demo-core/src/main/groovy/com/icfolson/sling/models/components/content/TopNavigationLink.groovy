package com.icfolson.sling.models.components.content

import groovy.transform.TupleConstructor

@TupleConstructor
class TopNavigationLink {

    String path

    String title

    boolean active
}
