select
(decode(count(m.coddoc), 1, 'S', 'N')) = 'S' as sentenciado 
from
   movintimacao m,
   t_processo p 
where
   p.coddoc = m.coddoc 
   and p.numproccompl = ?
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
         and p.codsecao = mi.codsecao
   )
   and p.indbaixa = 'N'