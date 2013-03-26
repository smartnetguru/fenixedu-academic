<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html:xhtml/>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr" %>
<bean:define id="degreeCurricularPlanID" name="degreeCurricularPlanID" scope="request" />
<bean:define id="executionDegreeOID" name="executionDegree" property="externalId" type="String"/>

<h2>
	<bean:message key="title.finalDegreeWorkProposal"/>
</h2>	

<h3>
	<bean:message key="message.final.degree.work.administration"/>
	<bean:write name="executionDegree" property="executionYear.nextYearsYearString"/>
</h3>	

<logic:present name="executionDegree" property="scheduling">
<logic:notEqual name="executionDegree" property="scheduling.executionDegreesSortedByDegreeName" value="1">
	<div class="infoop2">
		<p>
			<strong><bean:message key="message.final.degree.work.other.execution.degrees"/></strong>
		</p>
			<logic:iterate id="currentExecutionDegree" name="executionDegree" property="scheduling.executionDegreesSortedByDegreeName">
				<logic:notEqual name="currentExecutionDegree" property="externalId" value="<%= executionDegreeOID %>">
					<p class="mvvert05">
						<bean:write name="currentExecutionDegree" property="degreeCurricularPlan.presentationName"/>
					</p>
				</logic:notEqual>
			</logic:iterate>
	</div>
</logic:notEqual>
</logic:present>

<%--
<div class="infoop2">
	<p>Info msg</p>
</div>
--%>

<p>
	<html:link page="<%= "/manageFinalDegreeWork.do?method=finalDegreeWorkInfo&page=0&degreeCurricularPlanID=" + degreeCurricularPlanID + "&executionDegreeOID=" + executionDegreeOID%>"><bean:message key="button.back"/></html:link>
</p>

<div id="wrap">
	<fr:form action="<%= "/manageFinalDegreeWork.do?method=finalDegreeWorkInfo&page=0&degreeCurricularPlanID=" + degreeCurricularPlanID + "&executionDegreeOID=" + executionDegreeOID%>">
            <p class="mbottom05"><strong><bean:message key="finalDegreeWorkProposal.setProposalPeriod.header"/></strong></p>
			<fr:messages for="proposalPeriods">
				<li><span class="error0"><fr:message show="label"/>:<fr:message /></span></li>
			</fr:messages>
            <div style="border: thin; margin-left: 25px; padding-left: 25px; background-color: whiteSmoke; width: 50%">
            	<fr:edit name="executionDegree" id="proposalPeriods">
            		<fr:schema bundle="APPLICATION_RESOURCES" type="net.sourceforge.fenixedu.domain.ExecutionDegree">
	            		<fr:slot name="scheduling.startOfProposalPeriodDateTime" key="finalDegreeWorkProposal.setProposalPeriod.start"
    	        				validator="net.sourceforge.fenixedu.presentationTier.renderers.validators.DateTimeValidator">
        	    		</fr:slot>
	            		<fr:slot name="scheduling.endOfProposalPeriodDateTime" key="finalDegreeWorkProposal.setProposalPeriod.end"
    	        				validator="net.sourceforge.fenixedu.presentationTier.renderers.validators.DateTimeValidator">
        	    		</fr:slot>
            		</fr:schema>
            	</fr:edit>
            </div>

			<p class="mbottom05"><strong><bean:message key="finalDegreeWorkCandidacy.setCandidacyPeriod.header"/></strong></p>
			<fr:messages for="candidacyPeriods">
				<li><span class="error0"><fr:message show="label"/>:<fr:message /></span></li>
			</fr:messages>
            <div style="border: thin; margin-left: 25px; padding-left: 25px; background-color: whiteSmoke; width: 50%">
            	<fr:edit name="executionDegree" id="candidacyPeriods">
            		<fr:schema bundle="APPLICATION_RESOURCES" type="net.sourceforge.fenixedu.domain.ExecutionDegree">
	            		<fr:slot name="scheduling.startOfCandidacyPeriodDateTime" key="finalDegreeWorkProposal.setProposalPeriod.start"
    	        				validator="net.sourceforge.fenixedu.presentationTier.renderers.validators.DateTimeValidator">
        	    		</fr:slot>
	            		<fr:slot name="scheduling.endOfCandidacyPeriodDateTime" key="finalDegreeWorkProposal.setProposalPeriod.end"
    	        				validator="net.sourceforge.fenixedu.presentationTier.renderers.validators.DateTimeValidator">
        	    		</fr:slot>
            		</fr:schema>
            	</fr:edit>
            </div>
		<p><input value="Submeter" type="submit"></p>
	</fr:form>
</div>