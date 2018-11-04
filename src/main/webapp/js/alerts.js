const onDocumentReady = () => {

    const $modal = $('#delete-confirmation-modal');

    const openDeletionModal = (alertId, alertName) => {
        const $text = $('#deletion-modal-content');
        $text.html($text.html().replace('{{alertName}}', alertName));
        $modal
            .modal({
                onApprove: () => {
                    $.post({
                        url: '/alerts',
                        type: 'POST',
                        data: {id: alertId},
                        success: () => location.reload()
                    });
                }
            })
            .modal('show');
    };

    const $deleteAlertButtons = $('.delete-alert');
    $deleteAlertButtons.on('click', (event) => {
        const $clickedButton = $(event.target);
        const alertId = $clickedButton.attr('alert-id');
        const alertName = $clickedButton.attr('alert-name');
        openDeletionModal(alertId, alertName);
    });

};
$(document).ready(onDocumentReady);