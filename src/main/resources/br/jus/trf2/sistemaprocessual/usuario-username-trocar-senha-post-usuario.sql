update usuario
set senha=?
where
   upper(login) = ? 
   and indativo = 'S' 