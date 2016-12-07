import React from 'react';
import { Router, Route, IndexRoute, hashHistory, Redirect } from 'react-router';

/** components */
import Main from './components/Main.jsx';
import Home from './components/Home.jsx';
import About from './components/about/About.jsx';
import Auth from './components/auth/Auth.jsx';

export default class Root extends React.Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Route path='/' component={Main}>
                    <IndexRoute component={Home} />
                </Route>
                <Route path="about" component={About} />
                <Route path="auth" component={Auth} />
                <Redirect from="*" to="/" />
            </Router>
        );
    }
}
