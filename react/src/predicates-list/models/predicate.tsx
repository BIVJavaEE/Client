import {Type} from "./type";
import {Comparator} from "./comparator";

export class Predicate {

    public constructor(id: number, type: Type, comparator: Comparator) {
        this.id = id;
        this.type = type;
        this.comparator = comparator;
    }

    public id: number;

    public type: Type;

    public comparator: Comparator;

    public value: number;

    public beginTime: string = '00:00';

    public endTime: string = '23:59';

}