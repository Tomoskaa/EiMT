import React from "react";
import {Link} from "react-router-dom";

const productTerm = (props) => {
    return (
        <tr>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.availableCopies}</td>
            <td scope={"col"}>{props.term.category.name}</td>
            <td scope={"col"}>{props.term.author.name}</td>
            <td scope={"col"} className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/products/edit/${props.term.id}`}>
                    Edit
                </Link>
                <a title={"Mark As Taken"} className={"btn btn-warning"}
                   onClick={() => props.onMark(props.term.id)}>
                    Mark As Taken
                </a>
                {/*<Link className={"btn btn-warning ml-2"}*/}
                {/*      onClick={() => props.onTerm(props.term.id)}*/}
                {/*      to={`/products/term/${props.term.id}`}>*/}
                {/*    Mark As Taken*/}
                {/*</Link>*/}
            </td>
        </tr>
    )
}

export default productTerm;