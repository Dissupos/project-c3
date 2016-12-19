import React from 'react';
import ApiService from '../../services/ApiService';
import ErrorHandler from '../../services/ErrorHandler';
import Status from '../Status.jsx';
import EntitiesButtons from '../entities-table/EntitiesButtons.jsx';
import {MEETUP} from '../../constants/Constants.js';

export default class MeetupDetails extends React.Component {

    constructor() {
        super();

        this.state = {
            meetup: null
        };
    }

    componentDidMount() {
        let id = this.props.params && this.props.params.id;
        if (id) {
            ApiService.getMeetup(id).then((response) => {
                console.log(response);
                this.setState({
                    meetup: response
                });
            }).catch(ErrorHandler);
        }
    }

    render() {
        let meetup = this.state.meetup;
        return meetup ? (
            <div id="content" className="meetup-detail">
                <div className="meetup-details-container">
                    <div className="row">
                        <div className="col-sm-8 col-sm-offset-2 col-xs-12 col-xs-offset-0">
                            <div className="meetup-data">
                                <div className="c3-modal-header">Meetup detail</div>
                                <div className="c3-modal-content">
                                    <div className="data-block">
                                        <div className="header">Main details</div>
                                        <hr />
                                        <div className="row">
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Title:</strong> {meetup.title}</div>
                                            </div>
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Creator:</strong> {`${meetup.creator.person.firstName} ${meetup.creator.person.lastName}`}</div>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Category:</strong> {meetup.category}</div>
                                            </div>
                                            <div className="col-sm-6 col-xs-12">
                                                <div><strong>Address:</strong> {`${meetup.address.country}, ${meetup.address.city}`}</div>
                                            </div>
                                        </div>
                                        <div className="row">
                                            <div className="col-sm-6 col-xs-6">
                                                <div><strong>Status:</strong> <Status status={meetup.status} /></div>
                                            </div>
                                            <div className="col-sm-6 col-xs-6">
                                                <div><strong>Meetup date:</strong> {`${new Date(meetup.meetupDate).toGMTString()}`}</div>
                                            </div>
                                        </div>
                                    </div>

                                    <div className="data-block">
                                        <EntitiesButtons entityType={MEETUP} entity={meetup} />
                                    </div>

                                    <div className="data-block">
                                        <div className="header">Description</div>
                                        <div className="row">
                                            <div className="col-xs-12">
                                                {meetup.description}
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