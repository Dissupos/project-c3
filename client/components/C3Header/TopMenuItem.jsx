import React from 'react';
import { Link } from 'react-router';

export default class TopMenuItem extends React.Component {

    static propTypes = {
        link: React.PropTypes.string.isRequired,
        name: React.PropTypes.string.isRequired
    };

    constructor() {
        super();
    }

    render() {
        return (
            <li className="top-menu-item">
                <Link to={this.props.link}>{this.props.name}</Link>
            </li>
        );
    }
}
