import React, {Component} from "react";
import "./App.css";
import {Typography, Button, Select, Checkbox, DatePicker} from "antd";
import moment from "moment";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            basket: [],
            price: 0,
            customerId: 1,
            locationId: 0,
            date: moment(),
            weekdays: []
        };
        this.getData = this.getData.bind(this);
        this.isAdded = this.isAdded.bind(this);
        this.addMeal = this.addMeal.bind(this);
        this.removeMeal = this.removeMeal.bind(this);
        this.handleLocationChange = this.handleLocationChange.bind(this);
        this.setOrderDate = this.setOrderDate.bind(this);
        this.changeWeekdays = this.changeWeekdays.bind(this);
        this.createOrder = this.createOrder.bind(this);
        this.clearFields = this.clearFields.bind(this);
    }

    getData() {
        Promise.all([
            fetch("http://localhost:8080/catering_app_war_exploded/meals"),
            fetch("http://localhost:8080/catering_app_war_exploded/meals/top-meals"),
            fetch("http://localhost:8080/catering_app_war_exploded/locations")
        ])
            .then(([response1, response2, response3]) =>
                Promise.all([response1.json(), response2.json(), response3.json()])
            )
            .then(([meals, topMeals, locations]) => {
                this.setState({
                    meals: meals,
                    topMeals: topMeals,
                    locations: locations
                });
            });
    }

    componentDidMount() {
        this.getData();
    }

    addMeal(meal) {
        let {basket, price} = this.state;

        if (!basket.includes(meal.id)) {
            basket.push(meal.id);
            price += meal.price;
            price = +price.toFixed(2);
            this.setState({basket: basket, price: price});
        }
    }

    isAdded(mealId) {
        return this.state.basket.includes(mealId);
    }

    removeMeal(meal) {
        let {basket, price} = this.state;

        if (basket.includes(meal.id)) {
            const index = basket.findIndex(element => element === meal.id);
            basket.splice(index, 1);
            if (basket.length === 0) {
                price = 0;
            } else {
                price -= meal.price;
            }
            price = +price.toFixed(2);
            this.setState({basket: basket, price: price});
        }
    }

    handleLocationChange(value) {
        this.setState({locationId: value});
    }

    setOrderDate(value) {
        console.log("date: " + value);
        this.setState({date: value});
    }

    changeWeekdays(e) {
        if (e.target.checked) {
            let {weekdays} = this.state;

            if (!weekdays.includes(e.target.value)) {
                weekdays.push(e.target.value);
                this.setState({weekdays: weekdays});
            }
        } else {
            let {weekdays} = this.state;
            const index = weekdays.findIndex(element => element === e.target.value);
            weekdays.splice(index, 1);
            this.setState({weekdays: weekdays});
        }
    }

    createOrder() {
        const {
            basket,
            price,
            customerId,
            locationId,
            date,
            weekdays
        } = this.state;

        const dateSend = date.format("X");

        if (weekdays.length > 0) {
            const dates = [];
            for (let i = 0; i < weekdays.length; i++) {
                const newDate = moment(date);
                newDate.add(weekdays[i] - date.day(), 'd');

                if (newDate < date) {
                    newDate.add(7, 'd');
                    dates.push(newDate.format('X'));
                } else {
                    dates.push(newDate.format('X'));
                }

            }
            console.log(dates);

            let subBody = {
                mealsId: basket,
                locationId: locationId,
                customerId: customerId,
                dates: dates
            };

            fetch(
                `http://localhost:8080/catering_app_war_exploded/customers/${customerId}/subscriptions`,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(subBody)
                }
            )
        }

        if (weekdays.length === 0) {
            let orderBody = {
                mealsId: basket,
                locationId: locationId,
                customerId: customerId,
                date: dateSend
            };


            fetch(
                `http://localhost:8080/catering_app_war_exploded/customers/${customerId}/orders`,
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(orderBody)
                }
            );
        }
    }

    clearFields() {
        this.setState({
            basket: [],
            price: 0,
            customerId: 1,
            locationId: 0,
            date: moment(),
            weekdays: []
        });
    }

    render() {
        const {meals, topMeals, locations, price, date, locationId} = this.state;

        return (
            <div className="App">
                <header className="App-header">
                    <nav className="header-nav">
                        <a href="#">Historia zamówień</a>
                        <a href="#">Raporty płatności</a>
                    </nav>
                </header>
                <div className="content">
                    <Typography.Title>Menu</Typography.Title>
                    <Typography.Title level={2} className="category-name">
                        Najpopularniejsze Potrawy
                    </Typography.Title>
                    <ul className="top-meals-list">
                        {topMeals &&
                        topMeals.map(meal => {
                            return (
                                <li key={meal.id} className="meal">
                                    {meal.name + " " + meal.price + "zł"}
                                    {this.isAdded(meal.id) ? (
                                        <Button
                                            className="meal-btn"
                                            onClick={() => this.removeMeal(meal)}
                                            type="danger"
                                        >
                                            Usuń
                                        </Button>
                                    ) : (
                                        <Button
                                            className="meal-btn"
                                            onClick={() => this.addMeal(meal)}
                                            type="primary"
                                        >
                                            Dodaj
                                        </Button>
                                    )}
                                </li>
                            );
                        })}
                    </ul>
                    <ul className="meals-categories-list">
                        {meals &&
                        meals.map(mealCategory => {
                            return (
                                <li key={mealCategory.categoryId} className="meals-category">
                                    <Typography.Title className="category-name">
                                        {mealCategory.categoryName}
                                    </Typography.Title>
                                    <ul className="meals-list">
                                        {mealCategory.meals.map(meal => {
                                            return (
                                                <li key={meal.id} className="meal">
                                                    {meal.name + " " + meal.price + "zł"}
                                                    {this.isAdded(meal.id) ? (
                                                        <Button
                                                            className="meal-btn"
                                                            onClick={() => this.removeMeal(meal)}
                                                            type="danger"
                                                        >
                                                            Usuń
                                                        </Button>
                                                    ) : (
                                                        <Button
                                                            className="meal-btn"
                                                            onClick={() => this.addMeal(meal)}
                                                            type="primary"
                                                        >
                                                            Dodaj
                                                        </Button>
                                                    )}
                                                </li>
                                            );
                                        })}
                                    </ul>
                                </li>
                            );
                        })}
                    </ul>
                    <Typography.Title level={2} className="order-details">Szczegóły zamówienia</Typography.Title>
                    <div className="order-form">
                        <Select
                            style={{width: 300, display: "block", margin: "0 auto 20px auto"}}
                            placeholder="Wybierz miejsce dostawy"
                            onChange={this.handleLocationChange}
                        >
                            {locations &&
                            locations.map(location => {
                                return (
                                    <Select.Option key={location.id} value={location.id}>
                                        {location.name}
                                    </Select.Option>
                                );
                            })}
                        </Select>
                        <span className="subscribe-title">Jeśli chcesz, aby zamówienie było cykliczne zaznacz dni tygodnia:</span>
                        <Checkbox.Group style={{width: "100%", marginBottom: "20px"}}>
                            <Checkbox value={1} onChange={this.changeWeekdays}>
                                Poniedziałek
                            </Checkbox>
                            <Checkbox value={2} onChange={this.changeWeekdays}>
                                Wtorek
                            </Checkbox>
                            <Checkbox value={3} onChange={this.changeWeekdays}>
                                Środa
                            </Checkbox>
                            <Checkbox value={4} onChange={this.changeWeekdays}>
                                Czwartek
                            </Checkbox>
                            <Checkbox value={5} onChange={this.changeWeekdays}>
                                Piątek
                            </Checkbox>
                            <Checkbox value={6} onChange={this.changeWeekdays}>
                                Sobota
                            </Checkbox>
                            <Checkbox value={7} onChange={this.changeWeekdays}>
                                Niedziela
                            </Checkbox>
                        </Checkbox.Group>
                        <DatePicker className="date-picker" defaultValue={date} onChange={this.setOrderDate}/>
                        <span className="order-price">Wartość zamówienia: {price}zł</span>
                        <Button disabled={price === 0 || locationId === 0} type="primary" onClick={this.createOrder}>
                            Zamów
                        </Button>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;
