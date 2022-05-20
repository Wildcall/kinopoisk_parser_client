<template>
  <v-card class="pa-4">
    <v-row>
      <v-col cols="1">
        <h4 style="color: #263238">{{ film.pos }}</h4>
      </v-col>
      <v-col cols="8">
        <div>
          <h2 style="color: #263238">{{ film.title }}</h2>
        </div>
        <div class="mt-2">
          <span class="caption" style="color: #263238">Release dates:</span>
          <span class="font-weight-bold" style="color: #263238">{{ film.prodYear }}</span>
        </div>
      </v-col>
      <v-col cols="3"
             class="d-flex flex-column justify-center align-center"
      >
        <v-row no-gutters>
          <v-col cols="6"
                 class="d-flex justify-end caption"
          >
            <div
                class="align-self-end"
                style="color: #263238"
            >
              Rating:
            </div>
          </v-col>
          <v-col cols="6"
                 class="d-flex justify-center"
          >
            <div
                class="align-self-end text-h4 font-weight-bold"
                style="color: #00C853;"
            >
              {{ film.rating }}
            </div>
          </v-col>
          <v-col cols="6"
                 class="d-flex justify-end"
          >
            <div
                class="align-self-end caption"
                style="color: #263238"
            >
              Votes:
            </div>
          </v-col>
          <v-col cols="6"
                 class="d-flex justify-center"
          >
            <div
                class="align-self-end subtitle-2"
                style="color: #263238"
            >
              {{ film.voteCount }}
            </div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
export default {
  name: "film-info",
  props: {
    film: {}
  }
}
</script>

<style>
</style>