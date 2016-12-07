import React from 'react';

export default class Logo extends React.Component {

    static propTypes = {
        size: React.PropTypes.string.isRequired,
        showWords: React.PropTypes.bool
    };

    constructor() {
        super();
    }

    render() {
        let classes = this.props.size === 'small' ? 'small' : 'big';

        return (
            <div className={`logo-block ${classes}`}>
                <img src="/img/logo.png" className="full-logo" />
                {this.props.showWords ? (
                    <div className="logo-text">
                        <div>Create</div>
                        <div>Contribute</div>
                        <div>Communicate</div>
                    </div>) : null}
            </div>
        );
    }
}
