const Input = semanticUIReact.Input;
const Select = semanticUIReact.Select;
const Button = semanticUIReact.Button;

const Types = Object.freeze({
    Temperature: 0,
    Pressure: 1,
    WindSpeed: 2
});

const Comparators = Object.freeze({
    [">"]: 0,
    ["<"]: 1,
    ["="]: 2,
    [">="]: 3,
    ["<="]: 4
});

class Predicate extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            unit: this.getUnit(Types.Temperature)
        };
    }

    static generateSelectOptions(obj) {
        return Object.entries(obj).map(([text, id]) => {
            return {
                text: text,
                value: id
            }
        });
    }

    getName(field) {
        return `predicates[${this.props.id}][${field}]`;
    }

    getUnit(type) {
        switch (type) {
            case Types.Temperature:
                return "°C";
            case Types.Pressure:
                return "pa";
            case Types.WindSpeed:
                return "km/h";
            default:
                return "";
        }
    }

    onTypeChange(e, {value}) {
        this.setState({
            unit: this.getUnit(value)
        });
    }

    delete() {
        if (this.props.onDelete) {
            this.props.onDelete();
        }
    }

    render() {
        return (
            <div className="predicate fields">
                <Select options={Predicate.generateSelectOptions(Types)} name={this.getName("type")}
                        defaultValue={Types.Temperature} onChange={this.onTypeChange.bind(this)}/>

                <Select options={Predicate.generateSelectOptions(Comparators)} name={this.getName("comparator")}
                        defaultValue={Comparators[">"]}/>

                <Input label={{basic: true, content: this.state.unit}} labelPosition="right"
                       name={this.getName("value")}/>

                <Button color="red" onClick={this.delete.bind(this)} type="button">
                    Delete
                </Button>
            </div>
        );
    }
}

const $predicatesList = document.getElementById("predicates-list");
const predicates = [];

class AddPredicate extends React.Component {

    static renderPredicate($predicate) {
        const onDelete = () => {
            const index = predicates.indexOf($predicate);
            if (index > -1) {
                predicates.splice(index, 1);
            }
            $predicatesList.removeChild($predicate);
        };
        ReactDOM.render(<Predicate id={predicates.length} onDelete={onDelete}/>, $predicate);
        predicates.push($predicate);
    }

    static add() {
        const $newPredicate = document.createElement("div");
        $predicatesList.appendChild($newPredicate);
        AddPredicate.renderPredicate($newPredicate);
    }

    render() {
        return (
            <Button onClick={AddPredicate.add} id="add-predicate" type="button">
                Add predicate
            </Button>
        )
    }
}

// Render the "Add predicate" button
const $addPredicate = document.getElementById("add-predicate");
ReactDOM.render(
    <AddPredicate/>,
    $addPredicate
);

// Render the existing predicates
Array.from($predicatesList.children).forEach($p => AddPredicate.renderPredicate($p));