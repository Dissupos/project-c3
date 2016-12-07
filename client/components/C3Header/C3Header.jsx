import React from 'react';
import TopMenu from './TopMenu.jsx';
import Logo from './Logo.jsx';

export default class C3Header extends React.Component {
    constructor() {
        super();

        this.state = {
            menuItems: [
                {
                    link: 'meetups',
                    name: 'Meetups'
                },
                {
                    link: 'offers',
                    name: 'Offers'
                },
                {
                    link: 'about',
                    name: 'About'
                }
            ],
            logoSize: 'small',
            showWords: true
        };
    }

    render() {
        return (
          <div id="top-navbar" className="top-navbar">
              <Logo
                  size={this.state.logoSize}
                  showWords={this.state.showWords} />
              <TopMenu menuItems={this.state.menuItems} />
          </div>
        );
    }
}
