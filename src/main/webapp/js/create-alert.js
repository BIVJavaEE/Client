const onDocumentReady = () => {
    $('.ui.dropdown').dropdown();
    if (initialPriority != null) {
        const $checkbox = $('#priority-' + initialPriority);
        $checkbox.prop('checked', true);
    }
};
$(document).ready(onDocumentReady);