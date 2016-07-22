<%@include file="/apps/circuit-sling-models/components/global.jsp" %>

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="author" content="ICF Olson">
    <aem-library:description/>
    <aem-library:title/>
    <cq:include script="headlibs.jsp"/>
    <cq:include script="/libs/wcm/core/components/init/init.jsp"/>
    <cq:includeClientLib css="circuit.sling.models.demo"/>
    <cq:include script="css_includes.jsp"/>

    <c:if test="${isAuthor}">
        <cq:includeClientLib css="circuit.sling.models.author"/>
    </c:if>
</head>