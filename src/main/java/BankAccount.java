import java.util.ArrayList;

public class BankAccount {

    final int accountNum;
    double balance;
    ArrayList<String> history = new ArrayList<>();

    public BankAccount(int num, double balance){
        this.accountNum = num;
        this.balance = balance;
    }

    public int getAccount(){
        return accountNum;
    }

    public void transferTo(BankAccount payee, double amount){
        balance -= amount;
        updateHistoryOut(payee, amount);
        payee.transferFrom(this, amount);
    }

    public void transferFrom(BankAccount payer, double amount){
        balance += amount;
        updateHistoryIn(payer, amount);
    }

    public void updateHistoryIn(BankAccount payer, double amount){
        history.add("You were paid $"+ amount + " by account number: " + payer.getAccount() + ".");

    }

    public void updateHistoryOut(BankAccount payee, double amount){
        history.add("You paid $" + amount + " to account number: " + payee.getAccount() + ".");
    }

    public double getBalance(){
        return balance;
    }

    public String getHistory(){
        String out = "";
        for(int i = 0; i < history.size(); i++){
            out = out + history.get(i);
            if (i < history.size() - 1){
                out += " ";
            }
        }
        return out;
    }

}
