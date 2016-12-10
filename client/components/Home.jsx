import React from 'react';
import OffersList from './offers/OffersList.jsx';
import MeetupsList from './meetups/MeetupsList.jsx';

export default class Home extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <div id="home-page" className="home-page">
                <div className="row">
                    <div className="col-md-6 col-sm-12">
                        <div className="offers-container">
                            <OffersList />
                        </div>
                    </div>
                    <div className="col-md-6 col-sm-12">
                        <div className="meetups-container">
                            <MeetupsList />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
