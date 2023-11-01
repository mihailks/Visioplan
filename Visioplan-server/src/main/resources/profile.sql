SELECT e.first_name, e.last_name, u.username, u.email, c.name
from visiodb.employees as e
         join visiodb.users u on u.id = e.user_id
         join visiodb.companies c on e.company_id = c.id
where company_id = 1;
