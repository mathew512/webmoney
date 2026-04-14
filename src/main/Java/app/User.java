package app;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User entity annotated for WebMoney Framework.
 * Generates registration forms and tables dynamically.
 */
@WebMoneyForm(label = "Register User", actionUrl = "./register")
@WebMoneyTable(label = "Users", tableUrl = "./list_users")
public class User {

    @WebMoneyFormField(label = "Username", placeholder = "Enter Username")
    @WebMoneyTableCol(label = "Username")
    private String username;

    @WebMoneyFormField(label = "Email", placeholder = "Enter Email")
    @WebMoneyTableCol(label = "Email")
    private String email;

    @WebMoneyTableCol(label = "Balance")
    private double balance;

    @WebMoneyTableCol(label = "Status")
    private String registrationStatus;

    // Transaction history is not part of form/table, but used in htmlHistory()
    private List<String> history = new ArrayList<>();

    public User(String username, String email, double balance, String registrationStatus) {
        this.username = username;
        this.email = email;
        this.balance = balance;
        this.registrationStatus = registrationStatus;
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public String getRegistrationStatus() { return registrationStatus; }

    public void addTransaction(String action, double amount) {
        String record = action + " Ksh " + String.format("%.2f", amount) +
                " on " + LocalDateTime.now();
        history.add(record);
    }

    public List<String> getHistory() { return history; }
}
