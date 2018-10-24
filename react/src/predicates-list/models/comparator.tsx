import {BaseModelValue} from "./baseModelValue";

export enum Comparator {
    Equal = 0,
    Superior = 1,
    Inferior = 2,
    SuperiorOrEqual = 3,
    InferiorOrEqual = 4
}

export interface ComparatorValue extends BaseModelValue {
}

