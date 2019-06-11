import React from 'react';
import './App.css';
import * as orders from './orders.json';

import {List} from 'antd';
import 'antd/dist/antd.css';

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
        fetch("http://localhost:8080/catering_app_war_exploded/customers/orders")
            .then(orders => orders.json())
            .then(orders => {
                this.setState({orders: orders});
            })
    }

    componentDidMount() {
        this.getOrders();
        setInterval(() => this.getOrders(), 100000);
    }

    completeOrder(id) {

    }

    cancelOrder(id) {

    }


    render() {
        const {orders} = this.state;

        return (
            <div className="App">
                <div className="orders-list">
                <h1>Zamówienia do dostarczenia:</h1>

                {orders.map(order => {
                    return (
                        <div className="order">
                            <h2>Zamówienie nr {order.id}</h2>
                            <List
                                className="orders-list"
                                itemLayout="horizontal"
                                dataSource={order.meals}
                                renderItem={meal => (
                                    <List.Item actions={[<a>anuluj</a>, <a>dostarcz</a>]}>
                                        <List.Item.Meta
                                            title={meal.name}
                                            description="adres odbioru, adres dostawy"
                                        />
                                        <div className="order-date">{order.date}</div>
                                    </List.Item> )}
                            />
                        </div>
                    );
                })
                }
                </div>
            </div>
          );
    }
}

export default App;







