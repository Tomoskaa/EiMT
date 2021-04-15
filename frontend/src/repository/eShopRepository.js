import axios from '../custom-axios/axios';

const EShopService = {
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },
    fetchProducts: () => {
        return axios.get("/products");
    },
    deleteProduct: (id)=> {
        return axios.delete(`/products/delete/${id}`);   // /products/delete/id
    },
    markProduct: (id)=> {
        return axios.get(`/products/mark/${id}`);
    },
    addProduct: (name, availableCopies, category, author) => {
        return axios.post("/products/add", {
            "name": name,
            "availableCopies": availableCopies,
            "category": category,
            "author": author
        });
    },
    editProduct: (id, name, availableCopies, category, author) => {
        return axios.put(`/products/edit/${id}`, {
            "name": name,
            "availableCopies": availableCopies,
            "category": category,
            "author": author
        });
    },
    getProduct: (id) => {
        return axios.get(`/products/${id}`);
    }
}

export default EShopService;