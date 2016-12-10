import NotificationService from './NotificationService.js';

const errorHandler = (reason) => {
    let message = reason.message || 'Some error has occurred.';
    let title = reason.error || 'Error';

    NotificationService.error({
        title: title,
        message: message
    });
};

export default errorHandler;