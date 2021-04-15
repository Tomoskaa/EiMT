import React from "react";

const categories = (props) => {
    return (
        <div>
            <section class={"jumbotron text-center"}>
                <div className={"container"}>
                    <h1 className={"jumbotron-heading"}>BOOK SHOP</h1>
                    <h3 className={"jumbotron-heading"}>All categories</h3>
                </div>
            </section>
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"table-responsive"}>
                        <table className={"table table-striped"}>
                            <thead>
                            <th scope={"col"}>Name</th>
                            </thead>
                            <tbody>
                            {props.categories.map((term) => {
                                return (
                                    <tr>
                                        <td>{term.name}</td>
                                    </tr>
                                );
                            })}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default categories;