import {BaseModelValue} from "./baseModelValue";

export enum Type {
    Temperature = 0,
    Pressure = 1,
    WindSpeed = 2
}

export interface TypeValue extends BaseModelValue {
    unit: string;
}