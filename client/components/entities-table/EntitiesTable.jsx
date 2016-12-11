import React from 'react';
import { OFFER, MEETUP } from '../../constants/Constants.js';
import EntitiesTableRow from './EntitiesTableRow.jsx';

export default class EntitiesTable extends React.Component {

    static propTypes = {
        items: React.PropTypes.array.isRequired,
        entityType: React.PropTypes.string.isRequired
    };

    constructor() {
        super();
    }

    render() {
        let rows = this.props.items.map((val, index) => {
            return (
                <EntitiesTableRow
                    item={val}
                    entityType={this.props.entityType}
                    key={index} />
            )
        });

        let view = this.props.items.length > 0 ? (
            <div className="entities-list-container">
                <table className="table table-responsive table-striped">
                    <thead>
                    <tr>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Category</th>
                        <th>Address</th>
                        { this.props.entityType === OFFER ? <th>Company</th> : null}
                        { this.props.entityType === MEETUP ? <th>Meetup date</th> : null}
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {rows}
                    </tbody>
                </table>
            </div>
        ) :  <div>{`Sorry, we haven't found any ${this.props.entityType.toLowerCase()}s`}</div>;

        return view;
    }
}
