import * as React from "react";
import {Predicate} from "./models/predicate";
import {Button, DropdownItemProps, Input, Select, Form} from "semantic-ui-react";
import {BaseModelValue} from "./models/baseModelValue";
import {Type, TypeValue} from "./models/type";
import {ComparatorValue} from "./models/comparator";
import TimeInput from "react-time-input";

export interface PredicateProps {
    typeValues: Array<TypeValue>;
    comparatorValues: Array<ComparatorValue>;
    initialPredicate: Predicate;
    onDelete?: (p: Predicate) => void;
    onChange?: (p: Predicate) => void;
}

export interface PredicateState {
    predicate: Predicate;
    unit: string;
}

function generateSelectOptions(modelValues: Array<BaseModelValue>): Array<DropdownItemProps> {
    return Object.values(modelValues).map(({text, id}) => {
        return {
            text: text,
            value: id
        }
    });
}

export class PredicateComponent extends React.Component<PredicateProps, PredicateState> {

    public constructor(props: PredicateProps, context: any) {
        super(props, context);
        this.state = {
            predicate: this.props.initialPredicate,
            unit: this.getUnit(this.props.initialPredicate.type)
        };
    }

    public render() {
        return (
            <Form.Group wide className="predicates-container">

                <Form.Field>
                    <label>Type</label>
                    <Select options={generateSelectOptions(this.props.typeValues)} name={this.getName("type")}
                            defaultValue={this.props.initialPredicate.type} onChange={this.onTypeChange.bind(this)}/>
                </Form.Field>

                <Form.Field>
                    <label>Comparator</label>
                    <Select options={generateSelectOptions(this.props.comparatorValues)}
                            name={this.getName("comparator")}
                            defaultValue={this.props.initialPredicate.comparator}/>
                </Form.Field>

                <Form.Field>
                    <label>Value</label>
                    <Input label={{basic: true, content: this.state.unit}} labelPosition="right"
                           name={this.getName("value")}/>
                </Form.Field>

                <Form.Field>
                    <label>Begin time</label>
                    <TimeInput initTime={this.props.initialPredicate.beginTime}/>
                </Form.Field>

                <Form.Field>
                    <label>End time</label>
                    <TimeInput initTime={this.props.initialPredicate.endTime}/>
                </Form.Field>

                <Form.Field>
                    <label>&nbsp;</label>
                    <Button color="red" type="button" onClick={this.onDeleteButtonClicked.bind(this)}>
                        Delete
                    </Button>
                </Form.Field>
            </Form.Group>
        );
    }

    private onDeleteButtonClicked(event: React.FormEvent<HTMLButtonElement>, btn: HTMLButtonElement): void {
        if (this.props.onDelete) {
            this.props.onDelete(this.state.predicate);
        }
    }


    private onTypeChange(event: React.FormEvent<HTMLSelectElement>, {value}): void {
        this.setState({
            unit: this.getUnit(value as Type)
        });
    }

    private getUnit(type: Type): string {
        return this.props.typeValues[type].unit;
    }

    private getName(field: string): string {
        return `predicates[${this.state.predicate.id}][${field}]`;
    }

}