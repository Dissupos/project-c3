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

        this.getItems = this.getItems.bind(this);
    }

    componentWillReceiveProps(newProps) {
        this.getItems(newProps.queryParams);
    }

    getItems(queryParams) {
        ApiService.getOffers(queryParams).then((response) => {
            this.setState({
                items: response
            });
        }).catch(ErrorHandler);
    }

    componentDidMount() {
        this.getItems(this.props.queryParams);
    }

    render() {
        return (
            <EntitiesTable items={this.state.items} entityType={OFFER} />
        );
    }
}