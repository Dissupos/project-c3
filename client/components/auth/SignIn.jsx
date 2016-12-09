import React from 'react';
import Logo from '../navbar/Logo.jsx';
import ApiService from '../../services/ApiService.js';
import IdentityService from '../../services/IdentityService.js';

export default class SignIn extends React.Component {

    static contextTypes = {
        router: React.PropTypes.object
    };

    constructor() {
        super();

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit() {
        ApiService.login({
            username: this.refs.username.value,
            password: this.refs.password.value
        }).then((response) => {
            IdentityService.setUserIdentity(response.data);
            this.context.router.push({
                path: '/'
            });
        }).catch();
    }

    render() {
        return (
            <div className="form-container">
                <Logo
                    size="big"
                    showWords={true} />

                <form role="form" onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label>Login:</label>
                        <input id="login-input" type="text" ref="username" className="form-control"/>
                    </div>

                    <div className="form-group">
                        <label>Password:</label>
                        <input id="password-input" type="text" ref="password" className="form-control"/>
                    </div>

                    <button type="submit" className="btn btn-primary" >
                        Sign in
                    </button>
                </form>
            </div>
        );
    }
}