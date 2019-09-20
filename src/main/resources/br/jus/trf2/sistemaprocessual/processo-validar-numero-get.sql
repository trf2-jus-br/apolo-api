select
   s.descr as orgao,
   (
      select
         lf.nome 
      from
         localfisico lf 
      where
         lf.codlocfis = 
         (
            select
               localatualprocesso(pl.codsecao, 
               (
                  select
                     coddoc 
                  from
                     t_processo p 
                  where
                     p.numproccompl = :numero 
                     or p.numproccomplant = :numero
               )
) 
            from
               dual
         )
         and lf.indativo = 'S'
   )
   as unidade,
   (
      select
         va.nomesint 
      from
         vara va 
      where
         va.indativo = 'S' 
         and va.codvara = 
         (
            select
               localatualprocesso(pl.codsecao, 
               (
                  select
                     coddoc 
                  from
                     t_processo p 
                  where
                     p.numproccompl = :numero 
                     or p.numproccomplant = :numero
               )
) 
            from
               dual
         )
   )
   as localnaunidade,
   (
      select
         numproccompl 
      from
         t_processo p 
      where
         p.numproccompl = :numero 
         or p.numproccomplant = :numero
   )
   as numero,
   (
      select
         indsegrjustsist 
         or indsegrjustabs 
      from
         t_processo p 
      where
         p.numproccompl = :numero 
         or p.numproccomplant = :numero
   )
   as segredodejustica,
   (
      select
         indsegrjustsist 
      from
         t_processo p 
      where
         p.numproccompl = :numero 
         or p.numproccomplant = :numero
   )
   as segredodejusticadesistema,
   (
      select
         indsegrjustabs 
      from
         t_processo p 
      where
         p.numproccompl = :numero 
         or p.numproccomplant = :numero
   )
   as segredodejusticaabsoluto,
   (
      select
         coddoc 
      from
         t_processo p 
      where
         p.numproccompl = :numero 
         or p.numproccomplant = :numero
   )
   as coddoc,
   (
      select
         indproceletr 
      from
         t_processo p 
      where
         p.numproccompl = :numero 
         or p.numproccomplant = :numero
   )
   = 'S' as eletronico,
   (
      select
         indbaixa 
      from
         t_processo p 
      where
         p.numproccompl = :numero 
         or p.numproccomplant = :numero
   )
   = 'S' as baixado,
   p.dthrultmov as dataultimomovimento,
   (
      select
         indativo 
      from
         t_processo p 
      where
         p.numproccompl = :numero 
         or p.numproccomplant = :numero
   )
   = 'S' as ativo 
from
   processolocal pl,
   secao s,
   t_processo p 
where
   pl.coddoc = 
   (
      select
         coddoc 
      from
         t_processo p 
      where
         p.numproccompl = :numero 
         or p.numproccomplant = :numero
   )
   and pl.indreceb = 'S' 
   and pl.dthrmov = 
   (
      select
         max(pr.dthrmov) 
      from
         processolocal pr 
      where
         pr.coddoc = pl.coddoc 
         and pr.codsecao = pl.codsecao 
         and pr.indreceb = 'S'
   )
   and s.codsecao = pl.codsecao 
   and p.coddoc = pl.coddoc