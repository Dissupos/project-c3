import React from 'react';
import IdentityService from '../../services/IdentityService.js';

import {MALE, COMPANY, STUDENT, PROFESSOR} from '../../constants/Constants.js';

export default class AccountPage extends React.Component {
    constructor() {
        super();

        this.state = {
            account: null
        };
    }

    componentDidMount() {
        IdentityService.getUserIdentity().then((identity) => {
            this.setState({
                account: identity
            });
        });
    }

    render() {
        let account = this.state.account;
        let view = account ? (
            <div id="content" className="account-page">
                <div className="account-page-container">
                    <div className="row">
                        <div className="col-sm-6 col-sm-offset-3 col-xs-12 col-xs-offset-0">

                            <div className="account-page-data">
                                <div className="c3-modal-header">My account</div>
                                <div className="c3-modal-content">
                                    <div className="data-block">
                                        <div className="header">Personal data</div>
                                        <hr />
                                        <div className="row">
                                            <div className="col-md-6 col-xs-12">
                                                <div><strong>Firstname:</strong> {account.person.firstName}</div>
                                            </div>
                                            <div className="col-md-6 col-xs-12">
                                                <div><strong>Lastname:</strong> {account.person.lastName}</div>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-md-6 col-xs-12">
                                                <div><strong>Gender:</strong> {account.person.sex === MALE ? 'Male' : 'Female'}</div>
                                            </div>
                                            <div className="col-md-6 col-xs-12">
                                                <div><strong>Address:</strong> {`${account.person.country}, ${account.person.city}`}</div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="data-block">
                                        <div className="header">Account data</div>
                                        <hr />
                                        <div className="row">
                                            <div className="col-md-6 col-xs-12">
                                                <div><strong>Username:</strong> {account.username}</div>
                                            </div>
                                            <div className="col-md-6 col-xs-12">
                                                <div><strong>Email:</strong> {account.email}</div>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-md-6 col-xs-12">
                                                <div><strong>Created at:</strong> {(new Date(account.createdDate)).toDateString()}</div>
                                            </div>
                                            <div className="col-md-6 col-xs-12">
                                                <div><strong>Last modified:</strong> {(new Date(account.lastModifiedDate)).toDateString()}</div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="data-block">
                                        <div className="header">Account type</div>
                                        <hr />
                                        <div><strong>Type:</strong> {account.type}</div>
                                    </div>

                                    {account.type === COMPANY ? (
                                        <div className="data-block">
                                            <div className="header">Company data</div>
                                            <hr />
                                            <div><strong>Company name:</strong> {account.company.name}</div>
                                        </div>
                                    ) : null}

                                    {(account.type === STUDENT || account.type === PROFESSOR) ? (
                                        <div className="data-block">
                                            <div className="header">Univesity data</div>
                                            <hr />
                                            <div><strong>University name:</strong> {account.university.name}</div>
                                        </div>
                                    ) : null}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        ) : <div>Not logged in!</div>;
        return view;
    }
}