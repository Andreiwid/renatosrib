import React, { Component } from 'react';
import { homeStore } from '../stores/homeStore';

export default class FileCard extends Component {
  getIcon(fileName) {
    switch (fileName.split('.').pop()) {
      case 'pdf':
        return <i className="fa fa-file-pdf-o fa-5x sv-ph--10" aria-hidden="true" />;
      case 'xls':
      case 'csv':
      case 'xlsx':
        return <i className="fa fa-file-excel-o fa-5x sv-ph--10" aria-hidden="true" />;
      case 'doc':
      case 'docx':
      case 'odt':
        return <i className="fa fa-file-word-o fa-5x sv-ph--10" aria-hidden="true" />;
      case 'png':
      case 'jpg':
      case 'tiff':
      case 'gif':
        return <i className="fa fa-file-image-o fa-5x sv-ph--10" aria-hidden="true" />;
      case 'ppt':
      case 'pps':
      case 'pptx':
        return <i className="fa fa-file-powerpoint-o fa-5x sv-ph--10" aria-hidden="true" />;
      default:
        return <i className="fa fa-file-text-o fa-5x sv-ph--10" aria-hidden="true" />;
    }
  }

  render() {
    console.log(this.props.file);
    let fileIcon = this.getIcon(this.props.file.fileName);
    return (
      <li className="sv-pa--10">
        <div
          className="sv-card card iterable"
          title={this.props.file.fileName}
          onClick={() => {
            homeStore.showFileDownload(this.props.file);
          }}
        >
          <div className="sv-row ">
            <div className="filename sv-ph--10 ">{this.props.file.fileName}</div>
            {fileIcon}
          </div>
        </div>
      </li>
    );
  }
}
