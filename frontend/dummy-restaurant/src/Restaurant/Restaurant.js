import React, {Component} from 'react';
import './restaurant.css';

class Restaurant extends Component {

    constructor(props) {
        super(props);
        this.state = {
            restaurantId: 0
        };
        this.getRestaurantInfo = this.getRestaurantInfo.bind(this);
        this.getMeals = this.getMeals.bind(this);
    }

    getRestaurantInfo() {
        fetch("http://localhost:8080/catering_app_war_exploded/restaurants/" + this.props.match.params.restaurantId)
            .then(response => response.json())
            .then(restaurant => this.setState({restaurant}));
    }

    getMeals(restaurantId) {
        fetch("http://localhost:8080/catering_app_war_exploded/orders/all")
            .then(response => response.json())
            .then(orders => {
                orders = orders.filter(order => {
                    return new Date(order.date).toDateString() === new Date().toDateString()
                        && (order.state === "SUBSCRIBED" || order.state === "ORDERED");
            });

                let meals = [];

                for (let order of orders) {
                    meals = meals.concat(order.meals.filter(meal => {
                        return meal.restaurant.id === restaurantId;
                    }));
                }
                this.setState({meals: meals});
            });
    }

    componentDidMount() {
        if (this.props.match.params.restaurantId) {
            this.getRestaurantInfo();
            this.getMeals(+this.props.match.params.restaurantId);
            setInterval(() => this.getMeals(+this.props.match.params.restaurantId), 100000);
        }
    }

    componentDidUpdate() {
        const restaurantId = +this.props.match.params.restaurantId;

        if (restaurantId !== this.state.restaurantId)  {
            this.getRestaurantInfo();
            this.getMeals(restaurantId);
            this.setState(({restaurantId: restaurantId}));
        }
    }

    render() {
        if (this.state.restaurant && this.state.meals) {
            return (
                <div className="restaurant">
                    <h1>{this.state.restaurant.name}</h1>
                    <h2>Zamówione posiłki na dzisiaj ({new Date().toDateString()}):</h2>
                    <ul className="meals-list">
                    {this.state.meals.map(meal => {
                        return <li key={meal.id}>{meal.name}</li>
                    })}
                    </ul>
                </div>
            );
        } else {
            return <h1>"Ładuję restaurację..."</h1>;
        }
    }
}

export default Restaurant;
