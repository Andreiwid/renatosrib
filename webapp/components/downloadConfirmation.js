import React, { Component } from 'react';

export default class DownloadConfirmation extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    let fileName = null;
    let path = null;
    let lastModification = null;
    let contentHighlighted = null;
    if (this.props.file) {
      fileName = (
        <li className="text sv-pv--5" title={this.props.file.fileName}>
          <b>Nome do arquivo: </b> {this.props.file.fileName}
        </li>
      );
      path = (
        <li className="text sv-pv--5" title={this.props.file.path}>
          <b>Localização: </b> {this.props.file.path}
        </li>
      );

      lastModification = (
        <li className="text sv-pv--5">
          <b>Data de útima alteração:</b> {new Date(this.props.file.lastModification).toLocaleString()}
        </li>
      );

      if (this.props.file.contentHighlighted) {
        let items = this.props.file.contentHighlighted.map(item => {
          return <li className="text sv-pv--5">{item}</li>;
        });
        if (items.lenght > 0) {
          contentHighlighted = (
            <div>
              <b>Trechos encontrados</b>
              <ul>{items}</ul>
            </div>
          );
        }
      }
    }
    return (
      <ul>
        {fileName}
        {path}
        {lastModification}
        {contentHighlighted}
      </ul>
    );
  }
}
