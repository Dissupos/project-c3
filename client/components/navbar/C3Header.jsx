import React from 'react';
import TopMenu from './TopMenu.jsx';
import Logo from './Logo.jsx';

export default class C3Header extends React.Component {

    static contextType = {
      router: React.PropTypes.object.isRequired
    };

    constructor(props) {
        super(props);

        this.state = {
            logoSize: 'small',
            showWords: false,
            isVisible: true
        };

        this.checkRoute = this.checkRoute.bind(this);

        window.onhashchange = () => {
            this.checkRoute();
        };
    }

    checkRoute() {
        let [,routeName] = (/^#\/([A-Za-z\-_]*)?/).exec(window.location.hash);
        let isVisible = routeName !== 'sign-up' && routeName !== 'sign-in' && routeName !== 'about';

        this.setState({
            isVisible: isVisible
        });
    }

    componentDidMount() {
       this.checkRoute();
    }

    render() {
        return (
          <div id="top-navbar" className="top-navbar">
              <Logo
                  size={this.state.logoSize}
                  showWords={this.state.showWords}
                  isVisible={this.state.isVisible}
              />
              <TopMenu />
          </div>
        );
    }
}
