$(function () {
    $(document).on('change', '#voice', function () {
        var voiceId = $(this).val();

        console.log(voiceId);

        var source = $('#voice-source');
        var resourcePath = source.data('resource');

        source.attr('src', resourcePath + '.audio.' + voiceId + '.mp3');
        source.parent().load();
    });
});