import React from 'react';
import { Router, Route, IndexRoute, hashHistory, Redirect } from 'react-router';

/** components */
import Main from './components/Main.jsx';
import Home from './components/Home.jsx';
import About from './components/about/About.jsx';
import Auth from './components/auth/Auth.jsx';
import SignIn from './components/auth/SignIn.jsx';
import SignUp from './components/auth/SignUp.jsx';

export default class Root extends React.Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Route path='/' component={Main}>
                    <IndexRoute component={Home} />
                    <Route path="auth" component={Auth}>
                        <Route path="sign-in" component={SignIn} />
                        <Route path="sign-up" component={SignUp} />
                    </Route>
                    <Route path="about" component={About} />
                </Route>
                <Redirect from="*" to="/" />
            </Router>
        );
    }
}
