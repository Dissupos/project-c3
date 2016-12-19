import NotificationService from './NotificationService.js';

const errorHandler = (reason) => {
    let error = JSON.parse(reason.responseText);

    if (Array.isArray(error.errors)) {
        error.errors.forEach((e) => {
           NotificationService.error({
               title: error.error,
               message: e.defaultMessage
           });
        });

        return;
    }

    let message = error.message || 'Some error has occurred on the server, please try later.';
    let title = error.error || 'Error';

    NotificationService.error({
        title: title,
        message: message
    });
};

export default errorHandler;