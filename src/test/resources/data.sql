insert into appuser (id)
values (1);

insert into appuser_authuser (id, app_user_id, auth_user_name)
values (1, 1, "user");

insert into contract (id, creator_role, app_user_id, counter_party_id, text)
values (1, 0, 1, 1, "11111");