import React from 'react';
import ApiService from '../../services/ApiService.js';
import ErrorHandler from '../../services/ErrorHandler.js';

export default class OfferDetails extends React.Component {

    constructor() {
        super();

        this.state = {
            offer: null
        };
    }

    componentDidMount() {
        let id = this.props.params && this.props.params.id;
        if (id) {
            ApiService.getOffer(id).then((response) => {
                this.setState({
                    offer: response
                });
            }).catch(ErrorHandler);
        }
    }

    render() {
        let offer = this.state.offer;
        let view = offer ? (
            <div id="content" className="offer-detail" style={{color: 'white'}}>
                <h1>Offer detail</h1>
                <div>Title: {offer.title}</div>
                <div>Company: {offer.company.name}</div>
                <div>Category: {offer.category}</div>
                <div>Description: {offer.description}</div>
                <div>Professor: {offer.professor && (`${offer.professor.firstName} ${offer.professor.lastName}`)}</div>
                <div>Student: {offer.student && (`${offer.student.firstName} ${offer.student.lastName}`)}</div>
                <div>Address: {`${offer.address.country}, ${offer.address.city}`}</div>
            </div>
        ) : <div>Not available</div>
        return view;
    }
}