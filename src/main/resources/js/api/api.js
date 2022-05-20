import axios from "axios";
import {API_FILM} from "./ApiConst";

export class Api {
    static getFilmApi() {
        return axios.create({
            baseURL: API_FILM
        })
    }
}