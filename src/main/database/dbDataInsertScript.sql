//(000000-00001,{noop}a, Role_Admin )
insert into users(personal_code, password, role)
values ("MDAwMDAwLTAwMDAx","e25vb3B9YQ==","Um9sZV9BZG1pbg==");

//(000000-00002,{noop}u, Role_User)
insert into users(personal_code, password, role)
values ("MDAwMDAwLTAwMDAy","e25vb3B9dQ==","Um9sZV9Vc2Vy");
insert into bank_accounts(name,surname,personal_code, balance)
values ("Vladislav","Kulikov", "000000-00002",10000);

//(000000-00003, {noop}u, Role_User)
insert into users(personal_code, password, role)
values ("MDAwMDAwLTAwMDAz","e25vb3B9dQ==","Um9sZV9Vc2Vy");
insert into bank_accounts(name,surname,personal_code, balance)
values ("Alex","Opikov", "000000-00003",10000);