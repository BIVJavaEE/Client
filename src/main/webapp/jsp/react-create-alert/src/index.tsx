import '../css/create-alert.scss';
import * as React from "react";
import * as ReactDOM from "react-dom";

import {Main} from "./main.component";


const elementById = document.getElementById("create-alert-content");
console.log(elementById);
ReactDOM.render(
    <Main/>,
    elementById
);