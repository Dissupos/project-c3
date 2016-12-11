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
                <div className="account-page-container" style={{color: 'white'}}>\
                    <div className="row">
                        <div className="col-md-4 col-md-offset-4 col-xs-12 col-xs-offset-0">
                            <h2>My account</h2>

                            <div>Personal data</div>
                            <div>Firstname: {account.person.firstName}</div>
                            <div>Lastname: {account.person.lastName}</div>
                            <div>Gender: {account.person.sex === MALE ? 'Male' : 'Female'}</div>
                            <div>Address: {`${account.person.country}, ${account.person.city}`}</div>

                            <div>Account data</div>
                            <div>Username: {account.username}</div>
                            <div>Email: {account.email}</div>
                            <div>Type: {account.type}</div>
                            <div>Created at: {(new Date(account.createdDate)).toDateString()}</div>
                            <div>Last modified: {(new Date(account.lastModifiedDate)).toDateString()}</div>

                            {account.type === COMPANY && <div>Company data</div>}
                            {account.type === COMPANY && <div>Company name: {account.company.name}</div>}

                            {(account.type === STUDENT || account.type === PROFESSOR) && <div>University data</div>}
                            {(account.type === STUDENT || account.type === PROFESSOR) && <div>University name: {account.company.name}</div>}
                        </div>
                    </div>
                </div>
            </div>
        ) : <div>Not logged in!</div>;
        return view;
    }
}