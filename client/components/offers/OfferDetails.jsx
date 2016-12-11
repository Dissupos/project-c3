import React from 'react';
import ApiService from '../../services/ApiService.js';
import ErrorHandler from '../../services/ErrorHandler.js';

export default class OfferDetails extends React.Component {

    constructor() {
        super();

        this.state = {
            offer: {}
        };
    }

    componentDidMount() {
        let id = this.props.location && this.props.location.query && this.props.location.query.id;
        if (id) {
            ApiService.getOffer(id).then((response) => {
                this.setState({
                    offer: response
                });
            }).catch(ErrorHandler);
        }
    }

    render() {
        return (
            <div className="offer-detail">

            </div>
        );
    }
}