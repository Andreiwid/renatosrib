import React from 'react';
import { observer } from 'mobx-react';
import {Filter} from 'syntec-apollo-11';
import { homeStore}  from '../stores/homeStore'
import {v4} from 'uuid'

@observer
class HomePage extends React.Component {
  constructor(props) {    
    super(props);    
    homeStore.getFiles();
  }

  render() {
    let files = homeStore.files.map((file) => {
        return <li key={`file-${v4()}`}>{file.fileName}</li>
    });

    return (
    <div>      
        <div className="sv-row">
          <div className="sv-column">          
            <Filter />
          </div>
        </div>
        <div>
          <ul>
            {files}
          </ul>
        </div>
    </div>)
  }
}

HomePage.displayName = 'HomePage';
export default HomePage;
