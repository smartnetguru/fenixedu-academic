package ServidorPersistente.OJB;

import java.util.List;

import org.odmg.QueryException;

import Dominio.IDegreeCurricularPlan;
import Dominio.PossibleCurricularCourseForOptionalCurricularCourse;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.IPersistentChosenCurricularCourseForOptionalCurricularCourse;


/**
 * @author dcs-rjao
 *
 * 24/Mar/2003
 */

public class ChosenCurricularCourseForOptionalCurricularCourseOJB extends ObjectFenixOJB implements IPersistentChosenCurricularCourseForOptionalCurricularCourse {

	public List readAllByDegreeCurricularPlan(IDegreeCurricularPlan degreeCurricularPlan) throws ExcepcaoPersistencia {

		try {
			String oqlQuery = "select all from " + PossibleCurricularCourseForOptionalCurricularCourse.class.getName();
			oqlQuery += " where optionalCurricularCourse.degreeCurricularPlan.name = $1";
			oqlQuery += " and optionalCurricularCourse.degreeCurricularPlan.state = $2";
			oqlQuery += " and optionalCurricularCourse.degreeCurricularPlan.degree.nome = $3";
			oqlQuery += " and optionalCurricularCourse.degreeCurricularPlan.degree.sigla = $4";
			oqlQuery += " and optionalCurricularCourse.degreeCurricularPlan.degree.tipoCurso = $5";

			query.create(oqlQuery);

			query.bind(degreeCurricularPlan.getName());
			query.bind(degreeCurricularPlan.getState());
			query.bind(degreeCurricularPlan.getDegree().getNome());
			query.bind(degreeCurricularPlan.getDegree().getSigla());
			query.bind(degreeCurricularPlan.getDegree().getTipoCurso());

			List result = (List) query.execute();
			try {
				lockRead(result);
			} catch (ExcepcaoPersistencia ex) {
				throw ex;
			}

			return result;

		} catch (QueryException ex) {
			throw new ExcepcaoPersistencia(ExcepcaoPersistencia.QUERY, ex);
		}
	}

}