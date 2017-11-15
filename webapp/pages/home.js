import React from 'react';
import { observer } from 'mobx-react';

@observer
class HomePage extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return <div>HomePage</div>;
  }
}

HomePage.displayName = 'HomePage';
export default HomePage;
