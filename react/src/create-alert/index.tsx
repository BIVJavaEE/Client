import '../../css/create-alert.scss';
import * as React from "react";
import * as ReactDOM from "react-dom";

import {Main} from "./main.component";
import {Type, TypeValue} from "./models/type";
import {Comparator, ComparatorValue} from "./models/comparator";
import {Predicate} from "./models/predicate";

const comparatorsValues: Array<ComparatorValue> = [
    {
        id: Comparator.Equal,
        text: "="
    },
    {
        id: Comparator.Superior,
        text: ">"
    },
    {
        id: Comparator.Inferior,
        text: "<"
    },
    {
        id: Comparator.SuperiorOrEqual,
        text: ">="
    },
    {
        id: Comparator.InferiorOrEqual,
        text: "<="
    }
];

export const typeValues : Array<TypeValue> = [
    {
        id: Type.Temperature,
        unit: "°C",
        text: "Temperature"
    },
    {
        id: Type.Pressure,
        unit: "pa",
        text: "Pressure"
    },
    {
        id: Type.WindSpeed,
        unit: "km/h",
        text: "Wind speed"
    }
];

const initialPredicates: Array<Predicate> = [
    new Predicate(0, Type.Temperature, Comparator.Superior)
];

const main = <Main
        typeValues={typeValues}
        comparatorValues={comparatorsValues}
        initialPredicates={initialPredicates}/>;

const elementById = document.getElementById("create-alert-content");
ReactDOM.render(main, elementById);