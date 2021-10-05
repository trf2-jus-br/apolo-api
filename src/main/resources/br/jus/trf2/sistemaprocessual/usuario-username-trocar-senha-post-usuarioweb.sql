update usuarioweb
set senha=?
where
   upper(login) = ?
   and indativo = 'S'
   and dtrecebtermo is not null