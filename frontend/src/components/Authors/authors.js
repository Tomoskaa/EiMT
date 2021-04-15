import React from "react";

const authors = (props) => {

    return (
        <div>
            <section className={"jumbotron text-center"}>
                <div className={"container"}>
                    <h1 className={"jumbotron-heading"}>BOOK SHOP</h1>
                    <h3 className={"jumbotron-heading"}>All Authors</h3>
                </div>
            </section>
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <th scope={"col"}>Author name</th>
                            <th scope={"col"}>Author surname</th>
                            <th scope={"col"}>Author country</th>
                            </thead>
                            <tbody>
                            {props.authors.map((term) => {
                                return (
                                    <tr>
                                        {/*Zosto ne raboti so ovie parametri????*/}
                                        {/*<td>{term.name}</td>*/}
                                        {/*<td>{term.surname}</td>*/}
                                        {/*<td>{term.country}</td>*/}
                                        <td>{term.name}</td>
                                        <td>{term.surname}</td>
                                        <td>{term.country.name}</td>
                                    </tr>
                                );
                            })}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default authors;