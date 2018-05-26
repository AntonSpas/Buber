<%@ include file="../fragment/head.jspf" %>
<fmt:message key="driver_registration" var="title" />
<c:set var="isValid" value="${not empty isValid ? isValid : true}" />
<%@ include file="../fragment/header.jspf" %>

<c:url var="car_registration" value="/admin/register-car"/>
<fmt:message key="first_name" var="first_name" />
<fmt:message key="surname" var="surname" />
<fmt:message key="email" var="email" />
<fmt:message key="create_password" var="create_password" />
<fmt:message key="phone_number" var="phone_number" />
<fmt:message key="car_type" var="car_type" />
<fmt:message key="car_model_variant" var="car_model_variant" />
<fmt:message key="car_number_variant" var="car_number_variant" />

<main class="flex-fill">
<div id="bf">
    <form action = "${car_registration}" method = "POST" class="needs-validation" novalidate>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <h4><fmt:message key="driver_registration"/></h4>
            <label for="name">${first_name}</label>
            <input type="text" class="form-control" id="name" name="name"
                   pattern="([\u0400-\u04FF]|[A-Za-z]){2,20}"
                   placeholder="${first_name}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_name"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="surname">${surname}</label>
            <input type="text" class="form-control" id="surname" name="surname"
                   pattern="([\u0400-\u04FF]|[A-Za-z]){2,20}"
                   placeholder="${surname}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_surname"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="email">${email}</label>
            <input type="email" class="form-control" id="email" name="email"
                   pattern="[\w\.-]+@[\w\.-]+\.[a-zA-Z]{2,3}"
                   placeholder="${email}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_email"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="password"><fmt:message key="password"/></label>
            <input type="password" class="form-control" id="password" name="password"
                   pattern="(?=.*[a-z])(?=.*\d)[0-9a-zA-Z]{6,16}"
                   placeholder="${create_password}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_password"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="phone"><fmt:message key="phone"/></label>
            <input type="tel" class="form-control" id="phone" name="phone"
                   pattern="\+375\((29|33|44|25)\)\d{3}-\d{2}-\d{2}"
                   placeholder="${phone_number}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_phone"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="type">${car_type}</label>
            <select class="form-control" id="type" name="car_type" required>
                <option value="ECONOMY"><fmt:message key="ECONOMY"/></option>
                <option value="PREMIUM"><fmt:message key="PREMIUM"/></option>
                <option value="LARGE"><fmt:message key="LARGE"/></option>
            </select>
            <div class="invalid-feedback">
                <fmt:message key="invalid_car_type"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="model"><fmt:message key="car_model"/></label>
            <input type="text" class="form-control" id="model" name="car_model"
                   pattern="[a-zA-Z]{2,16}( \S+)+"
                   placeholder="${car_model_variant}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_car_model"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <label for="number"><fmt:message key="car_number"/></label>
            <input type="text" class="form-control" id="number" name="car_number"
                   pattern="\d{4} [A-Z]{2}-[1-7]"
                   placeholder="${car_number_variant}" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_car_number"/>
            </div>
        </div>
        <div class="form-group col-lg-4 col-md-6 col-sm-8 offset-lg-4 offset-md-3 offset-sm-2">
            <button class="btn btn-submit" type="submit"><fmt:message key="register_car"/></button>
        </div>
    </form>
    <c:if test="${!isValid}">
        <div class="error">
            <div class="container">
                    ${message}
            </div>
        </div>
    </c:if>
</div>
</main>

<%@ include file="../fragment/footer.jspf" %>
