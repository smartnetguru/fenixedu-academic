<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr" %>

<h2><bean:message key="label.absences" bundle="CONTRACTS_RESOURCES"/></h2>

<logic:present name="absences">
	<fr:edit name="absences">
		<fr:schema type="net.sourceforge.fenixedu.domain.personnelSection.contracts.Absence" bundle="CONTRACTS_RESOURCES">
			<fr:slot readOnly="true" name="giafId" key="label.giafId" layout="null-as-label"/>
			<fr:slot readOnly="true" name="name" key="label.situation" layout="null-as-label"/>
			<fr:slot name="importAbsence" layout="null-as-label"/>
			<fr:slot name="isSabaticalOrEquivalent" layout="null-as-label"/>
			<fr:slot name="hasMandatoryCredits" key="label.hasMandatoryCredits" layout="null-as-label"/>
			<fr:slot name="giveCredits" layout="null-as-label"/>
		</fr:schema>
		<fr:layout name="tabular-editable">
			<fr:property name="classes" value="tstyle2 thlight mtop15" />
			<fr:property name="sortBy" value="giafId"/>
		</fr:layout>
	</fr:edit>
</logic:present>
