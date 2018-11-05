$(document).ready(() => {
    $('#sensors-dropdown').dropdown('set selected', sensorId.toString());
    if (initialPriority != null) {
        const $checkbox = $('#priority-' + initialPriority);
        $checkbox.prop('checked', true);
    }
});