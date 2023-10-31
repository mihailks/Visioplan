insert into visiodb.users (id, email, password, username)
values (1, 'misho@abv.bg',
        'f69983b003545b40f424d1604bb27ace9a9a99cab0ad1ff1bec8f918c309ed934009d2f49a078809f921562a42424b5e', 'misho'),
       (2, 'gosho@abv.bg',
        '97cfca8cda2e239d1c861251db1fdaf86fc55aedceb120fa1e8d35ef432d89ce382e75fc6f66a6681d30cc7efa5405e1', 'gosho'),
       (3, 'mmm@abv.bg',
        '23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4', 'mmm');

insert into visiodb.roles (id, role)
values  (1, 'ADMIN'),
        (2, 'USER');

insert into visiodb.users_roles (user_id, role_id)
values  (1, 1),
        (1, 2),
        (2, 2),
        (3, 2);
