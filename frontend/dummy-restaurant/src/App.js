import React, {Component} from 'react';
import {BrowserRouter, Route, NavLink} from 'react-router-dom';
import './App.css';
import Restaurant from './Restaurant/Restaurant';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            restaurants: null
        };
    }

    getRestaurants() {
        fetch("http://localhost:8080/catering_app_war_exploded/restaurants")
            .then(response => response.json())
            .then(restaurants => {
                this.setState({restaurants: restaurants});
            });
    }

    componentDidMount() {
        this.getRestaurants();
    }

    render() {

        const {restaurants} = this.state;

        return (
            <div className="App">
                <BrowserRouter>
                    <h1>Podgląd restauracji zewnętrznych:</h1>
                    {restaurants && <ul className="restaurants-list">
                        {
                            restaurants.map(restaurant => (
                                <li key={restaurant.id}>
                                    <NavLink to={`/restaurant/${restaurant.id}`}>Restauracja {restaurant.name}</NavLink>
                                </li>
                            ))
                        }
                    </ul>}
                    <Route exact path="/restaurant/:restaurantId" component={Restaurant}/>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;
