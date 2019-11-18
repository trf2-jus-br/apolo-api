select concat('581',p.codtippet) as id, p.descr nome from tipopeticao p 
 where p.indativo='S' 
 and upper(p.descr) not like '%PETIÇÃO INICIAL%' 

 order by 2 