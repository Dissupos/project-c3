import React from 'react';
import EntitiesPage from '../entities-table/EntitiesPage.jsx';
import {MEETUP} from '../../constants/Constants.js';

export default class MeetupsPage extends React.Component {
    render() {
        return (
            <EntitiesPage entityType={MEETUP} />
        );
    }
}
