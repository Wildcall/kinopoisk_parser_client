import {defineStore} from 'pinia'
import {Api} from "../api/api";

export const useStore = defineStore({
    id: 'main',

    state: () => {
        return {
            loading: false,
            data: []
        }
    },

    getters: {
        getLoading(state) {
            return state.loading
        },

        getYears(state) {
            return [...new Set(state.data
                .map((value) => value.date.year))]
        },

        getMonths(state) {
            return (year) => [...new Set(state.data
                .filter(obj => obj.date.year === year)
                .map((value) => value.date.month))]
        },

        getDays(state) {
            return (year, month) => [...new Set(state.data
                .filter(obj => (obj.date.year === year) && (obj.date.month === month))
                .map((value) => value.date.day))]
                .sort((a, b) => a - b)
        },

        getData: (state) => (year, month, day) => {
            const index = state.data.findIndex(obj => {
                return (obj.date.year === year)
                    && (obj.date.month === month)
                    && (obj.date.day === day)
            })
            if (index < 0) return []
            return state.data[index]
        },

        getFilms: (state) => (year, month, day) => {
            const index = state.data.findIndex(obj => {
                return (obj.date.year === year)
                    && (obj.date.month === month)
                    && (obj.date.day === day)
            })

            if (index < 0){
                console.log("index < 0")
                return []
            }
            if (!state.data[index].films){
                console.log("!state.data.films")
                return []
            }
            return state.data[index].films
        }
    },

    actions: {
        async fetchFilms(year, month, day) {
            if (this.getFilms(year, month, day).length)
                return

            await Api.fetchFilms(year, month, day)
                .then((r) => {
                    if (r) {
                        this.getData(year, month, day).films = r.films
                    }
                })
        }
    },
})