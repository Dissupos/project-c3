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
                    <div className="col-xs-12">
                        <div className="main-text">
                            Welcome to the C<sup>3</sup> website!
                            Are you a representative of your company, searching for the clear minds to help you with your projects?
                            Or are you a student trying to gain some knowledge or add some project to your portfolio?
                            Or, maybe, you are a university teacher, eager to help students with their first steps in real-world project?
                            And if any of the sentences above makes sense for you, than you are on the right page.
                            <br /><strong>C<sup>3</sup> Create! Contribute! Communicate!</strong>
                        </div>
                    </div>
                    <div className="col-md-6 col-sm-12">
                        <div className="offers-container">
                            <h3>Latest offers</h3>
                            <OffersList />
                        </div>
                    </div>
                    <div className="col-md-6 col-sm-12">
                        <div className="meetups-container">
                            <h3>Nearest meetups</h3>
                            <MeetupsList />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
