/*
 * Created on 25/Jul/2003
 *
 */
package ServidorAplicacao.Servico.teacher;

import DataBeans.ExecutionCourseSiteView;
import DataBeans.InfoMetadata;
import DataBeans.InfoQuestion;
import DataBeans.InfoSiteQuestion;
import DataBeans.SiteView;
import DataBeans.util.Cloner;
import Dominio.DisciplinaExecucao;
import Dominio.IDisciplinaExecucao;
import Dominio.IMetadata;
import Dominio.IQuestion;
import Dominio.Metadata;
import Dominio.Question;
import ServidorAplicacao.IServico;
import ServidorAplicacao.Servico.exceptions.FenixServiceException;
import ServidorAplicacao.Servico.exceptions.InvalidArgumentsServiceException;
import ServidorPersistente.ExcepcaoPersistencia;
import ServidorPersistente.IDisciplinaExecucaoPersistente;
import ServidorPersistente.IPersistentMetadata;
import ServidorPersistente.IPersistentQuestion;
import ServidorPersistente.ISuportePersistente;
import ServidorPersistente.OJB.SuportePersistenteOJB;
import UtilTests.ParseMetadata;
import UtilTests.ParseQuestion;

/**
 * @author Susana Fernandes
 */
public class ReadQuestion implements IServico {

	private static ReadQuestion service = new ReadQuestion();

	public static ReadQuestion getService() {
		return service;
	}

	public String getNome() {
		return "ReadQuestion";
	}

	public SiteView run(
		Integer executionCourseId,
		Integer metadataId,
		Integer questionId)
		throws FenixServiceException {
		try {
			ISuportePersistente persistentSuport =
				SuportePersistenteOJB.getInstance();
			IDisciplinaExecucaoPersistente persistentExecutionCourse =
				persistentSuport.getIDisciplinaExecucaoPersistente();
			IDisciplinaExecucao executionCourse =
				new DisciplinaExecucao(executionCourseId);
			executionCourse =
				(IDisciplinaExecucao) persistentExecutionCourse.readByOId(
					executionCourse,
					false);
			if (executionCourse == null) {
				throw new InvalidArgumentsServiceException();
			}
			IPersistentMetadata persistentMetadata =
				persistentSuport.getIPersistentMetadata();
			IMetadata metadata = new Metadata(metadataId);
			metadata =
				(IMetadata) persistentMetadata.readByOId(metadata, false);
			if (metadata == null) {
				throw new InvalidArgumentsServiceException();
			}
			InfoMetadata infoMetadata =
				Cloner.copyIMetadata2InfoMetadata(metadata);
			ParseMetadata p = new ParseMetadata();
			try {
				infoMetadata =
					p.parseMetadata(metadata.getMetadataFile(), infoMetadata);
			} catch (Exception e) {
				throw new FenixServiceException(e);
			}
			IPersistentQuestion persistentQuestion =
				persistentSuport.getIPersistentQuestion();
			IQuestion question = null;
			if (questionId.equals(new Integer(-1))) {
				question =
					(IQuestion) persistentQuestion
						.readByFileNameAndMetadataId((String)infoMetadata.getMembers().get(0),metadata);
			} else {
				question = new Question(questionId);
				question =
					(IQuestion) persistentQuestion.readByOId(question, false);
			}
			if (question == null) {
				throw new InvalidArgumentsServiceException();
			}
			InfoQuestion infoQuestion =
				Cloner.copyIQuestion2InfoQuestion(question);
			infoQuestion.setInfoMetadata(infoMetadata);
			ParseQuestion parse = new ParseQuestion();
			try {
				infoQuestion =
					parse.parseQuestion(
						infoQuestion.getXmlFile(),
						infoQuestion);
			} catch (Exception e) {
				throw new FenixServiceException(e);
			}
			InfoSiteQuestion bodyComponent = new InfoSiteQuestion();
			bodyComponent.setInfoQuestion(infoQuestion);
			bodyComponent.setExecutionCourse(
				Cloner.copyIExecutionCourse2InfoExecutionCourse(
					executionCourse));
			SiteView siteView =
				new ExecutionCourseSiteView(bodyComponent, bodyComponent);
			return siteView;
		} catch (ExcepcaoPersistencia e) {
			throw new FenixServiceException(e);
		}
	}
}
