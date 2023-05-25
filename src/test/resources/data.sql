INSERT INTO public.account (id, account_number, balance) VALUES (1, '1', 80.00);
INSERT INTO public.account (id, account_number, balance) VALUES (5, '2', 400.00);
INSERT INTO public.account (id, account_number, balance) VALUES (9, '3', 450.00);
INSERT INTO public.account (id, account_number, balance) VALUES (12, '4', 50.00);

INSERT INTO public.transaction (id, amount, balance, date, operation, account_id) VALUES (3, 100.00, 100.00, '2023-05-23 11:15:40.846583', 0, 1);
INSERT INTO public.transaction (id, amount, balance, date, operation, account_id) VALUES (4, 20.00, 80.00, '2023-05-23 11:17:11.624596', 1, 1);
INSERT INTO public.transaction (id, amount, balance, date, operation, account_id) VALUES (6, 50.00, 450.00, '2023-05-23 17:29:58.763639', 1, 5);
INSERT INTO public.transaction (id, amount, balance, date, operation, account_id) VALUES (7, 150.00, 300.00, '2023-05-23 17:30:25.452561', 1, 5);
INSERT INTO public.transaction (id, amount, balance, date, operation, account_id) VALUES (8, 100.00, 400.00, '2023-05-23 17:30:34.288571', 0, 5);
INSERT INTO public.transaction (id, amount, balance, date, operation, account_id) VALUES (10, 100.00, 600.00, '2023-05-23 17:49:42.154539', 0, 9);
INSERT INTO public.transaction (id, amount, balance, date, operation, account_id) VALUES (11, 150.00, 450.00, '2023-05-23 17:50:08.465383', 1, 9);
INSERT INTO public.transaction (id, amount, balance, date, operation, account_id) VALUES (13, 100.00, 100.00, '2023-05-23 20:13:17.853312', 0, 12);
INSERT INTO public.transaction (id, amount, balance, date, operation, account_id) VALUES (14, 50.00, 50.00, '2023-05-23 20:14:07.073011', 1, 12);

INSERT INTO public.account_transactions (account_id, transactions_id) VALUES (1, 3);
INSERT INTO public.account_transactions (account_id, transactions_id) VALUES (1, 4);
INSERT INTO public.account_transactions (account_id, transactions_id) VALUES (5, 6);
INSERT INTO public.account_transactions (account_id, transactions_id) VALUES (5, 7);
INSERT INTO public.account_transactions (account_id, transactions_id) VALUES (5, 8);
INSERT INTO public.account_transactions (account_id, transactions_id) VALUES (9, 10);
INSERT INTO public.account_transactions (account_id, transactions_id) VALUES (9, 11);
INSERT INTO public.account_transactions (account_id, transactions_id) VALUES (12, 13);
INSERT INTO public.account_transactions (account_id, transactions_id) VALUES (12, 14);

