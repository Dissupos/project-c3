import React from 'react';
import { NotificationStack } from 'react-notification';

import C3Header from './navbar/C3Header.jsx';

import '../../node_modules/bootstrap/less/bootstrap.less';
import '../less/project-c3.less';
import '../img/logo.png';
import '../img/logo-full.png';
import '../img/rain.png';

export default class Main extends React.Component {
    render() {
        return (
            <div id="main-layout">
                <C3Header />
                <div id="content">{this.props.children}</div>
            </div>
        );
    }
}
