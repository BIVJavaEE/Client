import * as React from "react";
import {Form, Input, Radio} from "semantic-ui-react";
import {Type, TypeValue} from "./models/type";
import {ComparatorValue} from "./models/comparator";
import {Predicate} from "./models/predicate";
import {PredicatesListComponent} from "./predicates-list.component";

interface MainState {
    type: Type;
    predicates: Array<Predicate>;
}

export interface MainProps {
    typeValues: Array<TypeValue>;
    comparatorValues: Array<ComparatorValue>;
    initialType?: Type;
    initialPredicates?: Array<Predicate>;
}

export class Main extends React.Component<MainProps, MainState> {

    public state = {
        type: this.props.initialType || Type.Temperature,
        predicates: this.props.initialPredicates || []
    };

    public render(): JSX.Element {
        return (
            <div>
                <h1 id="title"> Create an alert </h1>
                <Form>

                    <h2>General information</h2>
                    <Form.Field>
                        <label>Name</label>
                        <Input/>
                    </Form.Field>
                    <Form.Group grouped>
                        <label>Priority</label>
                        {this.renderTypeFields()}
                    </Form.Group>

                    <h2>Predicates</h2>
                    <PredicatesListComponent typeValues={this.props.typeValues} comparatorValues={this.props.comparatorValues}
                    initialPredicates={this.props.initialPredicates}/>

                    <h2>Sensors</h2>

                </Form>
            </div>
        )
    }

    private renderTypeFields(): Array<JSX.Element> {
        return this.props.typeValues.map(typeValue => {
            return (
                <Form.Field key={typeValue.id}>
                    <Radio label={typeValue.text} value={typeValue.id} name="type"/>
                </Form.Field>
            )
        });
    }


}