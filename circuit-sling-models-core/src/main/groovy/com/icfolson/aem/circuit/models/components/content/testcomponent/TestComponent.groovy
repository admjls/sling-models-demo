package com.icfolson.aem.circuit.models.components.content.testcomponent

import com.citytechinc.cq.component.annotations.DialogField
import com.citytechinc.cq.component.annotations.widgets.TextField
import com.icfolson.aem.circuit.models.annotations.CircuitDemoComponent
import com.icfolson.aem.library.core.components.AbstractComponent

import javax.inject.Inject

@CircuitDemoComponent(value = "Test Component")
class TestComponent extends AbstractComponent {

    @DialogField(fieldLabel = "Test Title", fieldDescription = "This is the test title.")
    @TextField
    @Inject
    String title
}
