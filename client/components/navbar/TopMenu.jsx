import React from 'react';
import { Link } from 'react-router';
import IdentityService from '../../services/IdentityService.js';
import ApiService from '../../services/ApiService.js';

export default class TopMenu extends React.Component {

    constructor() {
        super();

        this.state = {
            user: null
        };

        this.logout = this.logout.bind(this);
        this.update = this.update.bind(this);

        IdentityService.subscribe(this);
    }

    update(user) {
        this.setState({
            user: user
        });
    }

    logout() {
        ApiService.logout().then(() => {
           window.location.reload();
        });
    }

    render() {
        return (
            <div className="top-menu pull-right">
                <ul>
                    {!this.state.user ? <li className="top-menu-item">
                        <Link to="sign-in">Sign in</Link>
                    </li> : null }
                    {!this.state.user ? <li className="top-menu-item">
                        <Link to="sign-up">Sign up</Link>
                    </li> : null }
                    {this.state.user ? <li className="top-menu-item">
                        <Link to="account">Account</Link>
                    </li> : null}
                    <li className="top-menu-item">
                        <Link to="/meetups">Meetups</Link>
                    </li>
                    <li className="top-menu-item">
                        <Link to="/offers">Offers</Link>
                    </li>
                    <li className="top-menu-item">
                        <Link to="about">About</Link>
                    </li>
                    {this.state.user ? <li className="top-menu-item">
                        <a href="javascript:void(0)" onClick={this.logout} >Log out</a>
                    </li> : null}
                </ul>
            </div>
        );
    }
}
