import React from 'react';
import { Router, Route, IndexRoute, hashHistory, Redirect } from 'react-router';

/** components */
import Main from './components/Main.jsx';
import Home from './components/Home.jsx';
import About from './components/about/About.jsx';
import SignIn from './components/auth/SignIn.jsx';
import SignUp from './components/auth/SignUp.jsx';
import MeetupsPage from "./components/meetups/MeetupsPage.jsx";
import OffersPage from "./components/offers/OffersPage.jsx";
import OfferDetails from './components/offers/OfferDetails.jsx';
import MeetupDetails from './components/meetups/MeetupDetails.jsx';
import AccountPage from './components/account/AccountPage.jsx';

export default class Root extends React.Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Route path='/' component={Main}>
                    <IndexRoute component={Home} />
                    <Route path="sign-in" component={SignIn} />
                    <Route path="sign-up" component={SignUp} />
                    <Route path="about" component={About} />
                    <Route path="meetups" component={MeetupsPage} />
                    <Route path="offers" component={OffersPage} />
                    <Route path="meetups/:id" component={MeetupDetails} />
                    <Route path="offers/:id" component={OfferDetails} />
                    <Route path="account" component={AccountPage} />
                </Route>
                <Redirect from="*" to="/" />
            </Router>
        );
    }
}
