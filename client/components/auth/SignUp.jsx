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

        this.userRole = null;

        this.handleRoleChange = this.handleRoleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleRoleChange(e) {
        this.userRole = e.target.value;
        this.forceUpdate();
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


        if (data.accountType === STUDENT || data.accountType === PROFESSOR) {
            data.universityName = this.refs.university.value;
        }

        if (data.accountType === COMPANY) {
            data.companyName = this.refs.company.value;
        }

        ApiService.register(data).then(() => {
            NotificationService.success({
                title: 'Registered',
                message: 'Your account have been successfully registered. Now you can log in.'
            });
            this.context.router.push({
                path: '/sign-in'
            });
        }).catch(ErrorHandler);
    }

    render() {
        return (
            <div id="content" className="auth sign-up">
                <div className="form-container">

                    <form role="form" onSubmit={this.handleSubmit}>
                        <div className="row">
                            <div className="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1 col-xs-12 col-xs-offset-0 heading">
                                Personal data
                                <hr />
                            </div>
                        </div>

                        <div className="row">
                            <div className="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1 col-xs-12 col-xs-offset-0">
                                <div className="form-group">
                                    <label htmlFor="firstname-input">Firstname:</label>
                                    <input id="firstname-input" type="text" ref="firstName" required className="form-control"/>
                                </div>

                                <div className="form-group">
                                    <label htmlFor="gender-input">Gender</label>
                                    <select id="gender-input" className="form-control" required defaultValue="" ref="sex">
                                        <option value="" disabled>Select your gender</option>
                                        <option value={MALE}>Male</option>
                                        <option value={FEMALE}>Female</option>
                                    </select>
                                </div>

                                <div className="form-group">
                                    <label htmlFor="country-input">Country:</label>
                                    <input id="country-input" type="text" ref="country" required className="form-control"/>
                                </div>

                            </div>
                            <div className="col-md-4 col-md-offset-0 col-sm-5 col-xs-12 col-xs-offset-0">
                                <div className="form-group">
                                    <label htmlFor="lastname-input">Lastname:</label>
                                    <input id="lastname-input" type="text" ref="lastName" required className="form-control"/>
                                </div>

                                <div className="form-group">
                                    <label htmlFor="email-input">Email:</label>
                                    <input id="email-input" type="text" ref="email" required className="form-control"/>
                                </div>

                                <div className="form-group">
                                    <label htmlFor="city-input">City:</label>
                                    <input id="city-input" type="text" ref="city" required className="form-control"/>
                                </div>

                            </div>
                        </div>

                        <div className="row">
                            <div className="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1 col-xs-12 col-xs-offset-0 heading">
                                Account data
                                <hr />
                            </div>
                        </div>

                        <div className="row">
                            <div className="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1 col-xs-12 col-xs-offset-0">
                                <div className="form-group">
                                    <label htmlFor="username-input">Username:</label>
                                    <input id="username-input" type="text" ref="username" required className="form-control"/>
                                </div>
                            </div>
                            <div className="col-md-4 col-md-offset-0 col-sm-5 col-sm-offset-0 col-xs-12 col-xs-offset-0">
                                <div className="form-group">
                                    <label htmlFor="password-input">Password:</label>
                                    <input id="password-input" type="password" ref="password" required className="form-control"/>
                                </div>
                            </div>
                        </div>


                        <div className="row">
                            <div className="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1 col-xs-12 col-xs-offset-0 heading">
                                Account type
                                <hr />
                            </div>
                        </div>

                        <div className="row">
                            <div className="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1 col-xs-12 col-xs-offset-0">
                                <div className="form-group">
                                    <label htmlFor="account-type-input">Account type:</label>
                                    <select id="account-type-input" ref="accountType" required defaultValue="" className="form-control" onChange={this.handleRoleChange}>
                                        <option value="" disabled>Tell us who are you...</option>
                                        <option value={STUDENT}>Student</option>
                                        <option value={PROFESSOR}>University professor</option>
                                        <option value={COMPANY}>Company representative</option>
                                    </select>
                                </div>
                            </div>
                            {this.userRole === STUDENT || this.userRole === PROFESSOR ? (
                                <div className="col-md-4 col-md-offset-0 col-sm-5 col-sm-offset-0 col-xs-12 col-xs-offset-0">
                                    <div className="form-group">
                                        <label htmlFor="university-input">University:</label>
                                        <input id="university-input" type="text" required ref="university" className="form-control"/>
                                    </div>
                                </div>
                            ) : null}

                            {this.userRole === COMPANY ? (
                                <div className="col-md-4 col-md-offset-0 col-sm-5 col-sm-offset-0 col-xs-12 col-xs-offset-0">
                                    <div className="form-group">
                                        <label htmlFor="company-input">Company:</label>
                                        <input id="company-input" type="text" required ref="company" className="form-control"/>
                                    </div>
                                </div>
                            ) : null}
                        </div>

                        <div className="row">
                            <div className="col-md-4 col-md-offset-2 col-sm-5 col-sm-offset-1 col-xs-12 col-xs-offset-0">
                                <button type="submit" className="btn btn-primary">
                                    Sign up
                                </button>
                            </div>
                        </div>


                    </form>
                </div>
            </div>
        );
    }
}