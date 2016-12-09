import React from 'react';

export default class Logo extends React.Component {

    static contextTypes = {
        router: React.PropTypes.object
    };

    static propTypes = {
        size: React.PropTypes.string.isRequired,
        showWords: React.PropTypes.bool
    };

    constructor() {
        super();

        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        this.context.router.push({
            path: '/'
        });
    }

    render() {
        let classes = this.props.size === 'small' ? 'small' : 'big';

        return (
            <div id="logo-component" className={`logo-block ${classes}`} onClick={this.handleClick}>
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
