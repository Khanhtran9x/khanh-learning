import axiosClient from "./axiosClient";

const studentApi = {
     getAll() {
        const url = `/students`;
        return axiosClient.get(url);
    },

    get(id) {
        const url = `/students/${id}`;
        return axiosClient.get(url);
    },

    add(data) {
        const url = `/students`;
        return axiosClient.post(url, data);
    },

    update(data) {
        const url = `/students`;
        return axiosClient.put(url, data);
    },

    remove(id) {
        const url = `/students/${id}`;
        return axiosClient.delete(url);
    },

}

export default studentApi
