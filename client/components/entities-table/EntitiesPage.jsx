import React from 'react';
import OffersList from '../offers/OffersList.jsx';
import MeetupsList from '../meetups/MeetupsList.jsx';
import {OFFER, MEETUP, COMPANY} from '../../constants/Constants.js';
import IdentityService from '../../services/IdentityService.js';

export default class EntitiesPage extends React.Component {

    static contextTypes = {
        router: React.PropTypes.object.isRequired
    };

    static propTypes = {
        entityType: React.PropTypes.string.isRequired
    };

    constructor() {
        super();

        this.state = {
            user: null,
            queryParams: {
                page: 0,
                size: 5,
                sort: 'createdDate,desc'
            }
        };

        this.handleQueryChange = this.handleQueryChange.bind(this);
        this.createNew = this.createNew.bind(this);
    }

	componentDidMount() {
		IdentityService.getUserIdentity().then((identity) => {
			this.setState({
				user: identity
			});
		});
	}

    handleQueryChange(e) {
        e.preventDefault();
        this.setState({
            queryParams: {
                size: this.refs.perPage.value,
                sort: this.refs.sortedBy.value
            }
        });
    }

    createNew() {
        this.context.router.push({
            path: `/new-${this.props.entityType.toLowerCase()}`
        });
    }

    render() {
        let table = this.props.entityType === OFFER ? (
            <OffersList
                queryParams={this.state.queryParams} />
            ) : (
            <MeetupsList
                 queryParams={this.state.queryParams} />
            );

        return (
            <div id="content" className="entities-page">
                <div className="row">
                    <div className="col-sm-10 col-sm-offset-1 col-xs-12 col-xs-offset-0">
                        <div className="entites-page-container">
                            <div className="entites-data">
                                <div className="c3-modal-header">{`${this.props.entityType.toLowerCase()}s`}</div>
                                <div className="c3-modal-sidebar">
                                    <div className="sidebar-content">
                                        <form onSubmit={this.handleQueryChange}>
                                            <div className="row">
                                                <div className="col-xs-10 col-xs-offset-1">
                                                    <div className="form-group">
                                                        <label htmlFor="per-page">Items count:</label>
                                                        <input id="per-page" type="number" ref="perPage" className="form-control" />
                                                    </div>
                                                </div>
                                            </div>

                                            <div className="row">
                                                <div className="col-xs-10 col-xs-offset-1">
                                                    <div className="form-group">
                                                        <label htmlFor="sort-by">Sorted</label>
                                                        <select id="sort-by" className="form-control" ref="sortedBy">
                                                            <option value="createdDate,desc">Newest first</option>
                                                            <option value="createdDate,asc">Latest first</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div className="row">
                                                <div className="col-xs-10 col-xs-offset-1">
                                                    <button type="submit" className="btn btn-primary" style={{marginRight: 5}}>Sort</button>
													{ this.state.user && (this.state.user.type === COMPANY) && (<button type="button" className="btn btn-primary" onClick={this.createNew}>Create</button>)}
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div className="c3-modal-content">
                                    {table}
                                </div>
                                </div>
                            </div>
                    </div>
                </div>
            </div>
        );
    }
}
