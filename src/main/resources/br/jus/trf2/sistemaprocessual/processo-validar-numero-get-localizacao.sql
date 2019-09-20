--Localização pelo campo Observação da fase 111
select
   m.obs as local,
   to_char(m.dthrmov, 'DD/MM/YYYY hh24:mi', 'nls_date_language=''brazilian portuguese''') as data_hora 
from
   movimento m,
   t_processo p 
where
   m.codfase = 111 
   and m.coddoc = p.coddoc 
   and p.codsecao = m.codsecao 
   and m.dthrmov = 
   (
      select
         max(mo.dthrmov) 
      from
         movimento mo 
      where
         mo.coddoc = m.coddoc 
         and mo.codfase = 111
   )
   and p.indbaixa = 'N' 
   and p.numproccompl = ? 
   and p.indproceletr = 'N' 
union all
--Localização pelo Código da Atividade
select
   ai.descr as local,
   to_char(mv.dthrmov, 'DD/MM/YYYY hh24:mi', 'nls_date_language = ''brazilian portuguese''') as data_hora 
from
   atividadeinterna ai,
   movinterna mv,
   t_processo ps 
where
   ps.coddoc = mv.coddoc 
   and ai.codativint = mv.codativint 
   and ps.indbaixa = 'N' 
   and ps.coddoc = 
   (
      select
         coddoc 
      from
         t_processo p 
      where
         p.numproccompl = ?
   )
   and mv.dthrmov = 
   (
      select
         max(mm.dthrmov) 
      from
         movinterna mm 
      where
         mm.coddoc = mv.coddoc 
         and mm.codfase = 111
   )
   and ai.indativo = 'S' 
   and ps.indproceletr = 'N' 
   and ps.codvara = mv.codlocfis 
   and ai.codlocfis = ps.codvara 
union all
--Local Virtual do Processo Eletrônico
select
   lv.descr as local,
   to_char(lvd.dthrentr, 'DD/MM/YYYY hh24:mi', 'nls_date_language=''brazilian portuguese''') as data_hora 
from
   localvirtualdocumento lvd,
   t_processo p,
   localvirtual lv 
where
   p.coddoc = lvd.coddoc 
   and p.codsecao = lvd.codsecao 
   and p.numproccompl = ?
   and lv.codsecao = p.codsecao 
   and lv.codlocfis = lvd.codlocfis 
   and lv.codlocalvirt = lvd.codlocalvirt 
   and p.indbaixa = 'N'