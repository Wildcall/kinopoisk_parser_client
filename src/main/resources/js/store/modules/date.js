import {getAllDate} from "../../services/FileService";

const state = () => ({
    loading: false,
    dates: []
})

const getters = {
    getLoading: (state) => {
        return state.loading
    },

    getDates: (state) => {
        return state.dates
    },

    getYears: (state) => {
        return state.dates.map((value, index, array) => value[0])
    },

    getMonths: (state) => (year) => {
        return state.dates.filter(obj => obj[0] === year).map((value, index, array) => value[1])
    },

    getDays: (state) => (year, month) => {
        return state.dates.filter(obj => (obj[0] === year) && (obj[1] === month)).map((value, index, array) => value[2]).sort((a, b) => a - b)
    }
}

const mutations = {
    setLoading(state, bool) {
        state.loading = bool
    },

    setDates(state, dates) {
        state.dates = dates
    }
}

const actions = {
    // Get list of sites
    async fetchAllAvailableDate({commit}) {
        commit('setLoading', true)
        await getAllDate()
            .then((r) => {
                if (r?.data) {
                    commit('setDates', r.data)
                }
            })
            .finally(() => {
                commit('setLoading', false)
            })

    },
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}