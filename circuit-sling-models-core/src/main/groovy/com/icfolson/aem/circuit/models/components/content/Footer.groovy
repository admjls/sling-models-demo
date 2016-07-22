package com.icfolson.aem.circuit.models.components.content

import com.icfolson.aem.circuit.models.annotations.CircuitDemoComponent
import com.icfolson.aem.library.core.constants.ComponentConstants

@CircuitDemoComponent(value = "Footer", group = ComponentConstants.GROUP_HIDDEN, noDecoration = true)
class Footer {

    String getYear() {
        Calendar.instance.get(Calendar.YEAR) as String
    }
}
