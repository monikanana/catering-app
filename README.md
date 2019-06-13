project starts on: http://localhost:8080/catering_app_war_exploded/

**GET** /customers/{id}

**GET** /customers/{id}/orders

**GET** /customers/{id}/history

**GET** /customers/{id}/subscriptions

**POST** /customers/{id}/orders -> body: src/main/java/com/barankosecki/dto/OrderFromClientDTO.java. UWAGA: zamówienie jest wysyłane na klienta o id z URL-a a nie z body

**POST** /customers/{id}/subscriptions -> body: src/main/java/com/barankosecki/dto/SubscriptionFromClientDTO.java

**GET** /locations

**GET** /locations/{id}

**GET** /meals

**GET** /meals/categories

**GET** /meals/top-meals (zwraca top-3)

**GET** /orders/all

**GET** /orders/active

**GET** /orders/subscribed

**POST** /orders/{id}/deliver -> no payload

**POST** /orders/{id}/cancel -> no payload

**POST** /orders/{id}/cancel-subscription -> no payload

**GET** /restaurants

**GET** /restaurants/{id}
