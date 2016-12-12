import React from 'react';
import OffersList from './offers/OffersList.jsx';
import MeetupsList from './meetups/MeetupsList.jsx';

export default class Home extends React.Component {
    constructor() {
        super();

        this.state = {
            offersQuery: {
                page: 0,
                size: 5,
                sort: 'createdDate,desc'
            },
            meetupsQuery: {
                page: 0,
                size: 5,
                sort: 'createdDate,desc'
            }
        };
    }

    render() {
        return (
            <div id="home-page" className="home-page">
                <div className="row">
                    <div className="col-xs-12">
                        <div className="welcome-container">
                            <div className="c3-modal-header">
                                Welcome to the C<sup>3</sup> website!
                            </div>
                            <div className="main-text">
                                Are you a representative of your company, searching for the clear minds to help you with your projects?
                                Or are you a student trying to gain some knowledge or add some project to your portfolio?
                                Or, maybe, you are a university teacher, eager to help students with their first steps in real-world project?
                                And if any of the sentences above makes sense for you, than you are on the right page.
                                <br /><strong>C<sup>3</sup> Create! Contribute! Communicate!</strong>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6 col-sm-12">
                        <div className="offers-container">
                            <div className="c3-modal-header">Latest offers</div>
                            <OffersList queryParams={this.state.offersQuery} />
                        </div>
                    </div>
                    <div className="col-md-6 col-sm-12">
                        <div className="meetups-container">
                            <div className="c3-modal-header">Nearest meetups</div>
                            <MeetupsList queryParams={this.state.meetupsQuery} />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
