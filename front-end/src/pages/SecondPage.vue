<script>
import axios from 'axios';
import { store } from '../store';
import NavBar from '../components/NavBar.vue';


export default {
    name: 'SecondPage',

    components: {
        NavBar,
    },

    data() {
        return {
            store,
            email: "",
            message: "",
        }
    },

    methods: {
        axiosPostCreate(email, message) {
            axios.post(`${this.store.baseApiUrlMsg}`, {
                email: email,
                message: message,
            }).then((resp) => {
                console.log(resp);

            });
            this.email = "";
            this.message = "";
            this.$router.push({ name: 'home' });
        }
    }
}
</script>

<template>
    <NavBar></NavBar>
    <div class="container">
        <div class="row pt-5">
            <div class="col-10 offset-1 mb-5">
                <RouterLink :to="{ name: 'home' }" class="btn btn-primary">torna alla homePage</RouterLink>
            </div>
            <div class="col-10 offset-1">
                <div class="card p-3">
                    <form class="form-group" action="">
                        <input v-model="email" type="text" class="form-control mb-2" placeholder="address email">
                        <textarea v-model="message" class="form-control mb-2" rows="10"
                            placeholder="inserisci il testo del messaggio"></textarea>
                        <button @click.prevent="axiosPostCreate(email, message)"
                            class="btn btn-primary mt-4 px-4">Invia</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped></style>