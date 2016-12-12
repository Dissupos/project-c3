import React from 'react';
import EntitiesPage from '../entities-table/EntitiesPage.jsx';
import {OFFER} from '../../constants/Constants.js';

export default class OffersPage extends React.Component {

    constructor() {
        super();
    }

    render() {
        return (
            <EntitiesPage entityType={OFFER}/>
        );
    }
}
