$(document).ready(() => {

    $unitLabel = $('#unit-label');

    $('#sensors-dropdown')
        .dropdown({
            onChange: (id, text) => {
                const sensor = sensors.find(s => s.id == id);
                console.log(sensor)
                if (!sensor) return;
                const unit = units[sensor.type];
                if (!unit) return;
                $unitLabel.text(unit);
            }
        })
        .dropdown('set selected', sensorId.toString());
    if (initialPriority != null) {
        const $checkbox = $('#priority-' + initialPriority);
        $checkbox.prop('checked', true);
    }


});