package net.sourceforge.fenixedu.domain.student.curriculum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

import net.sourceforge.fenixedu.domain.DomainReference;
import net.sourceforge.fenixedu.domain.ExecutionYear;
import net.sourceforge.fenixedu.domain.StudentCurricularPlan;
import net.sourceforge.fenixedu.domain.student.Registration;

abstract public class StudentCurriculumBase implements Serializable, ICurriculum {

    final private DomainReference<Registration> registrationDomainReference;

    final private DomainReference<ExecutionYear> executionYearDomainReference;

    static final protected int SCALE = 2;

    static final protected RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public StudentCurriculumBase(final Registration registration, final ExecutionYear executionYear) {
	super();
	if (registration == null) {
	    throw new NullPointerException("error.registration.cannot.be.null");
	}
	this.registrationDomainReference = new DomainReference<Registration>(registration);
	this.executionYearDomainReference = new DomainReference<ExecutionYear>(executionYear);
    }

    final public Registration getRegistration() {
	return registrationDomainReference.getObject();
    }

    final public ExecutionYear getExecutionYear() {
	return executionYearDomainReference.getObject();
    }

    final public StudentCurricularPlan getStudentCurricularPlan() {
	return getRegistration().getStudentCurricularPlan(getExecutionYear());
    }

    public boolean isEmpty() {
	return getCurriculumEntries().isEmpty();
    }

    abstract public Collection<ICurriculumEntry> getCurriculumEntries();

    abstract protected EnrolmentSet getApprovedEnrolments();

    public boolean hasAnyExternalApprovedEnrolment() {
	return false;
    }

    final public BigDecimal getSumEctsCredits() {
	BigDecimal ectsCredits = BigDecimal.ZERO;
	for (final ICurriculumEntry entry : getCurriculumEntries()) {
	    ectsCredits = ectsCredits.add(entry.getEctsCreditsForCurriculum());
	}
	return ectsCredits;
    }

    final public Integer getCurricularYear() {
	final int degreeCurricularYears = getTotalCurricularYears();
	final BigDecimal ectsCreditsCurricularYear = getSumEctsCredits().add(BigDecimal.valueOf(24)).divide(
		BigDecimal.valueOf(60), SCALE * SCALE + 1).add(BigDecimal.valueOf(1));
	return Math.min(ectsCreditsCurricularYear.intValue(), degreeCurricularYears);
    }

    public Integer getTotalCurricularYears() {
	final StudentCurricularPlan plan = getStudentCurricularPlan();
	if (plan == null) {
	    return Integer.valueOf(0);
	}

	return plan.getDegreeType().getYears();
    }

    private AverageType averageType = AverageType.WEIGHTED;

    public void setAverageType(AverageType averageType) {
	this.averageType = averageType;
    }

    final private class AverageCalculator {
	private BigDecimal sumPiCi = BigDecimal.ZERO;
	private BigDecimal sumPi = BigDecimal.ZERO;

	public AverageCalculator() {
	}

	public void add(final Collection<ICurriculumEntry> entries) {
	    for (final ICurriculumEntry entry : entries) {
		add(entry);
	    }
	}

	public void add(final ICurriculumEntry entry) {
	    if (entry.getWeigthTimesGrade() != null) {
		if (averageType == AverageType.WEIGHTED) {
		    sumPi = sumPi.add(entry.getWeigthForCurriculum());
		    sumPiCi = sumPiCi.add(entry.getWeigthTimesGrade());
		} else {
		    sumPi = sumPi.add(BigDecimal.ONE);
		    sumPiCi = sumPiCi.add(entry.getGrade().getNumericValue());
		}
	    }
	}

	public BigDecimal result() {
	    return sumPi.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : sumPiCi.divide(sumPi, SCALE * SCALE + 1,
		    ROUNDING_MODE);
	}

    }

    final public BigDecimal getAverage() {
	final AverageCalculator averageCalculator = new AverageCalculator();
	averageCalculator.add(getCurriculumEntries());
	return averageCalculator.result().setScale(SCALE, ROUNDING_MODE);
    }

    public Integer getRoundedAverage() {
	return getAverage().setScale(0, RoundingMode.HALF_UP).intValue();
    }

    final public BigDecimal getSumPiCi() {
	final AverageCalculator averageCalculator = new AverageCalculator();
	averageCalculator.add(getCurriculumEntries());
	return averageCalculator.sumPiCi;
    }

    final public BigDecimal getSumPi() {
	final AverageCalculator averageCalculator = new AverageCalculator();
	averageCalculator.add(getCurriculumEntries());
	return averageCalculator.sumPi;
    }

    final public BigDecimal getRemainingCredits() {
	final StudentCurricularPlan plan = getStudentCurricularPlan();
	if (plan == null) {
	    return BigDecimal.ZERO;
	}

	return BigDecimal.valueOf(plan.getGivenCredits());
    }

    @Override
    public String toString() {
	final StringBuilder result = new StringBuilder();

	result.append("\n[CURRICULUM]");
	result.append("\n[SUM ENTRIES] " + getCurriculumEntries().size());
	result.append("\n[SUM PiCi] " + getSumPiCi().toString());
	result.append("\n[SUM Pi] " + getSumPi().toString());
	result.append("\n[AVERAGE] " + getAverage());
	result.append("\n[ROUNDED_AVERAGE] " + getRoundedAverage());
	result.append("\n[SUM ECTS CREDITS] " + getSumEctsCredits().toString());
	result.append("\n[CURRICULAR YEAR] " + getCurricularYear());
	result.append("\n[REMAINING CREDITS] " + getRemainingCredits().toString());
	result.append("\n[TOTAL CURRICULAR YEARS] " + getTotalCurricularYears());

	result.append("\n[ENTRIES]");
	for (final ICurriculumEntry entry : getCurriculumEntries()) {
	    result.append("\n[ENTRY] [NAME]" + entry.getName().getContent() + "\t[CREATION_DATE]"
		    + entry.getCreationDateDateTime() + "\t[GRADE] " + entry.getGrade().toString() + "\t[WEIGHT] "
		    + entry.getWeigthForCurriculum() + "\t[ECTS] " + entry.getEctsCreditsForCurriculum() + "\t[CLASS_NAME] "
		    + entry.getClass().getSimpleName());
	}

	return result.toString();
    }

}
