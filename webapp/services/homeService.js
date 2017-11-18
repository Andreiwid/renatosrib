import axios from 'axios'

class HomeService {
    findAll() {        
        return axios.get(`${document.location.origin}/api/files/`);
    }
}

export default HomeService = new HomeService()