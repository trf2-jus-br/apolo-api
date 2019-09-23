select
   1 usuinterno,
   0 usuexterno,
   u.codusu codusu,
   null codentidade,
   null entidade,
   ul.codlocfis codunidade,
   null unidade,
   u.nome nome,
   u.numcpf cpf,
   null email,
   null perfil 
from
   usuario u 
   left outer join
      funcao f 
      on u.codfunc = f.codfunc 
   left outer join
      usuariolotacao ul 
      on u.codusu = ul.codusu 
      and ul.codtiplot = 1 
where
   upper(u.login) = ? 
   and u.senha = ?
   and u.indativo = 'S' 
union
select
   0 usuinterno,
   1 usuexterno,
   uws.codusuweb codusu,
   null codentidade,
   null entidade,
   null codunidade,
   null unidade,
   uws.nome nome,
   uws.numcpf cpf,
   uws.email,
   tp.descr perfil 
from
   usuarioweb uws 
   left outer join
      tipousuarioweb tp 
      on uws.codtipusuweb = tp.codtipusuweb 
where
   upper(uws.login) = ?
   and uws.senha = ?
   and uws.indativo = 'S'