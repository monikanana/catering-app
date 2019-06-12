import React from "react";
import "./App.css";
//import * as orders from './orders.json';
import "antd/dist/antd.css";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      orders: []
    };
    this.getOrders = this.getOrders.bind(this);
    this.completeOrder = this.completeOrder.bind(this);
    this.cancelOrder = this.cancelOrder.bind(this);
  }

  getOrders() {
    fetch("http://localhost:8080/catering_app_war_exploded/orders/active")
      .then(orders => orders.json())
      .then(orders => {
        this.setState({ orders: orders });
      });
  }

  componentDidMount() {
    this.getOrders();
    setInterval(() => this.getOrders(), 100000);
  }

  completeOrder(id) {
    fetch(
      "http://localhost:8080/catering_app_war_exploded/orders/" +
        id +
        "/deliver",
      {
        method: "POST"
      }
    ).then(() => this.getOrders());
  }

  cancelOrder(id) {
    fetch(
      "http://localhost:8080/catering_app_war_exploded/orders/" +
        id +
        "/cancel",
      {
        method: "POST"
      }
    ).then(() => this.getOrders());
  }

  render() {
    const { orders } = this.state;

    return (
      <div className="App">
        <h1 className="app-title">Zamówienia do dostarczenia:</h1>
        <ul className="orders-list">
          {orders.map(order => {
            return (
              <li key={order.id} className="order">
                <h2>Zamówienie nr {order.id}</h2>
                <h3>Imię klienta: {order.customer.name}</h3>
                <h3>
                  Adres dostawy: {order.location.name}, {order.location.address}
                </h3>
                <h5 className="order-date">
                  {new Date(order.date).toDateString()}
                </h5>
                <ul className="meals-list">
                  {order.meals.map(meal => (
                    <li key={meal.id}>
                      <h4 className="meal-name">{meal.name}</h4>
                      <h5 className="meal-restaurant">{`Odbierz z: ${
                        meal.restaurant.address
                      }`}</h5>
                    </li>
                  ))}
                </ul>
                <div className="actions">
                  <button
                    className="action-btn"
                    onClick={() => this.cancelOrder(order.id)}
                  >
                    Anuluj zamówienie
                  </button>
                  <button
                    className="action-btn"
                    onClick={() => this.completeOrder(order.id)}
                  >
                    Dostarcz zamówienie
                  </button>
                </div>
              </li>
            );
          })}
        </ul>
      </div>
    );
  }
}

export default App;
