import React from 'react';
import ApiService from '../../services/ApiService.js';
import ErrorHandler from '../../services/ErrorHandler.js';
import Status from '../Status.jsx';

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
        return offer ? (
            <div id="content" className="offer-detail">
                <div className="offer-details-container">
                    <div className="row">
                        <div className="col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
                            <div className="offer-data">
                                <div className="c3-modal-header">Offer detail</div>
                                <div className="c3-modal-content">
                                    <div className="data-block">
                                        <div className="header">Main details</div>
                                        <hr />
                                        <div className="row">
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Title:</strong> {offer.title}</div>
                                            </div>
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Company:</strong> {offer.company.name}</div>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Category:</strong> {offer.category}</div>
                                            </div>
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Address:</strong> {`${offer.address.country}, ${offer.address.city}`}</div>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-sm-6 col-xs-6">
                                                <div><strong>Status:</strong> <Status status={offer.status} /></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="data-block">
                                        <div className="header">Assigned users</div>
                                        <div className="row">
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Professor:</strong> {offer.professor && (`${offer.professor.firstName} ${offer.professor.lastName}`)}</div>
                                            </div>
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Student:</strong> {offer.student && (`${offer.student.firstName} ${offer.student.lastName}`)}</div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="data-block">
                                        <div className="header">Description</div>
                                        <div className="row">
                                            <div className="col-xs-12">
                                                {offer.description}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        ) : <div>Not available</div>;
    }
}