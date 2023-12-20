create database wallet_v2;
\c wallet_2 
--
-- ALTER SEQUENCE transaction_transactionid_seq RESTART 1;
-- Alter sequence solde_history_id_seq restart 1;
-- Alter sequence transfer_history_id_transfer_seq restart 1;

 --devise
 create table devise(
    deviseId int primary key,
    name varchar(50),
    code varchar(30)
 );
 create table account(
    IdAccount int primary key,
    AccountName varchar(20),
    accountType varchar(20),
    solde float ,
    date_heure timestamp,
    Type varchar(50)
);

 create table transaction(
     TransactionId serial primary key,
     label varchar(50),montant float ,
     type varchar(50),
     date_heure timestamp,
     transactionType varchar(40),
     idaccount int references account(idaccount)
    
 );


--create solde history
create table solde_history(
    id serial primary key,
    solde float ,
    date_heure timestamp,
    idaccount int references account(idaccount)
);

create table transfer_history(
    id_transfer serial primary key,
    id_transaction_debiter int references account(idaccount),
    id_transaction_crediter int references account(idaccount),
    date_heure timestamp

);


--INSERT ACCOUNT
insert into account values(1,'C1','epargne',0,'now','Mvola');
insert into account values(2,'C2','epargne',10000,'now','Mvola');
--INSERT DEVISE
insert into devise values(1,'Ariary','AR');
insert into devise values(2,'Euro','EUR');

---------- historique du transfer

select transaction.montant from transfer_history inner join transaction
on transaction.idaccount = transfer_history.idaccount;

-- pour avoir l'id_transfer 
 select id_transfer from transfer_history order by id_transfer  desc  limit 1;
 --id_transaction
  select  transactionid from transaction order by  transactionid  desc  limit 1;

 --ajouter la colone id_transfer dans transaction
alter table transaction
add column  id_transfer int references transfer_history( id_transfer);


--une fonction pour pouvoir obtenir l’historique des transferts dans une intervalle de date
select accountname , transaction.montant,transaction.date_heure from account inner join transaction on 
transaction.idaccount = account. idaccount 
inner join transfer_history on transfer_history.id_transfer=transaction.id_transfer
where transaction.date_heure BETWEEN '  2023-12-20 17:41:52.733141' and '2023-12-20 17:46:52.733141';

--TD2
-- Ajout de la table Categories
create table categories(
    id_category serial primary key,
    category_name varchar(50) unique
);
insert into categories values(1,'salaire');
insert into categories values(2,'restaurant');
insert into categories values(3,'pre');



-- Ajout de la colonne id_category à la table transaction
alter table transaction
add column id_category int references categories(id_category);



--alter table 
alter table transaction add column  idaccount int references account(idaccount);

--update
update transaction
set idaccount= 1
where transactionid=1;

update transaction
set idaccount= 2
where transactionid=2;


--FONCTION SQL

--2.
--a) Créer une fonction SQL qui prend trois paramètres (ID du compte bancaire, Date-heure
--de début et date-heure de fin) qui retourne la somme des entrées et de sorties d’argent
--entre la plage de date donnée.


-- COALESCE nofa tsisy transaction hita da gn resulta  sera 0
--END; :la fin du corps de la fonction.
--$$ : fin du bloc de code de la fonction.
--LANGUAGE plpgsql; : un langage de procédure stocké pour PostgreSQL.
--$$ :la fin du bloc de code de la fonction.

CREATE OR REPLACE FUNCTION calculate_balance_changes(
    p_account_id INT,
    p_start_date TIMESTAMP,
    p_end_date TIMESTAMP
) RETURNS FLOAT AS
$$
DECLARE
    total_balance_changes FLOAT; --stockeko ato gn resultat sql /variable locale
BEGIN
    SELECT COALESCE(SUM(CASE WHEN transaction.type = 'Credit' THEN transaction.montant ELSE -transaction.montant END), 0)
    INTO total_balance_changes
    FROM transaction
    WHERE transaction.idaccount = p_account_id
        AND transaction.date_heure BETWEEN p_start_date AND p_end_date;

    RETURN total_balance_changes;
END;
$$
LANGUAGE plpgsql;
SELECT calculate_balance_changes(1, ' 2023-12-20 22:24:31.572407', '2023-12-31 23:59:59') AS total_changes;



--2-b
CREATE OR REPLACE FUNCTION calculate_category_sums_between_dates(
    p_account_id INT,
    p_start_date TIMESTAMP,
    p_end_date TIMESTAMP
) RETURNS TABLE (category_name VARCHAR, montant_category_1 FLOAT, montant_category_2 FLOAT, montant_category_3 FLOAT) AS
$$
BEGIN
    RETURN QUERY
    SELECT
        c.category_name,
        COALESCE(SUM(CASE WHEN t.id_category = 1 THEN t.montant ELSE 0 END), 0) AS montant_category_1,
        COALESCE(SUM(CASE WHEN t.id_category = 2 THEN t.montant ELSE 0 END), 0) AS montant_category_2,
        COALESCE(SUM(CASE WHEN t.id_category = 3 THEN t.montant ELSE 0 END), 0) AS montant_category_3
    FROM
        categories c
    LEFT JOIN
        transaction t ON c.id_category = t.id_category AND t.idaccount = p_account_id AND t.date_heure BETWEEN p_start_date AND p_end_date
    GROUP BY
        c.category_name;
END;
$$
LANGUAGE plpgsql;


SELECT * FROM calculate_category_sums_between_dates(1, '2023-12-20 00:00:00', '2023-12-21 23:59:59');