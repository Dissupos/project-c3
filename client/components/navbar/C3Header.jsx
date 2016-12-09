import React from 'react';
import TopMenu from './TopMenu.jsx';
import Logo from './Logo.jsx';

export default class C3Header extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            logoSize: 'small',
            showWords: false
        };
    }

    render() {
        return (
          <div id="top-navbar" className="top-navbar">
              <Logo
                  size={this.state.logoSize}
                  showWords={this.state.showWords} />
              <TopMenu />
          </div>
        );
    }
}
