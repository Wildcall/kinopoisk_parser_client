<template>
  <v-app>
    <v-app-bar
        app
        class="d-flex justify-center"
        style="background:#263238;"
    >
      <div>
        <h2 style="color:#EEEEEE">
          История лучших фильмов с сайта Kinopoisk
        </h2>
      </div>
    </v-app-bar>
    <v-main style="background:#CFD8DC;">
      <v-container>
        <v-row>
          <v-col cols="3">
            <v-card>
              <v-card-title class="text-start">
            <span style="color:#263238">
              Выберите дату: </span>
              </v-card-title>
              <v-card-text>
                <v-select
                    v-model="year"
                    :items="years"
                    label="Год"
                    dense
                    outlined
                />
                <v-select
                    v-model="month"
                    :items="months(this.year)"
                    label="Месяц"
                    dense
                    outlined
                    :disabled="!year"
                />
                <v-select
                    v-model="day"
                    :items="days(this.year, this.month)"
                    label="День"
                    dense
                    outlined
                    :disabled="!month"
                />
              </v-card-text>
              <v-card-actions class="d-flex justify-end">
                <v-btn
                    :disabled="!day || !month || !year"
                    text
                    color="#263238"
                    @click="fetchFilm"
                >
                  Показать
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
          <v-col cols="9">
            <v-card class="mb-4">
              <v-card-title>
                Топ фильмов на {{ day }}-{{ month }}-{{ year }}
              </v-card-title>
            </v-card>
            <v-card v-if="!this.films(year, month, day)">
              <v-card-text>
                No films were found on this date...
              </v-card-text>
            </v-card>
            <FilmInfo
                v-else
                class="mb-4"
                v-for="film in this.films(year, month, day)"
                :key="film.pos"
                :film="film"
            />
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>

import {mapActions, mapGetters} from "vuex";
import FilmInfo from "./component/FilmInfo.vue";

export default {
  components: {FilmInfo},

  data: () => ({
    year: null,
    month: null,
    day: null,
  }),

  computed: {
    ...mapGetters({
      films: 'film/getFilms',
      years: 'date/getYears',
      months: 'date/getMonths',
      days: 'date/getDays',
      loading: 'date/getLoading',
    }),
  },

  methods: {
    ...mapActions({
      getDates: 'date/fetchAllAvailableDate',
      fetchAllFilmByDate: 'film/fetchAllFilmByDate'
    }),

    fetchFilm() {
      const date = {
        year: this.year,
        month: this.month,
        day: this.day
      }
      this.fetchAllFilmByDate(date)
    }
  },

  mounted() {
    this.getDates()
    const date = new Date()
    this.year = date.getFullYear()
    this.month = date.getMonth() + 1
    this.day = date.getDate()
    this.fetchAllFilmByDate({
      year: this.year,
      month: this.month,
      day: this.day
    })
  }
}
</script>

<style>
</style>