package net.sourceforge.fenixedu.util.gratuity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.fenixedu.util.FenixValuedEnum;

/**
 * @author - Shezad Anavarali (sana@mega.ist.utl.pt) - Nadir Tarmahomed
 *         (naat@mega.ist.utl.pt)
 * 
 */
public final class SibsPaymentStatus extends FenixValuedEnum {

    public static final int NOT_PROCESSED_PAYMENT_TYPE = 1;

    public static final int PROCESSED_PAYMENT_TYPE = 2;

    public static final int DUPLICATE_GRATUITY_PAYMENT_TYPE = 3;

    public static final int DUPLICATE_INSURANCE_PAYMENT_TYPE = 4;

    public static final int INVALID_EXECUTION_YEAR_TYPE = 5;

    public static final int INVALID_EXECUTION_DEGREE_TYPE = 6;

    public static final int INVALID_INSURANCE_VALUE_TYPE = 7;

    public static final int UNABLE_TO_DETERMINE_STUDENT_CURRICULAR_PLAN_TYPE = 8;

    public static final SibsPaymentStatus NOT_PROCESSED_PAYMENT = new SibsPaymentStatus(
            "sibsPaymentStatus.not.processed.payment", SibsPaymentStatus.NOT_PROCESSED_PAYMENT_TYPE);

    public static final SibsPaymentStatus PROCESSED_PAYMENT = new SibsPaymentStatus(
            "sibsPaymentStatus.processed.payment", SibsPaymentStatus.PROCESSED_PAYMENT_TYPE);

    public static final SibsPaymentStatus DUPLICATE_GRATUITY_PAYMENT = new SibsPaymentStatus(
            "sibsPaymentStatus.duplicate.gratuity.payment",
            SibsPaymentStatus.DUPLICATE_GRATUITY_PAYMENT_TYPE);

    public static final SibsPaymentStatus DUPLICATE_INSURANCE_PAYMENT = new SibsPaymentStatus(
            "sibsPaymentStatus.duplicate.insurance.payment",
            SibsPaymentStatus.DUPLICATE_INSURANCE_PAYMENT_TYPE);

    public static final SibsPaymentStatus INVALID_EXECUTION_YEAR = new SibsPaymentStatus(
            "sibsPaymentStatus.invalid.execution.year", SibsPaymentStatus.INVALID_EXECUTION_YEAR_TYPE);

    public static final SibsPaymentStatus INVALID_EXECUTION_DEGREE = new SibsPaymentStatus(
            "sibsPaymentStatus.invalid.execution.degree",
            SibsPaymentStatus.INVALID_EXECUTION_DEGREE_TYPE);

    public static final SibsPaymentStatus INVALID_INSURANCE_VALUE = new SibsPaymentStatus(
            "sibsPaymentStatus.invalid.insurance.value", SibsPaymentStatus.INVALID_INSURANCE_VALUE_TYPE);

    public static final SibsPaymentStatus UNABLE_TO_DETERMINE_STUDENT_CURRICULAR_PLAN = new SibsPaymentStatus(
            "sibsPaymentStatus.unable.to.determine.student.curricular.plan",
            SibsPaymentStatus.UNABLE_TO_DETERMINE_STUDENT_CURRICULAR_PLAN_TYPE);

    /**
     * @param name
     * @param value
     */
    private SibsPaymentStatus(String name, int value) {
        super(name, value);
    }

    public static SibsPaymentStatus getEnum(String name) {
        return (SibsPaymentStatus) getEnum(SibsPaymentStatus.class, name);
    }

    public static SibsPaymentStatus getEnum(int value) {
        return (SibsPaymentStatus) getEnum(SibsPaymentStatus.class, value);
    }

    public static Map getEnumMap() {
        return getEnumMap(SibsPaymentStatus.class);
    }

    public static List getEnumList() {
        return getEnumList(SibsPaymentStatus.class);
    }

    public static Iterator iterator() {
        return iterator(SibsPaymentStatus.class);
    }

    public String toString() {
        String result = "Sibs Payment Status :\n";
        result += "\n  - Sibs Payment Status : " + this.getName();

        return result;
    }

    public static net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus toEnumType(
            final SibsPaymentStatus sibsPaymentStatus) {
        if (sibsPaymentStatus == null) {
            return null;
        }

        switch (sibsPaymentStatus.getValue()) {
        case NOT_PROCESSED_PAYMENT_TYPE:
            return net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.NOT_PROCESSED_PAYMENT;
        case PROCESSED_PAYMENT_TYPE:
            return net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.PROCESSED_PAYMENT;
        case DUPLICATE_GRATUITY_PAYMENT_TYPE:
            return net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.DUPLICATE_GRATUITY_PAYMENT;
        case DUPLICATE_INSURANCE_PAYMENT_TYPE:
            return net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.DUPLICATE_INSURANCE_PAYMENT;
        case INVALID_EXECUTION_YEAR_TYPE:
            return net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.INVALID_EXECUTION_YEAR;
        case INVALID_EXECUTION_DEGREE_TYPE:
            return net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.INVALID_EXECUTION_DEGREE;
        case INVALID_INSURANCE_VALUE_TYPE:
            return net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.INVALID_INSURANCE_VALUE;
        case UNABLE_TO_DETERMINE_STUDENT_CURRICULAR_PLAN_TYPE:
            return net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.UNABLE_TO_DETERMINE_STUDENT_CURRICULAR_PLAN;
        }
        throw new RuntimeException("Enum and Util types do not match!");
    }

    public static SibsPaymentStatus toUtilType(
            final net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus sibsPaymentStatus) {
        if (sibsPaymentStatus == null) {
            return null;
        }

        if (sibsPaymentStatus
                .equals(net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.NOT_PROCESSED_PAYMENT))
            return NOT_PROCESSED_PAYMENT;
        if (sibsPaymentStatus
                .equals(net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.PROCESSED_PAYMENT))
            return PROCESSED_PAYMENT;
        if (sibsPaymentStatus
                .equals(net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.DUPLICATE_GRATUITY_PAYMENT))
            return DUPLICATE_GRATUITY_PAYMENT;
        if (sibsPaymentStatus
                .equals(net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.DUPLICATE_INSURANCE_PAYMENT))
            return DUPLICATE_INSURANCE_PAYMENT;
        if (sibsPaymentStatus
                .equals(net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.INVALID_EXECUTION_YEAR))
            return INVALID_EXECUTION_YEAR;
        if (sibsPaymentStatus
                .equals(net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.INVALID_EXECUTION_DEGREE))
            return INVALID_EXECUTION_DEGREE;
        if (sibsPaymentStatus
                .equals(net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.INVALID_INSURANCE_VALUE))
            return INVALID_INSURANCE_VALUE;
        if (sibsPaymentStatus
                .equals(net.sourceforge.fenixedu.domain.gratuity.SibsPaymentStatus.UNABLE_TO_DETERMINE_STUDENT_CURRICULAR_PLAN))
            return UNABLE_TO_DETERMINE_STUDENT_CURRICULAR_PLAN;

        throw new RuntimeException("Enum and Util types do not match!");
    }
}