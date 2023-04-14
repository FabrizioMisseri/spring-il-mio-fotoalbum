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
            <div class="col-6">
                <input id="search-bar" class="form-control" type="text" v-model="this.store.query"
                    @keyup.enter="axiosCall(this.store.query)" placeholder="cerca una foto">
            </div>

            <!-- debug -->
            <div class="col-6 text-end">
                <RouterLink :to="{ name: 'second' }" class="btn btn-primary">Scrivi un messaggio</RouterLink>
            </div>
            <!-- debug -->

        </div>
        <div class="row">

            <div v-for="(photo, index) in arrayPhotos" :key="index">
                <div class="mb-4 py-3" v-if="photo.visible">
                    <div class="row">
                        <div class="col-4">
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

                        <div class="col-4">
                            <img :src="photo.url" alt="" class="photo-img">
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
    max-width: 400px;
}

#search-bar {
    max-width: 300px;
}
</style>