class InvalidAmountException extends Exception{
    private double detail;
    InvalidAmountException(double a) {
        detail = a;
    }
    @Override
    public String toString() {
        return "The given amount can't be negative: " + detail;
    }
}
class InvalidBalanceException extends Exception{
    private double detail;
    InvalidBalanceException(double a) {
        detail = a;
    }
    @Override
    public String toString() {
        return "The account balance can't be less than zero: " + detail;
    }
}
interface Account {
    // the following method returns the balance
    double balance();

    // the following method subtracts the amount from the balance,
    // the amount in the method can’t be negative, the balance can’t be negative
    void debit(double amount) throws InvalidAmountException, InvalidBalanceException;

    // the following method adds the amount directly to the balance,
    // the amount in the method can’t be negative
    void credit(double amount) throws InvalidAmountException;
}

class SavingsAccount implements Account{
    double deposit;
    public SavingsAccount(){
        deposit=0;
    }

    @Override
    public double balance(){
        return deposit;
    }

    @Override
    public void debit(double amount) throws InvalidAmountException, InvalidBalanceException{
        System.out.println("Dedit Request: "+amount);
        if(amount<0) throw new  InvalidAmountException(amount);
        else if(deposit-amount<0) throw new  InvalidBalanceException(deposit-amount);
        else deposit-=amount;  
    }

    @Override
    public void credit(double amount) throws InvalidAmountException{
        System.out.println("Credit Request: "+amount);
        if(amount<0) throw new  InvalidAmountException(amount);
        else deposit+=amount;  
    }
}
public class AccountTest {
    public static void main(String[] args) {
        Account a = new SavingsAccount();
        
        try{
            a.credit(1000);
        }catch(Exception e){
            System.out.println(e);
        }finally{
            System.out.println("Balance: " + a.balance());
        }
        
        try{
            a.credit(-100);
        }catch(Exception e){
            System.out.println(e);
        }finally{
            System.out.println("Balance: " + a.balance());
        }
        
        try{
            a.debit(2000);
        }catch(Exception e){
            System.out.println(e);
        }finally{
            System.out.println("Balance: " + a.balance());
        }
        
        try{
            a.debit(200);
        }catch(Exception e){
            System.out.println(e);
        }finally{
            System.out.println("Balance: " + a.balance());
        }
        
        try{
            a.debit(-200);
        }catch(Exception e){
            System.out.println(e);
        }finally{
            System.out.println("Balance: " + a.balance());
        }
        
    }
}

/* Output of the main method:
Credit Request: 1000.0
Balance: 1000.0
Credit Request: -100.0
The given amount can’t be negative: -100.0
Balance: 1000.0
Debit Request: 2000.0
The account balance can’t be less than zero: -1000.0
Balance: 1000.0
Debit Request: 200.0
Balance: 800.0
Debit Request: -200.0
The given amount can’t be negative: -200.0
Balance: 800.0
*/