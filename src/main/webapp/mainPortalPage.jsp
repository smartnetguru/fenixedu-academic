<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<tiles:insert definition="df.layout.two-column-photo" beanName="" flush="true">
  <tiles:put name="title" value="/commons/blank.jsp" />
  <tiles:put name="serviceName" value="Intranet do <%=net.sourceforge.fenixedu.domain.organizationalStructure.Unit.getInstitutionName()%>" />
  <tiles:put name="navGeral" value="/commons/homeLogoutGeneralNavigationBar.jsp"/>
  <tiles:put name="photos" value="/entryPhotos.jsp"/>  
  <tiles:put name="body-context" value="/commons/blank.jsp" />
  <tiles:put name="body" value="/mainPortalPage_bd.jsp" />
  <tiles:put name="footer" value="/copyright.jsp" />
</tiles:insert>
