import React, {Component} from 'react';
import './restaurant.css';

class Restaurant extends Component {

    constructor(props) {
        super(props);
        this.state = {
            restaurantId: props.match.params.restaurantId
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
        fetch("http://localhost:8080/catering_app_war_exploded/orders/active")
            .then(response => response.json())
            .then(orders => {
                orders = orders.filter(order =>
                    new Date(order.date).toDateString() === new Date().toDateString());

                let meals = [];

                for (let order of orders) {
                    meals = meals.concat(order.meals.filter(meal => {
                        return meal.restaurant.id === restaurantId;
                    }));
                }

                console.log(meals);

                this.setState({meals: meals});
            });
    }

    componentDidMount() {
        if (this.props.match.params.restaurantId) {
            this.getRestaurantInfo();
            this.getMeals(+this.props.match.params.restaurantId);
        }
    }

    render() {
        if (this.state.restaurant) {
            return (
                <div className="restaurant">
                    <h1>{this.state.restaurant.name}</h1>
                    <h2>Zamówione posiłki na dzisiaj ({new Date().toDateString()}):</h2>

                </div>
            );
        } else {
            return <h1>"Ładuję restaurację..."</h1>;
        }
    }
}

export default Restaurant;
