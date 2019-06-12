project starts on: http://localhost:8080/catering_app_war_exploded/

**available endpoints GET**:

/customers/{id}

/customers/{id}/orders

/customers/{id}/history

/customers/{id}/subscriptions

/meals

/meals/top-meals

/meals/categories

/orders/all

/orders/active

**available endpoints POST**:

/customers/{id}/orders -> src/main/java/com/barankosecki/dto/OrderFromClientDTO.java

/orders/{id} -> no body is required, used to mark order as delivered

**available endpoints DELETE**:

/orders/{id}
