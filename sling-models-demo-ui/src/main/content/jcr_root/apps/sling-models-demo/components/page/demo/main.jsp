<%@include file="/apps/sling-models-demo/components/global.jsp" %>

<cq:include path="topnavigation" resourceType="sling-models-demo/components/content/topnavigation"/>

<div class="container" id="content-container">
    <cq:include script="breadcrumb.jsp"/>
    <cq:include script="content.jsp"/>
</div>

<div class="container">
    <cq:include path="footer" resourceType="sling-models-demo/components/content/footer"/>
</div>