import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Iterator;

public class BanqueInfo {

    private String accno; // numéro du compte
    private String ac_type; // type de compte
    private String name; // nom de l'utilisateur
    private long solde; // le solde de l'utilisateur
    private static ArrayList<BanqueInfo> accounts = new ArrayList<>(); // liste des comptes

    Scanner sc = new Scanner(System.in);

    /* CODE POUR OUVRIR UN COMPTE */
    public void ouvrirUnCompte() {
        System.out.println("Entrer le nom");
        name = sc.next();

        System.out.println("Entrer le numero du compte");
        accno = sc.next();

        System.out.println("Veuillez choisir le type de compte");
        System.out.println("1: Epargne");
        System.out.println("2: Courant");
        int ch = sc.nextInt();

        if (ch == 1 || ch == 2) {
            ac_type = (ch == 1) ? "Epargne" : "Courant";
            System.out.println("Merci d'avoir choisi " + ac_type);
        } else {
            System.out.println("Le numéro choisir ne correspond pas au type de choix de compte");
            return;
        }

        System.out.println("Entrer le solde");
        solde = sc.nextLong();

        // Ajout du compte à la liste des comptes
        accounts.add(this);
    }

    public void ouvrirUnCompte(String accno, String ac_type, String nom, long solde) {
        this.accno = accno;
        this.ac_type = ac_type;
        this.name = nom;
        this.solde = solde;
        accounts.add(this);
    }

    /* CODE POUR AFFICHER UN COMPTE */
    public void afficherUnCompte() {
        System.out.println("---le nom est: " + name);
        System.out.println("---le numero de la carte est: " + accno);
        System.out.println("---Le type de compte choisir est: " + ac_type);
        System.out.println("---Le solde du compte est: " + solde);
        System.out.println("");
    }

    /* CODE POUR DEPOSER DE L'ARGENT */
    public void depot() {
        long depot;
        System.out.println("Combien voulez vous deposer ?");
        depot = sc.nextLong();
        solde = solde + depot;
        System.out.println("Depot effectuer avec sucess, votre nouveau solde est " + solde);
    }

    /* CODE POUR RETIRER DE L'ARGENT */
    public void retrait() {
        long retrait;
        System.out.println("Combien voulez vous retirer ?");
        retrait = sc.nextLong();
        if (retrait <= solde) {
            solde = solde - retrait;
            System.out.println("Retrait effectuer avec sucess, votre nouveau solde est " + solde);
        } else {
            System.out.println("Solde insuffisant");
        }
    }

    /* CODE POUR CHERCHER UN COMPTE */
    public boolean search(String ac_no) {
        if (accno.equals(ac_no)) {
            afficherUnCompte();
            return true;
        }
        return false;
    }

    /* CODE POUR SUPPRIMER UN COMPTE */
    public static void supprimerUnCompte(String ac_no) {
        Iterator<BanqueInfo> iterator = accounts.iterator();
        while (iterator.hasNext()) {
            BanqueInfo account = iterator.next();
            if (account.accno.equals(ac_no)) {
                try (Scanner sc = new Scanner(System.in)) {
                    System.out.println("Êtes-vous sûr de vouloir supprimer le compte " + ac_no + " ? (o/n)");
                    char confirmation = sc.next().charAt(0);
                    if (confirmation == 'o' || confirmation == 'O') {
                        iterator.remove();
                        System.out.println("Le numéro du compte " + ac_no + " a été supprimé");
                    } else {
                        System.out.println("Le processus a été annulé");
                    }
                }
                return;
            }
        }
        System.out.println("Le numéro du compte ne correspond pas.");
    }

    /* CODE POUR TRANSFERER DE L'ARGENT */
    public void transfert() {
        System.out.println("Entrer le numéro du compte destinataire");
        String num_destinataire = sc.next();
        BanqueInfo compte_destinataire = null;
        for (BanqueInfo banque : accounts) {
            if (banque.accno.equals(num_destinataire)) {
                compte_destinataire = banque;
                break;
            }
        }
        if (compte_destinataire == null) {
            System.out.println("Échec, le numéro de compte destinataire n'existe pas");
            return;
        }

        System.out.println("Combien voulez-vous transférer ?");
        long montant = sc.nextLong();

        if (montant <= solde) {
            solde -= montant;
            compte_destinataire.solde += montant;
            System.out.println("Transfert effectué avec succès, votre nouveau solde est " + solde);
        } else {
            System.out.println("Solde insuffisant pour le transfert.");
        }
    }

