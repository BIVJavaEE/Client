const onDocumentReady = () => {
    $('.ui.dropdown').dropdown('set selected', sensorId);
    if (initialPriority != null) {
        const $checkbox = $('#priority-' + initialPriority);
        $checkbox.prop('checked', true);
    }
};
$(document).ready(onDocumentReady);