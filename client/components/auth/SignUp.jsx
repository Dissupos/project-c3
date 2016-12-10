import React from 'react';
import Logo from '../navbar/Logo.jsx';
import ApiService from '../../services/ApiService.js';
import ErrorHandler from '../../services/ErrorHandler.js';
import NotificationService from '../../services/NotificationService.js';
import {
    STUDENT,
    PROFESSOR,
    COMPANY,
    MALE,
    FEMALE
} from '../../constants/Constants.js';

export default class SignUp extends React.Component {

    static contextType = {
        router: React.PropTypes.object
    };

    constructor() {
        super();

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        let data = {
            firstName: this.refs.firstName.value,
            lastName: this.refs.lastName.value,
            sex: this.refs.sex.value,
            country: this.refs.country.value,
            city: this.refs.city.value,
            username: this.refs.username.value,
            password: this.refs.password.value,
            email: this.refs.email.value,
            accountType: this.refs.accountType.value
        };

        /*
        if (data.accountType === STUDENT || data.accountType === PROFESSOR) {
            data.universityName = this.refs.universityName.value;
        }

        if (data.accountType === COMPANY) {
            data.companyName = this.refs.companyName.value;
        }*/

        ApiService.register(data)
    }

    render() {
        return (
            <div id="content" className="auth sign-up">
                <div className="form-container">
                    <Logo
                        size="small"
                        showWords={true} />

                    <form role="form" onSubmit={this.handleSubmit}>
                        <div className="row">
                            <div className="col-xs-6">
                                <div className="form-group">
                                    <label>Firstname:</label>
                                    <input id="login-input" type="text" ref="firstName" className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label>Username:</label>
                                    <input id="login-input" type="text" ref="username" className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label>Country:</label>
                                    <input id="login-input" type="text" ref="country" className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label>Email:</label>
                                    <input id="login-input" type="text" ref="email" className="form-control"/>
                                </div>
                            </div>
                            <div className="col-xs-6">
                                <div className="form-group">
                                    <label>Lastname:</label>
                                    <input id="login-input" type="text" ref="lastName" className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label>Password:</label>
                                    <input id="password-input" type="text" ref="password" className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label>City:</label>
                                    <input id="login-input" type="text" ref="city" className="form-control"/>
                                </div>
                                <div className="form-group">
                                    <label>Account type:</label>
                                    <input id="login-input" type="text" ref="account-type" className="form-control"/>
                                </div>
                            </div>
                        </div>

                        <button type="submit" className="btn btn-primary">
                            Sign up
                        </button>
                    </form>
                </div>
            </div>
        );
    }
}