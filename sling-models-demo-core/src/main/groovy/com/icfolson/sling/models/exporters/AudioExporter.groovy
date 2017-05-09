package com.icfolson.sling.models.exporters

import com.amazonaws.services.polly.model.Voice

interface AudioExporter {

    String NAME = "audio"

    String VOICE_ID = "voiceId"

    List<Voice> getVoices()
}