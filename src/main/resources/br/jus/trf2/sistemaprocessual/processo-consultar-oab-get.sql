select distinct
   perdecompetencia,
   orgao,
   unidade,
   localnaunidade,
   numero,
   segredodejusticadesistema,
   segredodejusticaabsoluto,
   coddoc,
   eletronico,
   baixado,
   dataultimomovimento,
   ativo,
   sentenciado,
   autor,
   reu,
   LISTAGG(oab, ', ') WITHIN GROUP (
ORDER BY
   cpfcnpj) "cpfcnpj" 
from
   (
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
) as perdecompetencia, s.descr as orgao, lf.nome as unidade, va.nomesint as localnaunidade, t_processo.numproccompl as numero, t_processo.indsegrjustsist segredodejusticadesistema, t_processo.indsegrjustabs segredodejusticaabsoluto, t_processo.coddoc coddoc, t_processo.indproceletr eletronico, t_processo.indbaixa baixado, t_processo.dthrultmov as dataultimomovimento, t_processo.indativo as ativo , 
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
         sentenciado,
         trim(AUTORXREU (s.codsecao, t_processo.coddoc, 'S', 'N', 'N', 'N')) as autor,
         trim(AUTORXREU (s.codsecao, t_processo.coddoc, 'N', 'S', 'N', 'N')) as reu,
         a1.codoab as oab 
      from
         processolocal lc 
         inner JOIN
            secao s 
            on (s.codsecao = lc.codsecao) 
         inner JOIN
            t_processo 
            on ( lc.coddoc = t_processo.coddoc) 
         inner JOIN
            vara va 
            on (va.codvara = t_processo.codvara ) 
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
         inner join
            parteprocessoadvogado ppa1 
            on (ppa1.coddoc = t_processo.coddoc) 
         inner join
            advogado a1 
            on (a1.CodAdv = ppa1.CodAdv) 
      where
         (
           a1.codoab = ?
         )
         and t_processo.indsegrjustsist='N'
   		 and t_processo.indsegrjustabs='N'
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
      union
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
                  select
                     localatualprocesso (s.codsecao, t_processo.coddoc) 
                  from
                     dual
               )
                = 10043 
               or 
               (
                  select
                     localatualprocesso (s.codsecao, t_processo.coddoc) 
                  from
                     dual
               )
                = 10044 
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
) as perdecompetencia, s.descr as orgao, lf2.descr as unidade, va.nomesint as localnaunidade, t_processo.numproccompl as numero, t_processo.indsegrjustsist segredodejusticadesistema, t_processo.indsegrjustabs segredodejusticaabsoluto, t_processo.coddoc coddoc, t_processo.indproceletr eletronico, t_processo.indbaixa baixado, t_processo.dthrultmov as dataultimomovimento, t_processo.indativo as ativo , 
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
         sentenciado,
         trim(AUTORXREU (s.codsecao, t_processo.coddoc, 'S', 'N', 'N', 'N')) as autor,
         trim(AUTORXREU (s.codsecao, t_processo.coddoc, 'N', 'S', 'N', 'N')) as reu,
         a2.codoab as oab 
      from
         processolocal lc 
         inner JOIN
            secao s 
            on (s.codsecao = lc.codsecao) 
         inner JOIN
            t_processo 
            on ( lc.coddoc = t_processo.coddoc) 
         inner JOIN
            vara va 
            on (va.codvara = t_processo.codvara ) 
         inner join
            enviodocumento lf2 
            on (lf2.codenvdoc = 
            (
               select
                  localatualprocesso(lc.codsecao, t_processo.coddoc) 
               from
                  dual 
            )
) 
         inner join
            parteprocessoadvogado ppa2 
            on (ppa2.coddoc = t_processo.coddoc) 
         inner join
            advogado a2 
            on (a2.CodAdv = ppa2.CodAdv) 
      where
         (
            a2.codoab = ?
         )
         and t_processo.indsegrjustsist='N'
   		 and t_processo.indsegrjustabs='N'
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
   )
where
   ROWNUM <= 100 
group by
   numero,
   perdecompetencia,
   orgao,
   unidade,
   localnaunidade,
   segredodejusticadesistema,
   segredodejusticaabsoluto,
   coddoc,
   eletronico,
   baixado,
   dataultimomovimento,
   ativo,
   sentenciado,
   autor,
   reu