select
   formata_proc(pr.numproccompl) as num_processo,
   numprot as protocolo,
   dtentrreal as data_protocolo,
   c.descr as classe_processo,
   (
      select
         v.nomesint 
      from
         vara v 
      where
         v.codvara = p.codvaraproc
   )
   as unidade 
from
   peticao p,
   peticaodocumento pd,
   t_processo pr,
   classe c 
where
   p.coddoc in 
   (
      select
         da.coddoc 
      from
         documentoarquivo da 
      where
         da.nomeusuweb = 
         (
            select
               u.nome 
            from
               usuarioweb u 
            where
               upper(u.login) = ? 
               and u.indativo = 'S'
         )
   )
   and p.dtentrreal between to_date( ? , 'YYYY-MM-DD HH24:MI:SS') and to_date( ? , 'YYYY-MM-DD HH24:MI:SS') 
   and pd.coddocprinc = p.coddoc 
   and pd.coddocvinc = pr.coddoc 
   and c.codclass = pr.codclass 
union all
select
   formata_proc(pr.numproccompl) as num_processo,
   numprot as protocolo,
   dtentrreal as data_protocolo,
   c.descr as classe_processo,
   (
      select
         v.nomesint 
      from
         vara v 
      where
         v.codvara = p.codvaraproc
   )
   as unidade 
from
   peticao p,
   peticaodocumento pd,
   t_processo pr,
   classe c 
where
   p.coddoc in 
   (
      select
         dax.coddoc 
      from
         documentoanexo dax 
      where
         seqarqanexo in 
         (
            select
               aa.seqarqanexo 
            from
               arquivoanexo aa 
            where
               aa.nomeusuweb = 
               (
                  select
                     u.nome 
                  from
                     usuarioweb u 
                  where
                     upper(u.login) = ? 
                     and u.indativo = 'S'
               )
         )
   )
   and p.dtentrreal between to_date( ? , 'YYYY-MM-DD HH24:MI:SS') and to_date( ? , 'YYYY-MM-DD HH24:MI:SS') 
   and pd.coddocprinc = p.coddoc 
   and pd.coddocvinc = pr.coddoc 
   and c.codclass = pr.codclass