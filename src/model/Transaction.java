package model;

public class Transaction {
    private int TransactionId;
    private String label; // exemple:pret bancaire
    private double montant;
    private String type; //débit si c’est pour les sorties et crédit pour les entrées
    private String date_heure;
    private String transactionType;


    public Transaction(String label, double montant, String type, String date_heure, String transactionType) {
        this.label = label;
        this.montant = montant;
        this.type = type;
        this.date_heure = date_heure;
        this.transactionType = transactionType;


    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getMontant() {
        return montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDate_heure() {
        return date_heure;
    }

    public void setDate_heure(String date_heure) {
        this.date_heure = date_heure;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void Credit(double credit_amount, Account C2, Devise devise) {
        if (devise.getNameDevise().equals("ariary")) {
            //double a = C1.getSolde();
            double b = C2.getSolde();
            // C1.setSolde(a - credit_amount);
            C2.setSolde((float) (b + credit_amount));


        } else if (devise.getNameDevise().equals("euro")) {

            //  double a = C1.getSolde();
            double b = C2.getSolde();
            //  C1.setSolde(a - credit_amount);
            C2.setSolde((float) (b + credit_amount * 5000));
        }


    }

    public void Debit(double debit_amount, Account C2, Devise devise) {
        if (devise.getNameDevise().equals("Ariary")) {
            double b = C2.getSolde();
            C2.setSolde((float) (b - debit_amount));

        } else if (devise.getNameDevise().equals("Euro")) {
            double b = C2.getSolde();
            C2.setSolde((float) (b - debit_amount * 5000));
        }

    }

    public void transferDargent(float amount_transfer, Account C1, Account C2, Devise devise) {
        if(C1 != C2){

            if (devise.getNameDevise().equals("ariary")) {
                double a = C1.getSolde();
                double b = C2.getSolde();
                C1.setSolde((float) (a - amount_transfer));
                C2.setSolde((float) (b + amount_transfer));


            } else if (devise.getNameDevise().equals("euro")) {

                double a = C1.getSolde();
                double b = C2.getSolde();
                C1.setSolde((float) (a - amount_transfer));
                C2.setSolde((float) (b + amount_transfer * 5000));
            }
        }

    }

    @Override
    public String toString() {
        return "Transaction{" +
                ", label='" + label + '\'' +
                ", montant=" + montant +
                ", type='" + type + '\'' +
                ", date_heure='" + date_heure + '\'' +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
