import {getFilmsByDate} from "../../services/FileService";

const state = () => ({
    loading: false, films: []
})

const getters = {
    getLoading: (state) => {
        return state.loading
    },

    getFilms: (state) => (year, month, day) => {
        //  @formatter:off
        const date = {
            year: year, month: month, day: day
        }
        return state
            ?.films
            ?.find(obj =>
                obj.date.year === date.year &&
                obj.date.month === date.month &&
                obj.date.day === date.day)
            ?.films
        //  @formatter:on
    },
}

const mutations = {
    setLoading(state, bool) {
        state.loading = bool
    },

    setFilms(state, {date, films}) {
        state.films.push({date, films})
    }
}

const actions = {
    async fetchAllFilmByDate({commit}, {year, month, day}) {
        commit('setLoading', true)
        await getFilmsByDate({year, month, day})
            .then((r) => {
                if (r?.data) {
                    const data = {
                        date: {
                            year, month, day
                        }, films: r.data
                    }
                    commit('setFilms', data)
                }
            })
            .finally(() => {
                commit('setLoading', false)
            })
    },
}

export default {
    namespaced: true, state, getters, actions, mutations
}