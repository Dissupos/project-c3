import React from 'react';
import TopMenu from './TopMenu.jsx';

export default class C3Header extends React.Component {
    constructor() {
        super();

        this.state = {
            menuItems: [
                {
                    link: 'about',
                    name: 'About'
                },
                {
                    link: 'meetups',
                    name: 'Meetups'
                },
                {
                    link: 'offers',
                    name: 'Offers'
                }
            ]
        };
    }

    render() {
        return (
          <div id="top-navbar" className="top-navbar">
              <div className="logo-block">
                  <img src="/img/logo-full.png" className="full-logo" />
              </div>

              <TopMenu menuItems={this.state.menuItems} />
          </div>
        );
    }
}
