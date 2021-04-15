import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from "react-router-dom";
import Authors from "../Authors/authors";
import Categories from '../Categories/categories';
import Header from '../Header/header';
import Products from '../Products/ProductList/products';
import ProductAdd from '../Products/ProductAdd/productAdd';
import ProductEdit from "../Products/ProductsEdit/productEdit";
import EShopService from "../../repository/eShopRepository";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      authors: [],
      categories: [],
      products: [],
      selectedProduct: {}
    }
  }


  render() {
    return (

        <Router>
          <Header/>
          <main>
            <div className="container">
              <Route path={"/authors"} exact render={() =>
                  <Authors authors={this.state.authors}/>}/>
              <Route path={"/categories"} exact render={() =>
                  <Categories categories={this.state.categories}/>}/>
              <Route path={"/products/add"} exact render={() =>
                  <ProductAdd categories={this.state.categories}
                              authors={this.state.authors}
                              onAddProduct={this.addProduct}/>}/>
              <Route path={"/products/edit/:id"} exact render={() =>
                  <ProductEdit categories={this.state.categories}
                               authors={this.state.authors}
                               onEditProduct={this.editProduct}
                               product={this.state.selectedProduct}/>}/>
              <Route path={"/products"} exact render={() =>
                  <Products products={this.state.products}
                            onDelete={this.deleteProduct}
                            onEdit={this.getProduct}
                            onMark={this.markProduct}/>}/>

              <Redirect to={"/products"}/>
            </div>
          </main>
        </Router>
    );
  }

  loadAuthors = () => {
    EShopService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors: data.data
          })
        });
  }

  loadCategories = () => {
    EShopService.fetchCategories()
        .then((data) => {
          this.setState({
            categories: data.data
          })
        });
  }

  loadProducts = () => {
    EShopService.fetchProducts()
        .then((data) => {
          this.setState({
            products: data.data
          })
        });
  }

  deleteProduct = (id) => {
    EShopService.deleteProduct(id)
        .then(() => {
          this.loadProducts();
        });
  }

  markProduct = (id) => {
    EShopService.markProduct(id)
        .then(() => {
          this.loadProducts();
        });
  }

  addProduct = (name, availableCopies, category, author) => {
    EShopService.addProduct(name, availableCopies, category, author)
        .then(() => {
          this.loadProducts();
        });
  }

  getProduct = (id) => {
    EShopService.getProduct(id)
        .then((data) => {
          this.setState({
            selectedProduct: data.data
          })
        });
  }

  editProduct = (id, name, availableCopies, category, author) => {
    EShopService.editProduct(id, name, availableCopies, category, author)
        .then(() => {
          this.loadProducts();
        });
  }

  componentDidMount() {
    this.loadAuthors();
    this.loadCategories();
    this.loadProducts();
  }
}

export default App;
