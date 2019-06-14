import React, { Component } from "react";
import "./App.css";
import { Typography, Button, Select, Checkbox, DatePicker } from "antd";
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
    let { basket, price } = this.state;

    if (!basket.includes(meal.id)) {
      basket.push(meal.id);
      price += meal.price;
      price = +price.toFixed(2);
      this.setState({ basket: basket, price: price });
    }
  }

  isAdded(mealId) {
    return this.state.basket.includes(mealId);
  }

  removeMeal(meal) {
    let { basket, price } = this.state;

    if (basket.includes(meal.id)) {
      const index = basket.findIndex(element => element === meal.id);
      basket.splice(index, 1);
      if (basket.length === 0) {
        price = 0;
      } else {
        price -= meal.price;
      }
      price = +price.toFixed(2);
      this.setState({ basket: basket, price: price });
    }
  }

  handleLocationChange(value) {
    this.setState({ locationId: value });
  }

  setOrderDate(value) {
    console.log("date: " + value);
    this.setState({ date: value });
  }

  changeWeekdays(e) {
    if (e.target.checked) {
      let { weekdays } = this.state;

      if (!weekdays.includes(e.target.value)) {
        weekdays.push(e.target.value);
        this.setState({ weekdays: weekdays });
      }
    } else {
      let { weekdays } = this.state;
      const index = weekdays.findIndex(element => element === e.target.value);
      weekdays.splice(index, 1);
      this.setState({ weekdays: weekdays });
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
    console.log("dateSend: ", dateSend);

    const body = {
      mealsId: basket,
      locationId: locationId,
      customerId: customerId,
      date: dateSend
    };

    console.log(body);

    if (weekdays.length === 0) {
      fetch(
        `http://localhost:8080/catering_app_war_exploded/customers/${customerId}/orders`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(body)
        }
      );
    }
  }

  render() {
    const { meals, topMeals, locations, price, date } = this.state;

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
          <Select
            style={{ width: 300 }}
            placeholder="Wybierz miejsce dostawy"
            onChange={this.handleLocationChange}
          >
            {locations &&
              locations.map(location => {
                return (
                  <Select.Option value={location.id}>
                    {location.name}
                  </Select.Option>
                );
              })}
          </Select>
          Jeśli chcesz, aby zamówienie było cykliczne zaznacz dni tygodnia:
          <Checkbox.Group style={{ width: "100%" }}>
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
          <span>Wartość zamówienia: {price}zł</span>
          <DatePicker defaultValue={date} onChange={this.setOrderDate} />
          <Button type="primary" onClick={this.createOrder}>
            Zamów
          </Button>
        </div>
      </div>
    );
  }
}

export default App;
