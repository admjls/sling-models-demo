package com.icfolson.sling.models.exporters

import com.amazonaws.services.polly.model.Voice

interface AudioDescriptionExporter {

    String NAME = "audio-description"

    String VOICE_ID = "voiceId"

    List<Voice> getVoices()
}