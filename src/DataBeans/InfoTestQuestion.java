/*
 * Created on 31/Jul/2003
 *
 */
package DataBeans;

/**
 * @author Susana Fernandes
 */
public class InfoTestQuestion extends InfoObject implements Comparable {
	private Integer testQuestionOrder;
	private Integer testQuestionValue;
	private InfoQuestion question;
	private InfoTest test;

	public InfoTestQuestion() {
	}

	public int compareTo(Object arg0) {
		InfoTestQuestion infoTestQuestion = (InfoTestQuestion) arg0;
		return getTestQuestionOrder().compareTo(
			infoTestQuestion.getTestQuestionOrder());
	}

	public InfoQuestion getQuestion() {
		return question;
	}

	public InfoTest getTest() {
		return test;
	}

	public Integer getTestQuestionValue() {
		return testQuestionValue;
	}

	public Integer getTestQuestionOrder() {
		return testQuestionOrder;
	}

	public void setQuestion(InfoQuestion question) {
		this.question = question;
	}

	public void setTest(InfoTest test) {
		this.test = test;
	}

	public void setTestQuestionValue(Integer integer) {
		testQuestionValue = integer;
	}

	public void setTestQuestionOrder(Integer integer) {
		testQuestionOrder = integer;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof InfoTestQuestion) {
			InfoTestQuestion infoTestQuestion = (InfoTestQuestion) obj;
			result = getIdInternal().equals(infoTestQuestion.getIdInternal());
			result =
				result
					|| ((getTestQuestionOrder()
						.equals(infoTestQuestion.getTestQuestionOrder()))
						&& (getTestQuestionValue()
							.equals(infoTestQuestion.getTestQuestionValue()))
						&& (getTest().equals(infoTestQuestion.getTest()))
						&& (getQuestion().equals(infoTestQuestion.getQuestion())));
		}
		return result;
	}
}