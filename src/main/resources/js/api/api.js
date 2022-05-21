import Vue from "vue";

export const Api = {
    async fetchFilms(year, month, day) {
        const strMonth = month < 10 ? '0' + month : month
        const strDay = day < 10 ? '0' + day : day
        return Vue.http.get(`/api/v1/film/top?date=${year}-${strMonth}-${strDay}`)
            .then(r => {
                if (r.data) {
                    return {
                        date: {
                            year: parseInt(year),
                            month: parseInt(month),
                            day: parseInt(day)
                        },
                        films: r.data
                    }
                }
            })
    }
}