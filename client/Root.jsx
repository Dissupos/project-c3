import React from 'react';
import { Router, Route, IndexRoute, hashHistory } from 'react-router';
import Main from './components/Main.jsx';
import Home from './components/Home.jsx';

export default class Root extends React.Component {
    render() {
        return (
            <Router history={hashHistory}>
                <Route path='/' component={Main}>
                    <IndexRoute component={Home} />
                </Route>
            </Router>
        );
    }
}
