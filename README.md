# Crypto Trading System (Spring Boot)

This project is a simplified crypto trading backend system built with **Java + Spring Boot**. It allows users to:
- Fetch the best crypto prices aggregated from Binance and Huobi
- Execute trades (BUY/SELL) on BTCUSDT and ETHUSDT
- Track wallet balances
- View trading history
- Explore and test APIs via Swagger UI

---

## Features

- ✅ Price Aggregation from Binance & Huobi every 10s
- ✅ GET latest best bid/ask price
- ✅ POST trade execution API (BUY/SELL)
- ✅ GET wallet balance (USDT, BTC, ETH)
- ✅ GET trading history
- ✅ Swagger UI for testing and documentation
- ✅ H2 in-memory database for demo purposes

---

##  Technologies Used

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database
- Swagger (Springdoc OpenAPI)
- Lombok
- Maven

---

## API Endpoints

| Method | Endpoint             | Description                     |
|--------|----------------------|---------------------------------|
| GET    | `/api/prices/{symbol}` | Get latest price (e.g. BTCUSDT) |
| POST   | `/api/trade`         | Execute BUY/SELL trade          |
| GET    | `/api/wallet`        | View wallet balance             |
| GET    | `/api/history`       | View trading history            |

---

## Swagger UI
Access interactive API docs at:
http://localhost:8080/swagger-ui/index.html

## H2 Console
Access the in-memory database:
http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb
- User: sa
- Password: (leave blank)

## Wallet Info (Default)
- ID: 1
- USDT Balance: 50,000
- BTC/ETH: 0

## Notes
- Only BTCUSDT and ETHUSDT are supported
- No real trading/integration with external systems

## Project Structure
src/
- model/ → Entity classes
- controller/ → REST APIs
- service/ → Business logic
- repository/ → Spring Data JPA Interfaces
- dto/ → Request & response objects
- config/ → Swagger & Wallet initialization
- TradingSystemApplication.java  → Main class to run the app
