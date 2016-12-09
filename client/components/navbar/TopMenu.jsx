import React from 'react';
import { Link } from 'react-router';

export default class TopMenu extends React.Component {

    constructor() {
        super();
    }

    render() {
        return (
            <div className="top-menu pull-right">
                <ul>
                    <li className="top-menu-item">
                        <Link to="auth/sign-in">Sign in</Link>
                    </li>
                    <li className="top-menu-item">
                        <Link to="auth/sign-up">Sign up</Link>
                    </li>
                    <li className="top-menu-item">
                        <Link to="/meetups">Meetups</Link>
                    </li>
                    <li className="top-menu-item">
                        <Link to="/offers">Offers</Link>
                    </li>
                    <li className="top-menu-item">
                        <Link to="about">About</Link>
                    </li>
                </ul>
            </div>
        );
    }
}
