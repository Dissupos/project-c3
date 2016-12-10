import React from 'react';
import Logo from '../navbar/Logo.jsx';
import ApiService from '../../services/ApiService.js';
import ErrorHandler from '../../services/ErrorHandler.js';
import IdentityService from '../../services/IdentityService.js';
import NotificationService from '../../services/NotificationService.js';

export default class SignIn extends React.Component {

    static contextTypes = {
        router: React.PropTypes.object
    };

    constructor() {
        super();

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        ApiService.login({
            username: this.refs.username.value,
            password: this.refs.password.value
        }).then((response) => {
            IdentityService.setUserIdentity(response);
            this.context.router.push({
                path: '/'
            });
            NotificationService.success({message: 'You have successfully loged in.'});
        }).catch(ErrorHandler);
    }

    render() {
        return (
            <div id="content" className="auth">
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
                            <input id="password-input" type="password" ref="password" className="form-control"/>
                        </div>

                        <button type="submit" className="btn btn-primary">
                            Sign in
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}