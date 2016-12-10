import React from 'react';
import EntitiesTable from '../entities-table/EntitiesTable.jsx';
import {MEETUP} from '../../constants/Constants.js';
import ApiService from '../../services/ApiService.js';
import ErrorHandler from '../../services/ErrorHandler.js';

export default class OffersList extends React.Component {
    constructor() {
        super();

        this.state = {
            items: []
        };
    }

    componentDidMount() {
        ApiService.getMeetups({}).then((response) => {
            this.setState({
                items: response
            });
        }).catch(ErrorHandler);
    }

    render() {
        return (
            <EntitiesTable items={this.state.items} entityType={MEETUP} />
        );
    }
}