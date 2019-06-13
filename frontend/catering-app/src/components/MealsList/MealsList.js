import React, {Component} from "react";
import './meals-list.css';


class MealsList extends Component {

    constructor(props) {
        super(props);
        this.addToCart  = this.addToCart.bind(this);
    }

    addToCart() {
        console.log("added to cart");
    }

    render() {

        const {meals} = this.props;

        return (
            <ul className="meals-categories-list">
                {meals.map(mealCategory => {
                    return (
                        <li key={mealCategory.categoryId} className="meals-category">
                            <span className="category-name">{mealCategory.categoryName}</span>
                        <ul className="meals-list">
                            {mealCategory.meals.map(meal =>{
                                return <li key={meal.id} className="meal">{meal.name}</li>
                            })}
                        </ul>
                    </li>);
                })}
        </ul>

    )
        ;
    }
}

export default MealsList;