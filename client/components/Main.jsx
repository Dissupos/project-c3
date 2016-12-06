import React from 'react';

import Navbar from './navbar/Navbar.jsx';

import '../../node_modules/bootstrap/less/bootstrap.less';
import '../less/project-c3.less';

export default class Main extends React.Component {
    render() {
        return (
            <div id="main-layout">
                <Navbar />
                {this.props.children}
            </div>
        );
    }
}
