import java.util.ArrayList;

public class BanqueCompte {
    public static ArrayList<BanqueInfo> predefinedAccount() {
        ArrayList<BanqueInfo> accounts = new ArrayList<>();

        BanqueInfo account1 = new BanqueInfo();
        account1.ouvrirUnCompte("22510", "Epargne", "Jean-claude", 150000);
        accounts.add(account1);

        BanqueInfo account2 = new BanqueInfo();
        account2.ouvrirUnCompte("22511", "Epargne", "Diomande", 150000);
        accounts.add(account2);

        BanqueInfo account3 = new BanqueInfo();
        account3.ouvrirUnCompte("22512", "Epargne", "Maruis", 150000);
        accounts.add(account3);

        BanqueInfo account4 = new BanqueInfo();
        account4.ouvrirUnCompte("22513", "Epargne", "Kalou", 150000);
        accounts.add(account4);

        return accounts;

    }
}
