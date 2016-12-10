let instance = null;
let subscribers = [];

const defaultStyle = {};

class NotificationService {

    static count = 0;

    constructor() {
        if (!instance) {
            instance = this;
        }

        this.addNotification = this.addNotification.bind(this);
        this.closeNotification = this.closeNotification.bind(this);
        this.error = this.error.bind(this);
        this.subscribe = this.subscribe.bind(this);
        this.success = this.success.bind(this);

        return instance;
    }

    subscribe(subscriber) {
        subscribers.push(subscriber);
    }

    success(config) {
        let notification = Object.assign({
            isActive: true,
            key: NotificationService.count++,
            message: 'Successfully done.',
            title: 'Success',
            action: 'Dismiss',
            className: 'success-notification',
            dismissAfter: 4000,
            barStyle: defaultStyle
        }, config);

        notification.onClick = (deactivate) => {
            deactivate();
            setTimeout(() => this.closeNotification(notification), 400);
        };

        this.addNotification(notification);
    }

    error(config) {
        let notification = Object.assign({
            isActive: true,
            key: NotificationService.count++,
            message: 'Some error has occurred.',
            title: 'Error',
            action: 'Dismiss',
            className: 'error-notification',
            dismissAfter: 4000,
            barStyle: defaultStyle
        }, config);

        notification.onClick = (deactivate) => {
            deactivate();
            setTimeout(() => this.closeNotification(notification), 400);
        };

        this.addNotification(notification);
    }

    addNotification(notification) {
        subscribers.forEach((sub) => {
           sub.addNotification(notification);
        });
    }

    closeNotification(notification) {
        subscribers.forEach((sub) => {
            sub.removeNotification(notification);
        });
    }
}

export default new NotificationService();