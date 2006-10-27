<%@ page language="java" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/fenix-renderers.tld" prefix="fr"%>

<html:xhtml/>

<bean:define id="section" name="section" type="net.sourceforge.fenixedu.domain.Section"/>
<bean:define id="executionCourseId" name="executionCourse" property="idInternal"/>

<h2>
	<bean:message key="label.section"/>
	<fr:view name="section" property="name" />
</h2>

<logic:notEmpty name="section" property="associatedSections">
    <div class="warning0">
        <bean:message key="message.section.subSection.count" bundle="SITE_RESOURCES" 
                      arg0="<%= String.valueOf(section.getAssociatedSectionsCount()) %>"/>
    </div>
</logic:notEmpty>

<logic:notEmpty name="section" property="associatedItems">
    <div class="warning0">
        <bean:message key="message.section.items.count" bundle="SITE_RESOURCES"
                      arg0="<%= String.valueOf(section.getAssociatedItemsCount()) %>"/>
    </div>
</logic:notEmpty>

<fr:form action="<%= String.format("/manageExecutionCourse.do?method=confirmSectionDelete&executionCourseID=%s&sectionID=%s", executionCourseId, section.getIdInternal()) %>">
    <p>
        <bean:message key="message.section.delete.confirm" bundle="SITE_RESOURCES"/>
    </p>
    
    <html:submit property="confirm">
        <bean:message key="button.confirm" bundle="SITE_RESOURCES"/>
    </html:submit>
    
    <html:cancel property="cancel">
        <bean:message key="button.cancel" bundle="SITE_RESOURCES"/>
    </html:cancel>
</fr:form>

