package com.icfolson.sling.models.services.impl

import org.apache.sling.models.export.spi.ModelExporter
import org.apache.sling.models.factory.ExportException
import org.osgi.service.component.annotations.Component

@Component(service = ModelExporter)
class AudioModelExporter implements ModelExporter {

    @Override
    boolean isSupported(Class<?> clazz) {
        clazz == InputStream
    }

    @Override
    <T> T export(Object model, Class<T> clazz, Map<String, String> options) throws ExportException {
        return null
    }

    @Override
    String getName() {
        "audio"
    }
}