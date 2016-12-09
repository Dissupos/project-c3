let instance = null;
let subscribers = [];

class NotificationService {
    constructor() {
        if (!instance) {
            instance = this;
        }

        this.setNotificationStack = this.setNotificationStack.bind(this);

        return instance;
    }

    subscribe(subscriber) {
        subscribers.push(subscriber);
    }

    addNotification(notification) {
        subscribers.forEach((sub) => {
           sub.addNotification(notification);
        });
    }
}

export default new NotificationService();