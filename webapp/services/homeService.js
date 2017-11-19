import axios from 'axios';

class HomeService {
  findAll() {
    return axios.get(`${document.location.origin}/api/files/`);
  }

  search(content) {
    return axios.get(`${document.location.origin}/api/files/search?content=${content}`);
  }
}

export default (HomeService = new HomeService());
