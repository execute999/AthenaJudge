import axios from 'axios';

export class User {
    getAll(){
        return axios.get("http://localhost:1135/api/AllUsers").then(res => res.data);
    }
    save(user) {
        return axios.post("http://localhost:1135/api/SaveUser",user).then(res => res.data);
    }
}