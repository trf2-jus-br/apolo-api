select
( 
   case
      when
         lc.codenvdoc = '10115' 
         and lc.indreceb = 'S' 
      then
         1 
      else
         0 
   end
) as perdecompetencia , s.descr as orgao, lf.nome as unidade, va.nomesint as localnaunidade, t_processo.numproccompl as numero, t_processo.indsegrjustsist segredodejusticadesistema, t_processo.indsegrjustabs segredodejusticaabsoluto, t_processo.coddoc coddoc, t_processo.indproceletr eletronico, t_processo.indbaixa baixado, t_processo.dthrultmov as dataultimomovimento, t_processo.indativo as ativo 
from
   processolocal lc 
   inner join
      secao s 
      on (s.codsecao = lc.codsecao) 
   inner join
      t_processo 
      on ( lc.coddoc = t_processo.coddoc) 
   inner join
      vara va 
      on (va.codvara = 
      (
         select
            localatualprocesso(lc.codsecao, t_processo.coddoc)
         from
            dual
      )
      and va.indativo = 'S') 
   inner join
      localfisico lf 
      on (lf.codlocfis = 
      (
         select
            localatualprocesso(lc.codsecao, t_processo.coddoc)
         from
            dual
      )
      and lf.indativo = 'S'),
      (
         select
            ? as num_processo 
         from
            dual 
      )
      processo 
where
   (
      processo.num_processo = t_processo.numproccompl 
      or processo.num_processo = t_processo.numproccomplant
   )
   and lc.dthrmov = 
   (
      select
         max(pl.dthrmov) 
      from
         processolocal pl 
      where
         pl.coddoc = lc.coddoc 
         and pl.codsecao = lc.codsecao 
         and pl.indreceb = 'S' 
   )