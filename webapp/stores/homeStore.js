import { observable, computed, action, runInAction, toJS } from 'mobx';
import homeService from '../services/homeService';
import { Hermes } from 'syntec-apollo-11';

class HomeStore {
  @observable files = [];
  @observable fileToDownload = null;

  @action
  getFiles() {
    homeService
      .findAll()
      .then(response => {
        runInAction('Busca lista inicial', () => {
          this.files = response.data;
        });
      })
      .catch(response => {
        console.error(response);
        Hermes.clearMessages();
        Hermes.setContext('error');
        Hermes.addMessage('Houve um problema e não foi possível buscar os arquivos.', true);
      });
  }

  @action
  search(term) {
    debugger;
    homeService
      .search(term)
      .then(response => {
        runInAction('Busca lista filtrada', () => {
          debugger;
          this.files = response.data;
        });
      })
      .catch(response => {
        console.error(response);
        Hermes.clearMessages();
        Hermes.setContext('error');
        Hermes.addMessage('Houve um problema e não foi possível buscar os arquivos.', true);
      });
  }

  @action
  showFileDownload(file) {
    this.fileToDownload = file;
  }

  @action
  closeFileDownload(file) {
    this.fileToDownload = null;
  }
}

export const homeStore = new HomeStore();
