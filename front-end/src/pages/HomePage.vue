<script>
import { store } from '../store';
import axios from 'axios';
import NavBar from '../components/NavBar.vue';

export default {
    name: 'HomePage',
    components: {
        NavBar,
    },

    data() {
        return {
            store,
            arrayPhotos: [],
        }
    },

    created() {
        this.axiosCall(this.store.query);
    },

    methods: {
        axiosCall(query) {
            axios.get(`${this.store.baseApiUrl}?q=${query}`).then((resp) => {
                this.arrayPhotos = resp.data;
                console.log(this.arrayPhotos);
            });
        },
    }
}
</script>

<template>
    <NavBar></NavBar>
    <div class="container py-5">
        <div class="row mb-5">

            <div class="col-6 px-5 d-flex justify-content-between">
                <input id="search-bar" class="form-control d-inline" type="text" v-model="this.store.query"
                    @keyup.enter="axiosCall(this.store.query)" placeholder="cerca una foto">

                <RouterLink :to="{ name: 'second' }" class="btn btn-primary">
                    Scrivi un messaggio
                </RouterLink>
            </div>


        </div>
        <div class="row gy-4">

            <div v-for="(photo, index) in arrayPhotos" :key="index" v-show="photo.visible" class="mb-3 px-5 col-6">

                <div class="container-fluid card py-5">

                    <div class="row">
                        <div class="col-6">
                            <ul>
                                <li>
                                    <h4>{{ photo.title }}</h4>
                                </li>
                                <li>
                                    <p>
                                        {{ photo.description }}.
                                    </p>
                                </li>
                            </ul>
                        </div>

                        <div class="col-6" v-if="`http://localhost:8080/files/${photo.id}`">
                            <img :src="`http://localhost:8080/files/${photo.id}`" alt="" class="photo-img">

                        </div>
                    </div>

                </div>

            </div>

        </div>
    </div>
</template>

<style lang="scss" scoped>
ul {
    list-style: none;
}

.photo-img {
    max-width: 100%;
}

#search-bar {
    max-width: 300px;
}
</style>