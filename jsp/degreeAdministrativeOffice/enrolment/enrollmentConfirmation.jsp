<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="Util.EnrolmentState" %>
<h2><bean:message key="title.student.LEEC.enrollment"/></h2>
<span class="error"><html:errors/></span>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" class="infoselected">
			<b><bean:message key="label.student.enrollment.number"/></b>
			<bean:write name="infoStudentEnrolmentContext" property="infoStudentCurricularPlan.infoStudent.number" />&nbsp;-&nbsp;
			<bean:write name="infoStudentEnrolmentContext" property="infoStudentCurricularPlan.infoStudent.infoPerson.nome" />
			<br />
			<b><bean:message key="label.student.enrollment.executionPeriod"/></b>
			<bean:write name="infoStudentEnrolmentContext" property="infoExecutionPeriod.name" />&nbsp;				
			<bean:write name="infoStudentEnrolmentContext" property="infoExecutionPeriod.infoExecutionYear.year" />
		</td>
	</tr>
	<tr>
		<td style="text-align:center">
			<br /><br /><b><bean:message key="message.student.enrollment.confirmation" /></b><br /><br />
		</td>
	</tr>
</table>
<br />
<table>	
	<logic:present name="infoStudentEnrolmentContext" property="infoStudentCurricularPlan.infoBranch">
		<tr>
			<td>
				<bean:message key="label.student.enrollment.specializationArea" />:&nbsp;
				<bean:write name="infoStudentEnrolmentContext" property="infoStudentCurricularPlan.infoBranch.name" />
			</td>
			<td>
				<bean:message key="label.branch.credits" />:&nbsp;
				<bean:write name="infoStudentEnrolmentContext" property="infoStudentCurricularPlan.infoBranch.specializationCredits" />
			</td>
		</tr>
		<tr>
			<td>
				<bean:message key="label.student.enrollment.secondaryArea" />:&nbsp;
				<bean:write name="infoStudentEnrolmentContext" property="infoStudentCurricularPlan.infoSecundaryBranch.name" />
			</td>
			<td>
				<bean:message key="label.branch.credits" />:&nbsp;
				<bean:write name="infoStudentEnrolmentContext" property="infoStudentCurricularPlan.infoSecundaryBranch.secondaryCredits" />
			</td>
		</tr>
	</logic:present>
	<logic:notPresent name="infoStudentEnrolmentContext" property="infoStudentCurricularPlan.infoBranch">
		<tr>
			<td colspan='2'>
				<bean:message key="label.student.enrollment.specializationArea" />:&nbsp;
				<bean:message key="label.student.enrollment.no.area" />
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<bean:message key="label.student.enrollment.secondaryArea" />:&nbsp;
				<bean:message key="label.student.enrollment.no.area" />
			</td>
		</tr>
	</logic:notPresent>
	<tr>
		<td colspan="2">
			<br />
			<b><bean:message key="message.student.enrolled.curricularCourses" /></b>
		</td>
	</tr>
	<logic:iterate id="enrollmentElem" name="infoStudentEnrolmentContext" property="studentCurrentSemesterInfoEnrollments" type="DataBeans.InfoEnrolment">
		<tr>
			<td colspan='2'>
				<bean:write name="enrollmentElem" property="infoCurricularCourse.name"/>
			</td>
		</tr>
	</logic:iterate>
</table>
<logic:present name="curriculum">
	<table>
		<tr>
			<td colspan="5">
				<br />
				<b><bean:message key="message.student.curriculum" /></b>
			</td>
		</tr>
		<tr>
			<td class="listClasses-header"><bean:message key="label.student.curriculum.curricularYear" /></td>
			<td class="listClasses-header"><bean:message key="label.curricular.course.semester" /></td>
			<td class="listClasses-header"><bean:message key="label.student.curriculum.degree" /></td>
			<td class="listClasses-header"><bean:message key="label.student.curriculum.course" /></td>
			<td class="listClasses-header"><bean:message key="label.student.curriculum.finalEvaluation" /></td>
		</tr>
		<logic:iterate id="curriculumElem" name="curriculum">
			<tr>
				<td class="listClasses">
					<bean:write name="curriculumElem" property="infoExecutionPeriod.infoExecutionYear.year"/>
				</td>
				<td class="listClasses">
					<bean:write name="curriculumElem" property="infoExecutionPeriod.semester"/>
				</td>
				<td class="listClasses">
					<bean:write name="curriculumElem" property="infoCurricularCourse.infoDegreeCurricularPlan.infoDegree.sigla"/>
				</td>
				<td class="listClasses">
					<bean:write name="curriculumElem" property="infoCurricularCourse.name"/>
				</td>
			  <td class="listClasses">
				<logic:notEqual name="curriculumElem" property="enrolmentState" value="<%= EnrolmentState.APROVED.toString() %>">
					<bean:message name="curriculumElem" property="enrolmentState.name" bundle="ENUMERATION_RESOURCES" />
				</logic:notEqual>
				
				<logic:equal name="curriculumElem" property="enrolmentState" value="<%= EnrolmentState.APROVED.toString() %>">
					<bean:write name="curriculumElem" property="infoEnrolmentEvaluation.grade"/>
				</logic:equal>
			  </td>
			</tr>
		</logic:iterate>
	</table>
</logic:present>
