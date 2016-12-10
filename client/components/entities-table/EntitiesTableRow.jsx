import React from 'react';
import { OFFER, MEETUP } from '../../constants/Constants.js';

export default class EntitiesTableRow extends React.Component {

    static propTypes = {
        item: React.PropTypes.object.isRequired,
        entityType: React.PropTypes.string.isRequired
    };

    constructor() {
        super();
    }

    render() {
        return (
            <tr className="offer-item">
                <td>{this.props.item.title}</td>
                <td>{this.props.item.status}</td>
                <td>{this.props.item.category}</td>
                <td>{this.props.item.address.city}</td>
                <td>{this.props.entityType === OFFER ? this.props.item.company.name : this.props.item.meetupDate}</td>
                <td>Actions</td>
            </tr>
        );
    }
}