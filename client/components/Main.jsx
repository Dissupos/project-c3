import React from 'react';
import { NotificationStack } from 'react-notification';
import NotificationService from '../services/NotificationService.js';

import C3Header from './navbar/C3Header.jsx';

import '../../node_modules/jquery/dist/jquery.js';
import '../../node_modules/bootstrap/less/bootstrap.less';
import '../../node_modules/bootstrap/dist/js/bootstrap.min.js';
import '../less/project-c3.less';
import '../img/logo.png';
import '../img/logo-full.png';
import '../img/rain.png';

export default class Main extends React.Component {
    constructor() {
        super();

        this.state = {
            notifications: []
        };

        this.addNotification = this.addNotification.bind(this);
        this.removeNotification =  this.removeNotification.bind(this);
    }

    componentDidMount() {
        NotificationService.subscribe(this);
    }

    /**
     * Add notification to the stack of notifications.
     *
     * @param {Object} notification
     */
    addNotification(notification) {
        this.setState({
            notifications: this.state.notifications.concat(notification)
        });
    }

    removeNotification(notification) {
        let notifications = this.state.notifications;
        let index = notifications.indexOf(notification);

        if (index >= 0) {
            notifications.splice(index, 1);
        }

        this.setState({
            notifications: notifications
        });
    }

    render() {
        return (
            <div id="main-layout">
                <C3Header />
                <div id="content">{this.props.children}</div>
                <NotificationStack
                    notifications={this.state.notifications}
                    onDismiss={this.removeNotification} />
            </div>
        );
    }
}
