import React from 'react';
import Logo from '../navbar/Logo.jsx';

export default class Auth extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
          <div id="content" className="auth">
              <Logo
                  size="big"
                  showWords={true} />
          </div>
        );
    }
}