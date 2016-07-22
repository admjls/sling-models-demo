<%@include file="/apps/circuit-sling-models/components/global.jsp" %>

<cq:include path="topnavigation" resourceType="circuit-sling-models/components/content/topnavigation"/>

<div class="container" id="content-container">
    <cq:include script="breadcrumb.jsp"/>
    <cq:include script="content.jsp"/>
</div>

<div class="container">
    <cq:include path="footer" resourceType="circuit-sling-models/components/content/footer"/>
</div>