    /* CODE POUR MODIFIER UN COMPTE */
    public void update() {
        System.out.println("Que voulez-vous modifier?");
        System.out.println("1: Nom");
        System.out.println("2: Type de compte");
        System.out.println("3: Numéro de compte");
        int choix = sc.nextInt();

        switch (choix) {
            case 1:
                System.out.println("Entrer le nouveau nom");
                String newName = sc.next();
                setName(newName);
                System.out.println("Nom mis à jour avec succès");
                break;
            case 2:
                System.out.println("Veuillez choisir le nouveau type de compte");
                System.out.println("1: Epargne");
                System.out.println("2: Courant");
                int ch = sc.nextInt();
                if (ch == 1 || ch == 2) {
                    setAcType((ch == 1) ? "Epargne" : "Courant");
                    System.out.println("Type de compte mis à jour avec succès");
                } else {
                    System.out.println("Le numéro choisi ne correspond pas au type de choix de compte");
                }
                break;
            case 3:
                System.out.println("Entrer le nouveau numéro de compte");
                String newAccno = sc.next();
                setAccno(newAccno);
                System.out.println("Numéro de compte mis à jour avec succès");
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAcType(String ac_type) {
        this.ac_type = ac_type;
    }

    private void setAccno(String accno) {
        this.accno = accno;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<BanqueInfo> banques = BanqueCompte.predefinedAccount();

        int essaie = 0;
        int choix = 0;
        do {

            System.out.println("Bienvenue à la banque");
            System.out.println("1: Afficher les detais des comptes");
            System.out.println("2: Chercher un compte");
            System.out.println("3: Depot");
            System.out.println("4: Retrait");
            System.out.println("5: Supprimer un compte");
            System.out.println("6: Quitter");
            System.out.println("7: Transferer de l'argent");
            System.out.println("8: Moddifier un compte");

            try {
                String input = sc.next();
                try {
                    choix = Integer.parseInt(input);
                    switch (choix) {
                        case 1:
                            for (BanqueInfo banque : banques) {
                                banque.afficherUnCompte();
                            }
                            break;

                        case 2:
                            System.out.println("Entrer un numero de compte");
                            String ac_no = sc.next();
                            boolean trouver = false;
                            for (BanqueInfo banque : banques) {
                                if (banque.search(ac_no)) {
                                    trouver = true;
                                    break;
                                }
                            }
                            if (!trouver) {
                                System.out.println("Echec de recherche le compte n'existe pas");
                            }
                            break;

                        case 3:
                            System.out.println("Entrer un numero de compte");
                            ac_no = sc.next();
                            trouver = false;
                            for (BanqueInfo banque : banques) {
                                if (banque.search(ac_no)) {
                                    banque.depot();
                                    trouver = true;
                                    break;
                                }
                            }
                            if (!trouver) {
                                System.out.println("Echec le numero de depot n'existe pas");
                            }
                            break;

                        case 4:
                            System.out.println("Entrer un numero de retrait");
                            ac_no = sc.next();
                            trouver = false;
                            for (BanqueInfo banque : banques) {
                                if (banque.search(ac_no)) {
                                    banque.retrait();
                                    trouver = true;
                                    break;
                                }
                            }
                            if (!trouver) {
                                System.out.println("Echec le numero de retrait n'existe pas");
                            }
                            break;

                        case 5:
                            System.out.println("Entrer le numero de compte que vous souhaitez supprimer");
                            ac_no = sc.next();
                            supprimerUnCompte(ac_no);
                            banques.removeIf(banque -> banque.accno.equals(ac_no)); // Remove from local list as well
                            break;

                        case 6:
                            System.out.println("Merci d'avoir utilisé notre service.");
                            break;

                        case 7:
                            System.out.println("Entrer votre numéro de compte");
                            ac_no = sc.next();
                            trouver = false;
                            for (BanqueInfo banque : banques) {
                                if (banque.search(ac_no)) {
                                    banque.transfert();
                                    trouver = true;
                                    break;
                                }
                            }
                            if (!trouver) {
                                System.out.println("Echec le numero de transfert n'existe pas");
                            }
                            break;

                        case 8:
                            System.out.println("Entrer le numéro de compte à modifier");
                            ac_no = sc.next();
                            trouver = false;
                            for (BanqueInfo banque : banques) {
                                if (banque.search(ac_no)) {
                                    banque.update();
                                    trouver = true;
                                    break;
                                }
                            }
                            if (!trouver) {
                                System.out.println("Echec le numero à modifié n'existe pas");
                            }
                            break;

                        default:
                            System.out.println("Le Numero " + choix + " ne correspond pas au nombre d'option ");
                            essaie++;
                            if (essaie > 2) {
                                System.out.println("Vous avez dépassé le nombre de tentatives autorisées.");
                            } else {
                                System.out.println(
                                        "Veuillez rentrer les options entre 1 et 7, Il vous reste " + (3 - essaie)
                                                + " tentatives.");
                            }
                    }
                } catch (Exception e) {
                    System.out.println("Ceci n'est pas un NOMBRE ");
                }

            } catch (InputMismatchException e) {
                System.out.println("" + choix + " est invalide veuillez rentrer un chiffre");
                sc.next();
            }

        } while (choix != 6 && essaie <= 2);

        sc.close();

    }

}
