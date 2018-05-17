<%@ include file="fragment/head.jspf" %>
<fmt:message key="home" var="title" />
<c:set var="home_page" value="${true}" />
<%@ include file="fragment/header.jspf" %>

<div id="headerwrap">
    <div class="container">
        <div class="row centered">
            <div class="col-lg-10 col-lg-offset-2">
                <h1><fmt:message key="main_caption"/></h1>
                <h2><fmt:message key="second_caption"/></h2>
            </div>
        </div>
    </div>
</div>
<div id="w">
    <div class="container">
        <div class="row centered">
            <br><br>
            <div class="col-lg-4">
                <h4><fmt:message key="short_code"/></h4>
                <p>1111</p>
            </div>
            <div class="col-lg-4">
                <h4><fmt:message key="landline"/></h4>
                <p>+375 17 111-11-11</p>
            </div>
            <div class="col-lg-4">
                <h4><fmt:message key="mobile"/></h4>
                <p>+375 29 111-11-11</p>
            </div>
        </div>
    </div>
</div>
<div class="container wb">
    <div class="row centered">
        <br><br>
        <div class="col-lg-12 col-lg-offset-2">
            <h4><fmt:message key="benefits"/></h4>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</p>
            <p><br><br></p>
        </div>
    </div>
</div>
<div id="r">
    <div class="container">
        <div class="row centered">
            <div class="col-12">
                <h3><fmt:message key="third_caption"/></h3>
                <h3><fmt:message key="third_text"/></h3>
                <br>
            </div>
            <div class="col-lg-4">
                <h4><fmt:message key="forth_caption"/></h4>
                <p><fmt:message key="forth_text"/></p>
            </div>
            <div class="col-lg-4">
                <h4><fmt:message key="fifth_caption"/></h4>
                <p><fmt:message key="fifth_text"/></p>
            </div>
            <div class="col-lg-4">
                <h4><fmt:message key="sixth_caption"/></h4>
                <p><fmt:message key="sixth_text"/></p>
            </div>
        </div>
    </div>
</div>

<%@ include file="fragment/home_footer.jspf" %>
