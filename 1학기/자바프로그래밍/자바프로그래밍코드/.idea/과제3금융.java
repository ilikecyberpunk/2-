import java.util.Objects;

public class 과제3금융 {

    public static final class Account {
        private final int account_number;
        private final String owner_name;
        private final int current_money;

        public Account(int account_number, String owner_name, int current_money) {
            if (current_money < 0 || owner_name == null || owner_name.isBlank()) {
                throw new IllegalArgumentException();
            }
            this.account_number = account_number;
            this.owner_name = owner_name;
            this.current_money = current_money;
        }

        public int getAccount_number() { return account_number; }
        public String getOwner_name() { return owner_name; }
        public int getCurrent_money() { return current_money; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Account)) return false;
            Account account = (Account) o;
            return account_number == account.account_number && 
                   current_money == account.current_money && 
                   Objects.equals(owner_name, account.owner_name);
        }

        @Override
        public int hashCode() { return Objects.hash(account_number, owner_name, current_money); }

        @Override
        public String toString() { return account_number+ " "  + owner_name + " " + current_money; }
    }

    public static final class Transaction {
        private final int id;
        private final int amount;

        public Transaction(int id, int amount) {
            if (amount <= 0) throw new IllegalArgumentException();
            this.id = id;
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Transaction)) return false;
            Transaction t = (Transaction) o;
            return id == t.id && amount == t.amount;
        }

        @Override
        public int hashCode() { return Objects.hash(id, amount); }

        @Override
        public String toString() { return id + " " + amount; }
    }

    public static void main(String[] args) {
        Account a1 = new Account(100, "kimtaeho", 1000);
        Account a2 = new Account(100, "alina", 1000);
        Account a3 = new Account(200, "asuka", 2000);

        System.out.println(a1 != null);
        System.out.println(a1.getAccount_number() == 100);
        System.out.println(a1.getOwner_name().equals("kimtaeho"));
        System.out.println(a1.getCurrent_money() == 1000);
        System.out.println(a1.equals(a2));
        System.out.println(a1.equals(a3));
        System.out.println(a1.hashCode() == a2.hashCode());
        System.out.println(a1.toString());
    }
}