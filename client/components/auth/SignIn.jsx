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
            <div id="content" className="auth sign-in">
                <div className="form-container">
                    <div className="row">
                        <div className="col-sm-4 col-sm-offset-4 col-xs-offset-3">
                            <Logo
                                size="big"
                                showWords={true} />
                        </div>
                    </div>

                    <form role="form" onSubmit={this.handleSubmit}>
                        <div className="row">
                            <div className="col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3">
                                <div className="form-group">
                                    <label>Login:</label>
                                    <input id="login-input" type="text" ref="username" className="form-control" required/>
                                </div>
                            </div>
                        </div>

                        <div className="row">
                            <div className="col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3">
                                <div className="form-group">
                                    <label>Password:</label>
                                    <input id="password-input" type="password" ref="password" className="form-control" required/>
                                </div>
                            </div>
                        </div>

                        <div className="row">
                            <div className="col-sm-4 col-sm-offset-4 col-xs-6 col-xs-offset-3">
                                <button type="submit" className="btn btn-primary">
                                    Sign in
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}