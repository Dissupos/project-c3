import React from 'react';
import EntitiesTable from '../entities-table/EntitiesTable.jsx';
import {OFFER} from '../../constants/Constants.js';
import ApiService from '../../services/ApiService.js';
import ErrorHandler from '../../services/ErrorHandler.js';

export default class OffersList extends React.Component {

    static propTypes = {
        queryParams: React.PropTypes.object.isRequired
    };

    constructor() {
        super();

        this.state = {
            items: []
        };
    }

    componentDidMount() {
        ApiService.getOffers(this.props.queryParams).then((response) => {
            this.setState({
                items: response
            });
        }).catch(ErrorHandler);
    }

    render() {
        return (
            <EntitiesTable items={this.state.items} entityType={OFFER} />
        );
    }
}