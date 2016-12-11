import React from 'react';

export default class Status extends React.Component {
    static propTypes = {
        status: React.PropTypes.string.isRequired
    };

    constructor() {
        super();

        this.generateClassesString =  this.generateClassesString.bind(this);
    }

    generateClassesString() {
        return `status-block ${this.props.status.toLowerCase()}-status`;
    }

    render() {
        let classes = this.generateClassesString();
        return (
            <span className={classes}>
                {this.props.status}
            </span>
        );
    }
}
