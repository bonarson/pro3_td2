import dataBaseConfig.connect_to_db;
import model.Account;
import model.Devise;
import model.Transaction;
import service.CrudOperations;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.time.LocalTime.now;

public class Main {

    public static void main(String[] args) {

        connect_to_db db = new connect_to_db();
        Connection conn = db.conect_db("wallet", "postgres", "motdepasse");
        CrudOperations tran = new CrudOperations();
        //scanner pour demander a l'utilisateur;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ╰(*°▽°*)╯ ╰(*°▽°*)╯ ╰(*°▽°*)╯ 💲💲  WALLET  💲💲 ╰(*°▽°*)╯ ╰(*°▽°*)╯ ╰(*°▽°*)╯ ==");
        System.out.println(" ");
        System.out.println("1------- 🟠  TRANSACTION");
        System.out.println("2------- 🟠  CONSULTAION DU SOLDE ACTUEL");
        System.out.println("3------- 🟠  CONSULTAION DU SOLDE PAR DATE ET HEURE");
        System.out.println("4------- 🟠  CONSULTATION DU SOLDE EN INTERVAL DU TEMPS");
        System.out.println("5------- 🟠  TRANSFER D'ARGENT");
        System.out.println("6------- 🟠  HISTORIQUE DES TRANSFER ");
        System.out.print("votre choix ✏ : ");
        int number_choise = scanner.nextInt();
        if (number_choise == 1) {
            System.out.println("---------------TRANSACTION");
            System.out.println(" ");
            System.out.println("=============== DEVISE ============== ");
            System.out.print("devise_name(Ariary/Euro) : ");
            String devise_name = scanner.next().toLowerCase();
            System.out.print("code(EUR/AR) : ");
            String code = scanner.next();

            Devise devise = new Devise(1, devise_name, code);
            if (devise.getNameDevise().equals("ariary")) {
                System.out.println("----------------TRANSACTION TYPE :");
                System.out.println("1-salaire");
                System.out.println("2-restauran");
                System.out.println("3-pre ");
                System.out.print("votre choix : ");
                int num_choise = scanner.nextInt();
                if (num_choise == 1) {
                    System.out.println("label : ");
                    String label = scanner.next();
                    System.out.print("montant : ");
                    double montant = scanner.nextDouble();
                    System.out.println("type : ");
                    String type = scanner.next();
                    String date_heure = "now";
                    float solde = tran.read_solde_byId(conn, "account", 1);
                    String h = tran.read_heure_byId(conn, "account", 1);
                    String h_act = tran.read_heure_now(conn, "now()");
                    Transaction transaction = new Transaction(label, montant, type, h, "Credit");
                    Account C1 = new Account(1, "C1", "epargne", transaction, solde, h_act, devise, type);
                    tran.insert_tran(conn, "transaction", "account", C1.getIdAccount(), transaction.getTransactionType(), label, C1.getSolde(), C1.getSolde() + montant, montant, type, h_act, solde, "solde_history", h_act, h,1);
                    transaction.Credit(montant, C1, devise);
                    System.out.println(C1);

                } else if (num_choise == 2) {
                    System.out.println("label : ");
                    String label = scanner.next();
                    System.out.print("montant : ");
                    double montant = scanner.nextDouble();
                    System.out.println("type : ");
                    String type = scanner.next();
                    String h = tran.read_heure_byId(conn, "account", 1);
                    String h_act = tran.read_heure_now(conn, "now()");
                    Transaction transaction = new Transaction(label, montant, type, h, "Debit");
                    float solde = tran.read_solde_byId(conn, "account", 1);
                    Account C1 = new Account(1, "C1", "epargne", transaction, solde, h_act, devise, type);
                    tran.insert_tran(conn, "transaction", "account", C1.getIdAccount(), transaction.getTransactionType(), label, C1.getSolde(), C1.getSolde() - montant, montant, type, h_act, solde, "solde_history", h_act, h,2);
                    transaction.Debit(montant, C1, devise);
                    float d = (float) (C1.getSolde() - montant);
                    C1.setSolde(d);
                    System.out.println(C1);

                } else if (num_choise == 3) {
                    System.out.println("----------------TRANSACTION TYPE :");
                    System.out.println("1-Credite");
                    System.out.println("2-Debiter");
                    System.out.print("votre choix : ");
                    num_choise = scanner.nextInt();
                    if (num_choise == 1) {
                        System.out.println("label : ");
                        String label = scanner.next();
                        System.out.print("montant : ");
                        double montant = scanner.nextDouble();
                        System.out.println("type : ");
                        String type = scanner.next();
                        String date_heure = "now";
                        float solde = tran.read_solde_byId(conn, "account", 1);
                        String h = tran.read_heure_byId(conn, "account", 1);
                        String h_act = tran.read_heure_now(conn, "now()");
                        Transaction transaction = new Transaction(label, montant, type, h, "Credit");
                        Account C1 = new Account(1, "C1", "epargne", transaction, solde, h_act, devise, type);
                        tran.insert_tran(conn, "transaction", "account", C1.getIdAccount(), transaction.getTransactionType(), label, C1.getSolde(), C1.getSolde() + montant, montant, type, h_act, solde, "solde_history", h_act, h,3);
                        transaction.Credit(montant, C1, devise);
                        System.out.println(C1);

                    } else if (num_choise == 2) {
                        System.out.println("label : ");
                        String label = scanner.next();
                        System.out.print("montant : ");
                        double montant = scanner.nextDouble();
                        System.out.println("type : ");
                        String type = scanner.next();
                        String h = tran.read_heure_byId(conn, "account", 1);
                        String h_act = tran.read_heure_now(conn, "now()");
                        Transaction transaction = new Transaction(label, montant, type, h, "Debit");
                        float solde = tran.read_solde_byId(conn, "account", 1);
                        Account C1 = new Account(1, "C1", "epargne", transaction, solde, h_act, devise, type);
                        tran.insert_tran(conn, "transaction", "account", C1.getIdAccount(), transaction.getTransactionType(), label, C1.getSolde(), C1.getSolde() - montant, montant, type, h_act, solde, "solde_history", h_act, h,3);
                        transaction.Debit(montant, C1, devise);
                        float d = (float) (C1.getSolde() - montant);
                        C1.setSolde(d);
                        System.out.println(C1);
                    }
                }

            } else if (devise.getNameDevise().equals("euro")) {
                System.out.println("----------------TRANSACTION TYPE :");
                System.out.println("1-Salaire");
                System.out.println("2-Restaurant");
                System.out.println("3-Pret");
                System.out.print("votre choix : ");
                int num_choise = scanner.nextInt();
                if (num_choise == 1) {
                    System.out.println("label : ");
                    String label = scanner.next();
                    System.out.print("montant : ");
                    double montant = scanner.nextDouble();
                    System.out.println("type : ");
                    String type = scanner.next();
                    String date_heure = "now";
                    float solde = tran.read_solde_byId(conn, "account", 1);
                    String h = tran.read_heure_byId(conn, "account", 1);
                    String h_act = tran.read_heure_now(conn, "now()");
                    Transaction transaction = new Transaction(label, montant, type, h, "Credit");
                    Account C1 = new Account(1, "C1", "epargne", transaction, solde, h_act, devise, type);
                    tran.insert_tran(conn, "transaction", "account", C1.getIdAccount(), transaction.getTransactionType(), label, C1.getSolde(), C1.getSolde() + (montant * 5000), montant, type, h_act, solde, "solde_history", h_act, h,1);
                    transaction.Credit(montant, C1, devise);
                    System.out.println(C1);

                } else if (num_choise == 2) {
                    System.out.println("label : ");
                    String label = scanner.next();
                    System.out.print("montant : ");
                    double montant = scanner.nextDouble();
                    System.out.println("type : ");
                    String type = scanner.next();
                    String h = tran.read_heure_byId(conn, "account", 1);
                    String h_act = tran.read_heure_now(conn, "now()");
                    Transaction transaction = new Transaction(label, montant, type, h, "Debit");
                    float solde = tran.read_solde_byId(conn, "account", 1);
                    Account C1 = new Account(1, "C1", "epargne", transaction, solde, h_act, devise, type);
                    tran.insert_tran(conn, "transaction", "account", C1.getIdAccount(), transaction.getTransactionType(), label, C1.getSolde(), C1.getSolde() - (montant * 5000), montant, type, h_act, solde, "solde_history", h_act, h,2);
                    transaction.Debit(montant, C1, devise);
                    float d = (float) (C1.getSolde() - montant);
                    C1.setSolde(d);
                    System.out.println(C1);

                } else if (num_choise == 3) {
                    System.out.println("----------------TRANSACTION TYPE :");
                    System.out.println("1-Credite");
                    System.out.println("2-Debiter");
                    System.out.print("votre choix : ");
                    num_choise = scanner.nextInt();
                    if (num_choise == 1) {
                        System.out.println("label : ");
                        String label = scanner.next();
                        System.out.print("montant : ");
                        double montant = scanner.nextDouble();
                        System.out.println("type : ");
                        String type = scanner.next();
                        String date_heure = "now";
                        float solde = tran.read_solde_byId(conn, "account", 1);
                        String h = tran.read_heure_byId(conn, "account", 1);
                        String h_act = tran.read_heure_now(conn, "now()");
                        Transaction transaction = new Transaction(label, montant, type, h, "Credit");
                        Account C1 = new Account(1, "C1", "epargne", transaction, solde, h_act, devise, type);
                        tran.insert_tran(conn, "transaction", "account", C1.getIdAccount(), transaction.getTransactionType(), label, C1.getSolde(), C1.getSolde() + (montant * 5000), montant, type, h_act, solde, "solde_history", h_act, h,3);
                        transaction.Credit(montant, C1, devise);
                        System.out.println(C1);

                    } else if (num_choise == 2) {
                        System.out.println("label : ");
                        String label = scanner.next();
                        System.out.print("montant : ");
                        double montant = scanner.nextDouble();
                        System.out.println("type : ");
                        String type = scanner.next();
                        String h = tran.read_heure_byId(conn, "account", 1);
                        String h_act = tran.read_heure_now(conn, "now()");
                        Transaction transaction = new Transaction(label, montant, type, h, "Debit");
                        float solde = tran.read_solde_byId(conn, "account", 1);
                        Account C1 = new Account(1, "C1", "epargne", transaction, solde, h_act, devise, type);
                        tran.insert_tran(conn, "transaction", "account", C1.getIdAccount(), transaction.getTransactionType(), label, C1.getSolde(), C1.getSolde() - (montant * 5000), montant, type, h_act, solde, "solde_history", h_act, h,3);
                        transaction.Debit(montant, C1, devise);
                        float d = (float) (C1.getSolde() - montant);
                        C1.setSolde(d);
                        System.out.println(C1);

                    }
                }
            }


        } else if (number_choise == 2) {

            float c1 = tran.read_solde_byId(conn, "account", 1);
            float c2 = tran.read_solde_byId(conn, "account", 2);
            String h = tran.read_heure_now(conn, "now()");

            System.out.println("entre votre compte : ");
            String compte = scanner.next();
            if (compte.equals("C1")) {
                // int id_transfer = tran.id_transfer(conn);
                // System.out.println("id transfer = "+id_transfer);
                // int id_transaction =tran.id_transaction(conn);
                //System.out.println(id_transaction);
                System.out.print("Votre solde actuel : " + c1 + " Ariary date : " + h);

            } else if (compte.equals("C2")) {
                System.out.print("Votre solde actuel : " + c2 + " Ariary date : " + h);
            }


        } else if (number_choise == 3) {
            System.out.print(" date : ");
            List<String> h = Collections.singletonList(scanner.next());
            System.out.print("heure : ");
            List<String> h1 = Collections.singletonList(scanner.next());
            float solde = tran.read_solde_list(conn, "solde_history", h.get(0) + " " + h1.get(0));
            tran.read_heure_now(conn, "now()");
            System.out.println("votre solde du " + h.get(0) + " " + h1.get(0) + " est " + solde + " Ar");
        } else if (number_choise == 4) {
            System.out.println("etrre le premier date_heure :");
            System.out.print(" date : ");
            List<String> h = Collections.singletonList(scanner.next());
            System.out.print("heure : ");
            List<String> h1 = Collections.singletonList(scanner.next());
            System.out.println("etrre le deuxiem date_heure :");
            System.out.print(" date : ");
            List<String> h2 = Collections.singletonList(scanner.next());
            System.out.print("heure : ");
            List<String> h3 = Collections.singletonList(scanner.next());
            System.out.println(tran.read_solde_intervale(conn, "solde_history", 1, h.get(0) + " " + h1.get(0), h2.get(0) + " " + h3.get(0)));

        } else if (number_choise == 5) {
            //"5-TRANSFER D'ARGENT";
            System.out.println(" ");
            System.out.println("DEVISE");
            System.out.print("devise_name(Ariary/Euro) : ");
            String devise_name = scanner.next().toLowerCase();
            System.out.print("code(EUR/AR) : ");
            String code = scanner.next();
            Devise devise = new Devise(1, devise_name, code);
            System.out.println("label : ");
            String label = scanner.next();
            System.out.print("montant : ");
            double montant = scanner.nextDouble();
            System.out.println("type : ");
            String type = scanner.next();
            String date_heure = "now";
            int id_transfer = tran.id_transfer(conn) + 1;
            //System.out.println("id transfer = "+id_transfer);
            int id_transaction = tran.id_transaction(conn);
            //System.out.println(id_transaction);
            if (devise.getNameDevise().equals("ariary")) {
                float solde1 = tran.read_solde_byId(conn, "account", 1);
                float solde2 = tran.read_solde_byId(conn, "account", 2);
                //dernier heure
                String h = tran.read_heure_byId(conn, "account", 1);
                //heure actuel
                String h_act = tran.read_heure_now(conn, "now()");
                Transaction transaction = new Transaction(label, montant, type, h_act, "Debit");
                Transaction transaction1 = new Transaction(label, montant, type, h_act, "credit");
                Account C1 = new Account(1, "C1", "epargne", transaction, solde1, h_act, devise, type);
                Account C2 = new Account(2, "C2", "epargne", transaction1, solde2, h_act, devise, type);
                tran.insert_transfer(conn, "account", "transaction", C1.getIdAccount(), C2.getIdAccount(), "Debit", "Credit", label, solde1, solde1 - montant, solde2, solde2 + montant, montant, type, h_act, solde1, solde2, "solde_history", h_act, h, "transfer_history");
                transaction.Debit(montant, C1, devise);
                transaction.transferDargent((float) montant, C1, C2, devise);
                System.out.print("saisir Ok :  ");
                String ok = scanner.next();
                if (ok.equals("ok")) {
                    tran.save_id_transfer(conn, id_transaction, id_transfer);
                    tran.save_id_transfer(conn, id_transaction - 1, id_transfer);
                }
                System.out.println(C1);
                System.out.println(C2);

            } else if (devise.getNameDevise().equals("euro")) {
                float solde1 = tran.read_solde_byId(conn, "account", 1);
                float solde2 = tran.read_solde_byId(conn, "account", 2);
                //dernier heure
                String h = tran.read_heure_byId(conn, "account", 1);
                //heure actuel
                String h_act = tran.read_heure_now(conn, "now()");
                Transaction transaction = new Transaction(label, montant, type, h_act, "Debit");
                Transaction transaction1 = new Transaction(label, montant, type, h_act, "credit");
                Account C1 = new Account(1, "C1", "epargne", transaction, solde1, h_act, devise, type);
                Account C2 = new Account(2, "C2", "epargne", transaction1, solde2, h_act, devise, type);
                tran.insert_transfer(conn, "account", "transaction", C1.getIdAccount(), C2.getIdAccount(), "Debit", "Credit", label, solde1, solde1 - (montant * 5000), solde2, solde2 + (montant * 5000), montant, type, h_act, solde1, solde2, "solde_history", h_act, h, "transfer_history");
                transaction.Debit(montant, C1, devise);
                transaction.transferDargent((float) montant, C1, C2, devise);

                System.out.print("saisir Ok :  ");
                String ok = scanner.next();
                if (ok.equals("ok")) {
                    tran.save_id_transfer(conn, id_transaction, id_transfer);
                }
                System.out.println(C1);
                System.out.println(C2);
            }


        } else if (number_choise == 6) {
            System.out.println("date_debut : ");
            String date_debu = scanner.next();
            System.out.println("Heure_debut : ");
            String h_debut = scanner.next();

            System.out.println("date_fin : ");
            String date_fin = scanner.next();
            System.out.println("heure_fin : ");
            String h_fin = scanner.next();

            tran.historique_Transfer(conn, date_debu + " " + h_debut, date_fin + " " + h_fin);
        }


    }
}


