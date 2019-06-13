import React, {Component} from 'react';
import './App.css';
import MealsList from "./components/MealsList/MealsList";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {};
        this.getMeals = this.getMeals.bind(this);
    }

    getMeals() {
        Promise.all([
            fetch("http://localhost:8080/catering_app_war_exploded/meals"),
            fetch("http://localhost:8080/catering_app_war_exploded/meals/top-meals")
        ])
            .then(([response1, response2]) => Promise.all([response1.json(), response2.json()]))
            .then(([data1, data2]) => {
                this.setState({
                    meals: data1,
                    topMeals: data2
                });
            });
    }

    componentDidMount() {
        this.getMeals();
    }

    render() {

        const {meals} = this.state;

        return (
            <div className="App">
                <header className="App-header">
                    <nav className="header-nav">
                        <a href="">Historia zamówień</a>
                        <a href="">Raporty płatności</a>
                    </nav>
                </header>
                <div className="content">
                    <h1>Menu</h1>
                    {meals && <MealsList meals={meals}/>}
                </div>
            </div>
        );
    }
}

export default App;
