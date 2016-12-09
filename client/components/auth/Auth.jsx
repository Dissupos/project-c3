import React from 'react';

export default class Auth extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div id="content" className="auth">
                {this.props.children}
            </div>
        );
    }
}