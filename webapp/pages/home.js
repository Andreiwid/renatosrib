import React from 'react';
import { observer } from 'mobx-react';
import { Filter, Modal } from 'syntec-apollo-11';
import FileCard from '../components/fileCard';
import DownloadConfirmation from '../components/downloadConfirmation';
import { homeStore } from '../stores/homeStore';
import { v4 } from 'uuid';

@observer
class HomePage extends React.Component {
  constructor(props) {
    super(props);
    homeStore.getFiles();
  }

  render() {
    let files = homeStore.files.map(file => {
      return <FileCard key={`file-${v4()}`} file={file} />;
    });

    return (
      <div>
        <Modal
          title="Fazer download do arquivo?"
          confirmButtonText="Download"
          type="small"
          isVisible={homeStore.fileToDownload != null}
          onClose={() => {
            homeStore.closeFileDownload();
          }}
        >
          <DownloadConfirmation file={homeStore.fileToDownload} />
        </Modal>
        <div className="sv-row">
          <div className="sv-column">
            <Filter
              onFilter={term => {
                homeStore.search(term);
              }}
            />
          </div>
        </div>
        <div className="sv-pv--10">
          <ul className="sv-row cards">{files}</ul>
        </div>
      </div>
    );
  }
}

HomePage.displayName = 'HomePage';
export default HomePage;
