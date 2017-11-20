import axios from 'axios';

class HomeService {
  findAll() {
    return axios.get(`${document.location.origin}/api/files/`);
  }

  search(content) {
    return axios.get(`${document.location.origin}/api/files/search?content=${content}`);
  }

  download(documentPayback) {
    let hrefLink = document.createElement('a');
    document.body.appendChild(hrefLink);
    hrefLink.download = documentPayback.fileName;
    hrefLink.href = `${document.location.origin}/api/files/download?path=${documentPayback.path}`;
    hrefLink.click();
    document.body.removeChild(hrefLink);
  }
}

export default (HomeService = new HomeService());
