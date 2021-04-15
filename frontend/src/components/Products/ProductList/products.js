import React from "react";
import ReactPaginate from 'react-paginate';
import ProductTerm from '../ProductTerm/productTerm';
import {Link} from "react-router-dom";

//promenata vo klasna komponenta ni e potrebna za da definirame state
class Products extends React.Component {

    constructor(props) {
        super(props);

        //vo sostojbata ke cuvame broj na strana i goleminata na produktite
        this.state = {
            page: 0,
            size: 5
        }
    }

    render() {

        //definirame kolkava e goleminata na stranata
        const offset = this.state.size * this.state.page;
        //promenliva koja ni kazuva kolku imame do slednata strana
        const nextPageOffset = offset + this.state.size;
        //promenliva koja ke ni go presmeta vkupniot broj na strani
        const pageCount = Math.ceil(this.props.products.length / this.state.size);
        //ako ne gi zadademe (offset, nextPageOffset) kako argumenti nema da gi prikazuva produktite
        const products = this.getProductsPage(offset, nextPageOffset);

        return (
            <div>
                <section className={"jumbotron text-center"}>
                    <div className={"container"}>
                        <h1 className={"jumbotron-heading"}>BOOK SHOP</h1>
                        <h3 className={"jumbotron-heading"}>All books</h3>
                    </div>
                </section>
                <div className={"container mm-4 mt-5"}>
                    <div className={"row"}>
                        <div className={"table-responsive"}>
                            <table className={"table table-striped"}>
                                <thead>
                                <tr>
                                    <th scope={"col"}>Name</th>
                                    <th scope={"col"}>Available Copies</th>
                                    <th scope={"col"}>Category</th>
                                    <th scope={"col"}>Author</th>
                                </tr>
                                </thead>
                                <tbody>
                                {products}
                                </tbody>
                            </table>
                        </div>
                        <div className="col mb-3">
                            <div className="row">
                                <div className="col-sm-12 col-md-12">
                                    <Link className={"btn btn-block btn-dark"} to={"/products/add"}>Add new book</Link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <ReactPaginate previousLabel={"back"}
                                   nextLabel={"next"}
                                   breakLabel={<a href="/#">...</a>}
                                   breakClassName={"break-me"}
                                   pageClassName={"ml-1"}
                                   pageCount={pageCount}
                                   marginPagesDisplayed={2}
                                   pageRangeDisplayed={5}
                                   onPageChange={this.handlePageClick}
                                   containerClassName={"pagination m-4 justify-content-center"}
                                   activeClassName={"active"}/>
                </div>
            </div>
        )
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getProductsPage = (offset, nextPageOffset) => {
        return this.props.products.map((term, index) => {
            return (
                <ProductTerm term={term} onDelete={this.props.onDelete} onEdit={this.props.onEdit}
                             onMark={this.props.onMark}/>
            );
        }).filter((product, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}

export default Products;
