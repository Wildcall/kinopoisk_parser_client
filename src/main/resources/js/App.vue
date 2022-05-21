<template>
  <v-app>
    {{ mainStore.getYears }}
    {{ mainStore.getMonths(2022) }}
    {{ mainStore.getDays(2022, 4) }}
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
                    :items="mainStore.getYears"
                    label="Год"
                    dense
                    outlined
                />
                <v-select
                    v-model="month"
                    :items="mainStore.getMonths(this.year)"
                    label="Месяц"
                    dense
                    outlined
                    :disabled="!year"
                />
                <v-select
                    v-model="day"
                    :items="mainStore.getDays(this.year, this.month)"
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
                    @click="showFilms"
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
            <FilmInfo
                class="mb-4"
                v-for="film in this.mainStore.getFilms(this.year, this.month, this.day)"
                :key="film.pos"
                :film="film"
            />
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script lang="js">
import FilmInfo from "./component/FilmInfo.vue";
import {useStore} from "./store/store";

export default {
  components: {FilmInfo},

  setup() {
    const mainStore = useStore()

    return {
      mainStore
    }
  },

  data: () => ({
    year: null,
    month: null,
    day: null,
    dates: dates
  }),

  methods: {
    showFilms() {
      this.mainStore.fetchFilms(this.year, this.month, this.day)
    }
  },

  mounted() {
    this.mainStore.data = dates.map((val => {
      const arr = val.split('-')
      const date = {
        year: parseInt(arr[0]),
        month: parseInt(arr[1]),
        day: parseInt(arr[2]),
      }
      return {
        date: date,
        films: []
      }
    }))
    const date = new Date()
    this.year = date.getFullYear()
    this.month = date.getMonth() + 1
    this.day = date.getDate()
  }
}
</script>

<style>
</style>