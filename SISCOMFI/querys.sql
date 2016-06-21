/*REPORTES*/
select 	CONCAT(CAST(ppxp.idPartidoPolitico as char(5)), ' - ', pp.Nombre) as Partido,
		year(p.FechaProceso1Inicio) as Anio, ppxp.idProceso, tp.Nombre as TipoProceso,
        'SI' as Fase1,
        case when FechaFase2 is null then 'NO'
			else 'SI'
		end Fase2,
        count(axp.idAdherente) AdherentesAceptados, 
        case when ppxp.EstadoPartido = '1' then 'ACEPTADO'
				when ppxp.EstadoPartido = '0' then 'RECHAZADO'
                else 'EN PROCESO'
		END EstadoFinal
from 	PartidoPoliticoxProceso ppxp, PartidoPolitico pp, 
		Proceso p, TipoProceso tp,
        
        Planillon pl, AdherentexPlanillon axp
        
where 	ppxp.idPartidoPolitico = pp.idPartidoPolitico and
		ppxp.idProceso= p.idProceso	and
        p.idTipoProceso = tp.idTipoProceso and
        
        pl.idPartidoPolitico = ppxp.idPartidoPolitico and
        pl.idProceso = ppxp.idProceso and
        pl.idPlanillon = axp.idPlanillon and        
        axp.EstadoValidez <> '0' and 
		tp.idTipoProceso = 2

group by CONCAT(CAST(ppxp.idPartidoPolitico as char(5)), ' - ', pp.Nombre) ,
		year(p.FechaProceso1Inicio) , ppxp.idProceso, tp.Nombre ,
        'SI',
        case when FechaFase2 is null then 'NO'
			else 'SI'
		end ,
        case when ppxp.EstadoPartido = '1' then 'ACEPTADO'
				when ppxp.EstadoPartido = '0' then 'RECHAZADO'
                else 'EN PROCESO'
		END 