import React, {Component} from 'react';
import './App.css';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      restaurants: []
    };
  }

  getRestaurants() {
    fetch("http://localhost:8080/catering_app_war_exploded/restaurants")
        .then(restaurants => {
          this.setState({});
        });
  }

  componentDidMount() {
    this.getRestaurants();
  }

  render() {
    return (
        <div className="App">

        </div>
    );
  }
}

export default App;
