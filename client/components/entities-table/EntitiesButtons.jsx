import React from 'react';
import IdentityService from '../../services/IdentityService.js';
import ApiService from '../../services/ApiService.js';
import ErrorHandler from '../../services/ErrorHandler.js';
import NotificationService from '../../services/NotificationService.js';

import {OFFER, MEETUP, STUDENT, PROFESSOR, COMPANY, OVERPAST, CANCELED} from '../../constants/Constants.js';

export default class EntitiesButtons extends React.Component {

	static propTypes = {
		entityType: React.PropTypes.string.isRequired,
		entity: React.PropTypes.object.isRequired
	};

	constructor() {
		super();

		this.state = {
			user: null
		};

		this.generateButtons = this.generateButtons.bind(this);
		this.joinMeetup = this.joinMeetup.bind(this);
		this.joinOffer = this.joinOffer.bind(this);
		this.leaveMeetup = this.leaveMeetup.bind(this);
		this.leaveOffer = this.leaveOffer.bind(this);
	}

	componentDidMount() {
		IdentityService.getUserIdentity().then((identity) => {
			this.setState({
				user: identity
			});
		});
	}

	generateButtons() {
		let buttons = [];

		if (!this.state.user) {
			return buttons;
		}

		if (this.props.entityType === MEETUP && this.props.entity.status !== OVERPAST &&
			this.props.entity.status !== CANCELED) {

			buttons.push(
				<button
					type="button"
					key={buttons.length}
					className="btn btn-success"
					onClick={this.joinMeetup}>Join</button>
			);

			buttons.push(
				<button
					type="button"
					key={buttons.length}
					className="btn btn-danger"
					onClick={this.leaveMeetup}>Leave</button>
			);
		}

		if (this.props.entityType === OFFER) {
			if ((this.state.user.type === PROFESSOR && !this.props.entity.professor) ||
				(this.state.user.type === STUDENT && !this.props.entity.student)) {
				buttons.push(
					<button
						type="button"
						key={buttons.length}
						className="btn btn-success"
						onClick={this.joinOffer}>Join</button>
				);
			}

			if ((this.props.entity.professor && (this.state.user.id === this.props.entity.professor.id)) ||
				(this.props.entity.student && (this.state.user.id === this.props.entity.student.id))) {
				buttons.push(
					<button
						type="button"
						key={buttons.length}
						className="btn btn-danger"
						onClick={this.leaveOffer}>Leave</button>
				);
			}
		}

		return buttons;
	}

	joinMeetup() {
		ApiService.joinMeetup(this.props.entity.id).then(() => {
			NotificationService.success({message: 'You have successfully joined the meetup.'});
			window.location.reload();
		}).catch(ErrorHandler);
	}

	joinOffer() {
		ApiService.joinOffer(this.props.entity.id).then(() => {
			NotificationService.success({message: 'You have successfully joined the offer.'});
			window.location.reload();
		}).catch(ErrorHandler);
	}

	leaveOffer() {
		ApiService.leaveOffer(this.props.entity.id).then(() => {
			NotificationService.success({message: 'You have successfully left the offer.'});
			window.location.reload();
		}).catch(ErrorHandler);
	}

	leaveMeetup() {
		ApiService.leaveMeetup(this.props.entity.id).then(() => {
			NotificationService.success({message: 'You have successfully left the meetup.'});
			window.location.reload();
		}).catch(ErrorHandler);
	}

	render() {
		let buttons = this.generateButtons();
		return buttons.length ? (
			<div className="entity-buttons">
				{buttons}
			</div>
		): null;
	}
}
