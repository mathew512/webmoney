# MoneyWeb Project

## Overview
MoneyWeb is a simple financial management portal built using **Java Servlets**.  
It demonstrates how online banking systems work by allowing users to:
- Register accounts
- View balances
- Perform deposits and withdrawals
- Log out securely

This project is designed to **showcase all three servlet implementation styles**:
1. **Servlet Interface Implementation**  
2. **GenericServlet Implementation**  
3. **HttpServlet Implementation**

---

## Servlet Implementations

### 1. Servlet Interface
- **AboutUsPage.java**  
  Implements the `Servlet` interface directly.  
  Demonstrates the raw lifecycle (`init`, `service`, `destroy`) and outputs static informational content.

### 2. GenericServlet
- **WelcomePage.java**  
  Extends `GenericServlet` and overrides `service()`.  
  Displays the welcome page with navigation links.  

- **LogoutPage.java**  
  Extends `GenericServlet` and overrides `service()`.  
  Handles session invalidation and shows a logout confirmation.

### 3. HttpServlet
- **RegisterPage.java**  
  Extends `HttpServlet`.  
  - `doGet()` → Displays the registration form.  
  - `doPost()` → Processes registration, saves user data in session, and confirms success.

- **TransactionPage.java**  
  Extends `HttpServlet`.  
  - `doGet()` → Displays the transaction dashboard and form.  
  - `doPost()` → Processes deposits/withdrawals, updates session balance, and redirects back to the dashboard.

---

## Project Structure
