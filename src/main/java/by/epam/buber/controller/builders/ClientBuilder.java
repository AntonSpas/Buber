package by.epam.buber.controller.builders;

import by.epam.buber.model.Client;

import javax.servlet.http.HttpServletRequest;

public class ClientBuilder implements EntityBuilder {

    @Override
    public Client build(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setEmail(email);
        client.setPassword(password);
        client.setPhone(phone);

        return client;
    }
}
