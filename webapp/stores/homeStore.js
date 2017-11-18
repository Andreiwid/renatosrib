import { observable, computed, action, runInAction, toJS } from 'mobx';
import homeService from '../services/homeService'
import { Hermes } from 'syntec-apollo-11';

class HomeStore {

    @observable files = [];    

    @action
    getFiles() {
        homeService.findAll().then((response) => {
            runInAction('Busca lista inicial', () => {                           
                this.files = response.data;                
            });
        }).catch((response)=> {
            console.error(response);            
            Hermes.clearMessages();
            Hermes.setContext('error');
            Hermes.addMessage('Houve um problema e não foi possível busca os arquivos.', true);
        });
    }

    @action
    search() {
        homeService.findAll().then((response) => {
            runInAction('Busca lista inicial', () => {
                this.files = response.data;                
            });
        }).catch((response)=> {
            console.error(response);            
            Hermes.clearMessages();
            Hermes.setContext('error');
            Hermes.addMessage('Houve um problema e não foi possível busca os arquivos.', true);
        });
    }
}

export const homeStore = new HomeStore();
