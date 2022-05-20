import {Api} from "../api/api";

export const getAllDate = () => {
    return Api.getFilmApi().get('/date')
}

export const getFilmsByDate = ({year, month, day}) => {
    const strMonth = month < 10 ? '0' + month : month
    const strDay = day < 10 ? '0' + day : day
    return Api.getFilmApi().get(`/top?date=${year}-${strMonth}-${strDay}`)
}