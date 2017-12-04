INSERT INTO USER (USER_NAME, EMAIL, IMAGE, PASSWORD) VALUES ('admin', 'admin@budgetkeeper.ru', NULL, 'password123');
INSERT INTO USER (USER_NAME, EMAIL, IMAGE, PASSWORD) VALUES ('User1', 'email1@mail.ru', NULL, 'password1');
INSERT INTO USER (USER_NAME, EMAIL, IMAGE, PASSWORD) VALUES ('User2', 'email2@mail.ru', NULL, 'password2');
INSERT INTO USER (USER_NAME, EMAIL, IMAGE, PASSWORD) VALUES ('User3', 'email3@mail.ru', NULL, 'password3');
INSERT INTO USER (USER_NAME, EMAIL, IMAGE, PASSWORD) VALUES ('User4', 'email4@mail.ru', NULL, 'password4');
INSERT INTO USER (USER_NAME, EMAIL, IMAGE, PASSWORD) VALUES ('User5', 'email5@mail.ru', NULL, 'password5');

INSERT INTO ACCOUNT (ACCOUNT_ID, TYPE, AMOUNT, USER_NAME) VALUES (1, 'CASH', 1000, 'User1');
INSERT INTO ACCOUNT (ACCOUNT_ID, TYPE, AMOUNT, USER_NAME) VALUES (2, 'CASH', 1000, 'User1');
INSERT INTO ACCOUNT (ACCOUNT_ID, TYPE, AMOUNT, USER_NAME) VALUES (3, 'CASH', 1000, 'User2');
INSERT INTO ACCOUNT (ACCOUNT_ID, TYPE, AMOUNT, USER_NAME) VALUES (4, 'CASH', 1000, 'User2');
INSERT INTO ACCOUNT (ACCOUNT_ID, TYPE, AMOUNT, USER_NAME) VALUES (5, 'CASH', 1000, 'User3');
INSERT INTO ACCOUNT (ACCOUNT_ID, TYPE, AMOUNT, USER_NAME) VALUES (6, 'CASH', 1000, 'User3');

INSERT INTO ACCOUNT (ACCOUNT_ID, TYPE, AMOUNT, USER_NAME) VALUES (7, 'CASH', 1200, 'admin');
INSERT INTO ACCOUNT (ACCOUNT_ID, TYPE, AMOUNT, USER_NAME) VALUES (8, 'BANK_CARD', 5000, 'admin');


INSERT INTO USER_ROLES (USER_ROLE_ID, USER_NAME, ROLE) VALUES (1, 'admin', 'ADMIN');
INSERT INTO USER_ROLES (USER_ROLE_ID, USER_NAME, ROLE) VALUES (2, 'User1', 'USER');
INSERT INTO USER_ROLES (USER_ROLE_ID, USER_NAME, ROLE) VALUES (3, 'User2', 'USER');
INSERT INTO USER_ROLES (USER_ROLE_ID, USER_NAME, ROLE) VALUES (4, 'User3', 'USER');
INSERT INTO USER_ROLES (USER_ROLE_ID, USER_NAME, ROLE) VALUES (5, 'User4', 'USER');
INSERT INTO USER_ROLES (USER_ROLE_ID, USER_NAME, ROLE) VALUES (6, 'User5', 'USER');

INSERT INTO CATEGORY (CATEGORY_ID, NAME, USER_NAME) VALUES (1, 'some cool category 1', 'User1');
INSERT INTO CATEGORY (CATEGORY_ID, NAME, USER_NAME) VALUES (2, 'some cool category 2', 'User2');
INSERT INTO CATEGORY (CATEGORY_ID, NAME, USER_NAME) VALUES (3, 'some cool category 3', 'User3');
INSERT INTO CATEGORY (CATEGORY_ID, NAME, USER_NAME) VALUES (4, 'some cool category 4', 'User4');

INSERT INTO CATEGORY (CATEGORY_ID, NAME, USER_NAME) VALUES (5, 'some cool category 5', 'admin');
INSERT INTO CATEGORY (CATEGORY_ID, NAME, USER_NAME) VALUES (6, 'some cool category 6', 'admin');
INSERT INTO CATEGORY (CATEGORY_ID, NAME, USER_NAME) VALUES (7, 'some cool category 7', 'admin');
INSERT INTO CATEGORY (CATEGORY_ID, NAME, USER_NAME) VALUES (8, 'some cool category 8', 'admin');

-- dd-MM-yyyy
INSERT INTO FUNDS_TRANSACTION (TRANSACTION_ID, ACCOUNT_ID, CATEGORY_ID, DATE, TRANSACTION_TYPE, AMOUNT)
VALUES (1, 1, 1, TIMESTAMP '2012-12-23', 'COSTS', 100);

INSERT INTO FUNDS_TRANSACTION (TRANSACTION_ID, ACCOUNT_ID, CATEGORY_ID, DATE, TRANSACTION_TYPE, AMOUNT)
VALUES (2, 2, 2, TIMESTAMP '2012-12-23', 'COSTS', 101);

INSERT INTO FUNDS_TRANSACTION (TRANSACTION_ID, ACCOUNT_ID, CATEGORY_ID, DATE, TRANSACTION_TYPE, AMOUNT)
VALUES (3, 3, 3, TIMESTAMP '2012-12-23', 'COSTS', 102);

INSERT INTO FUNDS_TRANSACTION (TRANSACTION_ID, ACCOUNT_ID, CATEGORY_ID, DATE, TRANSACTION_TYPE, AMOUNT)
VALUES (212, 4, 4, TIMESTAMP '2012-12-23', 'COSTS', 1011);
