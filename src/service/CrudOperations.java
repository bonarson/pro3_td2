package service;


import model.CategorySumResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CrudOperations {


    public int id_transaction(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        int id_transaction = 0;
        try {
            String query = String.format("select  transactionid from transaction order by  transactionid  desc  limit 1;");
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id_transaction = rs.getInt("transactionid");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return id_transaction;
    }

    public int id_transfer(Connection conn) {
        Statement statement;
        ResultSet rs = null;
        int id_transfer = 0;
        try {
            String query = String.format(" select id_transfer from transfer_history order by id_transfer  desc  limit 1;");
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                id_transfer = rs.getInt("id_transfer");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return id_transfer;
    }

    public void transfer_history(Connection conn, String table_name, int id_transaction_debiter, int id_transaction_crediter, String date_heure) {
        Statement statement;
        try {
            String query = String.format("insert into %s(id_transaction_debiter,id_transaction_crediter,date_heure) values('%s','%s','%s');", table_name, id_transaction_debiter, id_transaction_crediter, date_heure);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("transaction ok ");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String read_heure_now(Connection conn, String now) {
        Statement statement;
        ResultSet rs = null;
        String h = " ";
        try {
            String query = String.format(" select(%s)", now);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                h = rs.getString("now");
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        return h;

    }

    public List<String> read_solde_intervale(Connection conn, String table_name, int idaccount, String date_heure1, String date_heure2) {
        List<String> soldes = new ArrayList<>();
        Statement statement;
        ResultSet rs = null;
        float h = 0;
        try {
            String query = String.format(" select solde from %s where idaccount='%s' and solde_history.date_heure between  '%s' and '%s' ", table_name, idaccount, date_heure1, date_heure2);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                // System.out.println(rs.getFloat("solde"));
                h = rs.getFloat("solde");
                soldes.add(String.valueOf(h) + " Ar");

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return soldes;

    }

    //liste solde
    public float read_solde_list(Connection conn, String table_name, String date_heure) {
        Statement statement;
        ResultSet rs = null;
        float h = 0;
        try {
            String query = String.format(" select solde from %s where  solde_history.date_heure='%s' ", table_name, date_heure);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                // System.out.println(rs.getFloat("solde"));
                h = rs.getFloat("solde");


            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return h;

    }

    public String read_heure_byId(Connection conn, String table_name, int idaccount) {
        Statement statement;
        ResultSet rs = null;
        String h = " ";
        try {
            String query = String.format(" select date_heure from %s where  idaccount='%s' ", table_name, idaccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                // System.out.println(rs.getFloat("solde"));
                h = rs.getString("date_heure");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return h;
    }

    public float read_solde_byId(Connection conn, String table_name, int idaccount) {
        Statement statement;
        ResultSet rs = null;
        float q = 0;
        try {
            String query = String.format(" select solde from %s where  idaccount='%s' ", table_name, idaccount);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                // System.out.println(rs.getFloat("solde"));
                q = rs.getFloat("solde");

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return q;
    }

    //transaction
    public void insert_tran(Connection conn, String table_name, String table_name2, int idaccount,
                            String transactionType, String label, double old_solde, double new_solde,
                            double montant, String type, String date_heure, float solde, String table_name3, String new_date_heure, String old_date_heure,int  id_category) {
        Statement statement;
        try {
            String query = String.format("insert into %s(label,montant,type,date_heure,transactionType, idaccount, id_category) values('%s','%s','%s','%s','%s','%s','%s');", table_name, label, montant, type, date_heure, transactionType, idaccount, id_category);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("transaction ok  ✔ ");
            String query1 = String.format("update %s set solde='%s' where solde='%s' and  idaccount=1", table_name2, new_solde, old_solde);
            statement = conn.createStatement();
            statement.executeUpdate(query1);
            System.out.println("update solde ok  ✔ ");
            String query3 = String.format("update %s set    date_heure='%s' where    date_heure='%s'", table_name2, new_date_heure, old_date_heure);
            statement = conn.createStatement();
            statement.executeUpdate(query3);
            System.out.println("update date_heure ok  ✔ ");
            String query2 = String.format("insert into %s(solde,date_heure,idaccount) values('%s','%s','%s');", table_name3, solde, date_heure, idaccount);
            statement = conn.createStatement();
            statement.executeUpdate(query2);
            System.out.println("solde history ok  ✔ ");
            // String query5 = String.format("insert into %s(solde,date_heure,idaccount) values('%s','%s','%s');", table_name3, solde, date_heure, idaccount2);
            // statement = conn.createStatement();
            //  statement.executeUpdate(query5);
            //  System.out.println("transaction ok ");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //transfer
    public void insert_transfer(Connection conn, String table_name_account, String table_name_transa, int idaccount1, int idaccount2,
                                String transactionType1, String transactionType2, String label, double old_solde_C1, double new_solde_C1, double old_solde_C2, double new_solde_C2,
                                double montant, String type, String date_heure, float solde_c1, float solde_c2, String table_name3, String new_date_heure, String old_date_heure, String table_name_transferHistory) {
        Statement statement;
        try {
            //insert transaction C1
            String query = String.format("insert into %s(label,montant,type,date_heure,transactionType, idaccount) values('%s','%s','%s','%s','%s','%s');", table_name_transa, label, montant, type, date_heure, transactionType1, idaccount1);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("transaction ok ✔ ");
            //insert transaction C2
            String query1 = String.format("insert into %s(label,montant,type,date_heure,transactionType, idaccount) values('%s','%s','%s','%s','%s','%s');", table_name_transa, label, montant, type, date_heure, transactionType2, idaccount2);
            statement = conn.createStatement();
            statement.executeUpdate(query1);
            System.out.println("transaction ok  ✔");
            //mise a jour pour chaque solde du compte
            String query2 = String.format("update %s set solde='%s' where solde='%s'", table_name_account, new_solde_C1, old_solde_C1);
            statement = conn.createStatement();
            statement.executeUpdate(query2);
            System.out.println("SOLDE C1 a JOUR ok  ✔");

            String query6 = String.format("update %s set solde='%s' where solde='%s'", table_name_account, new_solde_C2, old_solde_C2);
            statement = conn.createStatement();
            statement.executeUpdate(query6);
            System.out.println("SOLDE C2 a JOUR ok  ✔ ");

            //MISE A JOUR HEURE DU SOLDE
            String query3 = String.format("update %s set    date_heure='%s' where    date_heure='%s'", table_name_account, new_date_heure, old_date_heure);
            statement = conn.createStatement();
            statement.executeUpdate(query3);
            System.out.println("Heure a jour ok   ✔");

            //solde history
            String query4 = String.format("insert into %s(solde,date_heure,idaccount) values('%s','%s','%s');", table_name3, solde_c1, date_heure, idaccount1);
            statement = conn.createStatement();
            statement.executeUpdate(query4);
            System.out.println("solde_history ok  ✔");

            String query7 = String.format("insert into %s(solde,date_heure,idaccount) values('%s','%s','%s');", table_name3, solde_c2, date_heure, idaccount2);
            statement = conn.createStatement();
            statement.executeUpdate(query7);
            System.out.println("solde_history ok  ✔ ");
            //tranfer_history
            String query8 = String.format("insert into %s(id_transaction_debiter, id_transaction_crediter,date_heure) values('%s','%s','%s');", table_name_transferHistory, idaccount1, idaccount2, date_heure);
            statement = conn.createStatement();
            statement.executeUpdate(query8);
            System.out.println("tranfer_history ok  ✔");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void save_id_transfer(Connection conn, int id_transaction, int id_transfer) {
        Statement statement;
        try {
            String query = String.format("update transaction set id_transfer='%s' where transactionid='%s' ;", id_transfer, id_transaction);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("merci ✔");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void historique_Transfer(Connection conn, String date_heure_debut, String date_heure_fin) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select accountname , transaction.montant,transaction.date_heure from account inner join transaction on \n" +
                    "transaction.idaccount = account. idaccount \n" +
                    "inner join transfer_history on transfer_history.id_transfer=transaction.id_transfer\n" +
                    "where transaction.date_heure BETWEEN '%s' and '%s';", date_heure_debut, date_heure_fin);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.println("COMPTE  :" );
                System.out.println(rs.getString("accountname")+" ");
                System.out.println("-------- Montant --------- : ");
                System.out.println(rs.getString("montant")+" ARIARY");
                System.out.println("----------- date_heure ------- :");
                System.out.println(rs.getString("date_heure")+" ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
