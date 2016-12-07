import React from 'react';
import Logo from '../navbar/Logo.jsx';

export default class Auth extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
          <div id="content" className="auth">
              <div className="form-container">
                <Logo
                      size="big"
                      showWords={true} />

                <form>
                    <div className="form-group">
                        <label for="login-input">Login:</label>
                        <input id="login-input" type="text" className="form-control"/>
                    </div>

                    <div className="form-group">
                        <label for="password-input">Password</label>
                        <input id="password-input" type="text" className="form-control"/>
                    </div>
                </form>
              </div>

          </div>
        );
    }
}