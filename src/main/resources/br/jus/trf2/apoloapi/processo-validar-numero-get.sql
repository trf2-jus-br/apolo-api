select
( 
   case
      when
         (
            lc.codenvdoc = '10115' 
            and lc.indreceb = 'S'
         )
         or 
         (
            exists
            (
               select
                  1 
               from
                  movbaixaarquivamento m 
               where
                  m.coddoc = t_processo.coddoc 
                  and m.codcompl1 = 722
            )
         )
         or 
         (
            exists
            (
               select
                  1 
               from
                  migradoseproc 
               where
                  coddoc in
                  (
                     select
                        p.coddoc 
                     from
                        t_processo p 
                     where
                        (
(p.numproccompl in
                           (
                              t_processo.numproccompl
                           )
) 
                           or p.numproccompl in
                           (
                              t_processo.numproccomplant
                           )
                        )
                  )
            )
         )
      then
         'S' 
      else
         'N' 
   end
) as perdecompetencia , s.descr as orgao, lf.nome as unidade, va.nomesint as localnaunidade, t_processo.numproccompl as numero, t_processo.indsegrjustsist segredodejusticadesistema, t_processo.indsegrjustabs segredodejusticaabsoluto, t_processo.coddoc coddoc, t_processo.indproceletr eletronico, t_processo.indbaixa baixado, t_processo.dthrultmov as dataultimomovimento, t_processo.indativo as ativo , 
   (
      select
(decode(count(m.coddoc), 1, 'S', 'N')) as sentenciado 
      from
         movintimacao m 
      where
         t_processo.coddoc = m.coddoc 
         and m.codcompl1 = 3 
         and m.dthrmov = 
         (
            select
               max(mi.dthrmov) 
            from
               movintimacao mi 
            where
               mi.coddoc = m.coddoc 
               and mi.codcompl1 = 3 
               and t_processo.codsecao = mi.codsecao 
         )
         and t_processo.indbaixa = 'N' 
   )
   sentenciado 
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
      and lf.indativo = 'S') 
where
   (
      t_processo.numproccompl in (:list)
      or t_processo.numproccomplant in (:list)
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