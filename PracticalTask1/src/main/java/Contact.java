public class Contact {
    private String firstName;
    private String lastName;
    private int phone;
    private bankAccount bAcc;

    public Contact(String firstName, String lastName, int phone, bankAccount bAcc){
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setbAcc(bAcc);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public bankAccount getbAcc() {
        return bAcc;
    }

    public void setbAcc(bankAccount bAccount) {
        this.bAcc = bAccount;
    }
}
