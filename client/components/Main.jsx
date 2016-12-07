import React from 'react';

import C3Header from './C3Header/C3Header.jsx';

import '../../node_modules/bootstrap/less/bootstrap.less';
import '../less/project-c3.less';

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
