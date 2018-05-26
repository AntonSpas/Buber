<%@ include file="../fragment/head.jspf" %>
<fmt:message key="ride_order" var="title" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="home" value="/"/>
<c:url var="ride_order" value="/client/ride-order"/>

<main class="flex-fill">
<div id="smf">
    <form action = "${ride_order}" method = "POST">
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <h4><fmt:message key="ride_order"/></h4>
            <label for="type"><fmt:message key="car_type"/></label>
            <select class="form-control" id="type" name="car_type" required>
                <option value="ECONOMY"><fmt:message key="ECONOMY"/></option>
                <option value="PREMIUM"><fmt:message key="PREMIUM"/></option>
                <option value="LARGE"><fmt:message key="LARGE"/></option>
            </select>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="destination"><fmt:message key="destination"/></label>
            <select class="form-control" id="destination" name="dest_street" required>
                <option value="Aeroflotskaya"><fmt:message key="Aeroflotskaya"/></option>
                <option value="Bogdanovicha"><fmt:message key="Bogdanovicha"/></option>
                <option value="Chaykovskogo"><fmt:message key="Chaykovskogo"/></option>
                <option value="Daumana"><fmt:message key="Daumana"/></option>
                <option value="Dimitrova"><fmt:message key="Dimitrova"/></option>
                <option value="Engelsa"><fmt:message key="Engelsa"/></option>
                <option value="Fedorova"><fmt:message key="Fedorova"/></option>
                <option value="Filimonova"><fmt:message key="Filimonova"/></option>
                <option value="Golodeda"><fmt:message key="Golodeda"/></option>
                <option value="Izmailovskaya"><fmt:message key="Izmailovskaya"/></option>
                <option value="Kalinina"><fmt:message key="Kalinina"/></option>
                <option value="Lopatina"><fmt:message key="Lopatina"/></option>
            </select>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <button class="btn btn-submit" type="submit"><fmt:message key="get_ride"/></button>
        </div>
    </form>
</div>
</main>

<%@ include file="../fragment/footer.jspf" %>
