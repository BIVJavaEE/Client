import {Predicate} from "./models/predicate";
import {Type, TypeValue} from "./models/type";
import {Comparator, ComparatorValue} from "./models/comparator";
import * as React from "react";
import {PredicateComponent} from "./predicate.component";
import {Button} from "semantic-ui-react";

export interface PredicatesListState {
    predicates: Array<Predicate>;
}

export interface PredicatesListProps {
    typeValues: Array<TypeValue>;
    comparatorValues: Array<ComparatorValue>;
    initialType?: Type;
    initialPredicates?: Array<Predicate>;
}

export class PredicatesListComponent extends React.Component<PredicatesListProps, PredicatesListState> {

    public state = {
        predicates: this.props.initialPredicates || []
    };

    public render(): JSX.Element {
        return (
            <div>
                <div id="predicates-list">
                    {this.renderPredicates()}
                </div>
                <Button onClick={this.addPredicate.bind(this)} id="add-predicate" type="button">
                    Add predicate
                </Button>
            </div>
        );
    }

    private deletePredicate(predicate: Predicate): void {
        const predicates = this.state.predicates;
        const index = predicates.indexOf(predicate);
        if (index !== -1) {
            predicates.splice(index, 1);
            let id = -1;
            for (const predicate of predicates) {
                predicate.id = ++id;
            }
            this.setState({
                predicates: predicates
            });
        }
    }

    private addPredicate(): void {
        const predicates = this.state.predicates;
        const id = predicates.length;
        predicates.push(new Predicate(id, Type.Temperature, Comparator.Superior));
        this.setState({
            predicates: predicates
        })
    }

    private renderPredicates(): Array<JSX.Element> {
        return this.state.predicates.map(predicate => {
            return <PredicateComponent
                key={predicate.id}
                typeValues={this.props.typeValues}
                comparatorValues={this.props.comparatorValues}
                initialPredicate={predicate}
                onDelete={this.deletePredicate.bind(this)}/>
        });
    }

}