import React, { Component } from 'react';
import { debug } from 'util';
import { v4 } from 'uuid';

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
        let items = this.props.file.contentHighlighted.map((item, index) => {
          if (index < 5) {
            return <li key={v4()} className="item-found sv-pv--5" dangerouslySetInnerHTML={{ __html: item }} />;
          }
        });
        if (items.length > 0) {
          contentHighlighted = (
            <div>
              <b>Alguns trechos encontrados</b>
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
