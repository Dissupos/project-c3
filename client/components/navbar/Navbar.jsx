import React from 'react';

export default class Navbar extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
          <div id="top-nav-bar" className="top-navbar">
              <div className="logo-block">
                  C<sup>3</sup>
              </div>
          </div>
        );
    }
}
