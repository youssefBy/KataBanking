DROP TABLE IF EXISTS public.account;
DROP TABLE IF EXISTS public.transaction;
DROP TABLE IF EXISTS public.account_transactions;

create table public.account
(
    id             bigint not null
        primary key,
    account_number varchar(255)
        constraint uk_66gkcp94endmotfwb8r4ocxm9
            unique,
    balance        numeric(19, 2)
);



create table public.transaction
(
    id         bigint not null
        primary key,
    amount     numeric(19, 2),
    balance    numeric(19, 2),
    date       timestamp,
    operation  integer,
    account_id bigint
        constraint fk6g20fcr3bhr6bihgy24rq1r1b
            references public.account
);



create table public.account_transactions
(
    account_id      bigint not null
        constraint fkbrn3bd376gr5d364db3tn3rx9
            references public.account,
    transactions_id bigint not null
        constraint uk_hwp3451tqfjdhmlqw8ruc7y0k
            unique
        constraint fkbo9ocnxyf4qmchbuu7nwgfw90
            references public.transaction
);

