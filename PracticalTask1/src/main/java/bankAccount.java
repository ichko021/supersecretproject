import org.iban4j.Iban;

public class bankAccount {

    private String bankName;
    private Iban iban;
    private double Balance;

    public bankAccount(String bankName, Iban iban, double Balance){
        setBankName(bankName);
        setIBAN(iban);
        setBalance(Balance);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Iban getIBAN() {
        return iban;
    }

    public void setIBAN(Iban iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public void top_up(double add_money){
        this.Balance = this.Balance + add_money;
        System.out.println("You have added BGN "+add_money+". Your current balance is BGN "+this.Balance+".");
    }
}
