
import { reactive } from "vue";

export const store = reactive({

    baseApiUrl: 'http://localhost:8080/api/v1/photos',
    query: "",
    baseApiUrlMsg: 'http://localhost:8080/api/v1/msgs',
});
