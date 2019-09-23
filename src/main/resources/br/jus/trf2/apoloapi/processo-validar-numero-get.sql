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
                     p.numproccompl = ? 
                     or p.numproccomplant = ? 
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
                     p.numproccompl = ? 
                     or p.numproccomplant = ? 
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
         p.numproccompl = ? 
         or p.numproccomplant = ? 
   )
   as numero,
   (
      select
         indsegrjustsist 
      from
         t_processo p 
      where
         p.numproccompl = ? 
         or p.numproccomplant = ? 
   )
   as segredodejusticadesistema,
   (
      select
         indsegrjustabs 
      from
         t_processo p 
      where
         p.numproccompl = ? 
         or p.numproccomplant = ? 
   )
   as segredodejusticaabsoluto,
   (
      select
         coddoc 
      from
         t_processo p 
      where
         p.numproccompl = ? 
         or p.numproccomplant = ? 
   )
   as coddoc,
   (
      select
         indproceletr 
      from
         t_processo p 
      where
         p.numproccompl = ? 
         or p.numproccomplant = ? 
   )
   as eletronico,
   (
      select
         indbaixa 
      from
         t_processo p 
      where
         p.numproccompl = ? 
         or p.numproccomplant = ? 
   )
   as baixado,
   p.dthrultmov as dataultimomovimento,
   (
      select
         indativo 
      from
         t_processo p 
      where
         p.numproccompl = ? 
         or p.numproccomplant = ? 
   )
   as ativo 
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
         p.numproccompl = ? 
         or p.numproccomplant = ? 
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