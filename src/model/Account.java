package model;

public class Account {
    private int IdAccount;
    //caracteriser par le montant et la date de dernier maj
    private String accountName;
    private String accountType;  // courant ou épargne
    private Transaction TransactionsListe;
    private float solde;
    private String date_heure;
    private Devise devise;//Euro ou Ariary
    private String Type;//Banque, Espèce ou Mobile Money

    //constructor
    public Account(int idAccount, String accountName, String accountType, Transaction transactionsListe, float solde, String date_heure, Devise devise, String type) {
        IdAccount = idAccount;
        this.accountName = accountName;
        this.accountType = accountType;
        TransactionsListe = transactionsListe;
        this.solde = solde;
        this.date_heure = date_heure;
        this.devise = devise;
        Type = type;
    }
    //getter and setter


    public int getIdAccount() {
        return IdAccount;
    }

    public void setIdAccount(int idAccount) {
        IdAccount = idAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Transaction getTransactionsListe() {
        return TransactionsListe;
    }

    public void setTransactionsListe(Transaction transactionsListe) {
        TransactionsListe = transactionsListe;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public String getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(String date_heure) {
        this.date_heure = date_heure;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "IdAccount=" + IdAccount +
                ", accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", TransactionsListe=" + TransactionsListe +
                ", solde=" + solde +
                ", date_heure='" + date_heure + '\'' +
                ", devise=" + devise +
                ", Type='" + Type + '\'' +
                '}';
    }
}
