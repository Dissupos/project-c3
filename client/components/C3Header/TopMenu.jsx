import React from 'react';
import TopMenuItem from './TopMenuItem.jsx';

export default class TopMenu extends React.Component {
    static propTypes = {
        menuItems: React.PropTypes.array.isRequired
    };

    constructor() {
        super();
    }

    render() {
        let menuItems = this.props.menuItems.map((item, index) => {
            return (
                <TopMenuItem
                    key={index}
                    link={item.link}
                    name={item.name} />
            );
        });

        return (
            <div className="top-menu pull-right">
                <ul>
                    {menuItems}
                </ul>
            </div>
        );
    }
}
