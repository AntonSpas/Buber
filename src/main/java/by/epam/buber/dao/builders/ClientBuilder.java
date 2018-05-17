package by.epam.buber.dao.builders;

import by.epam.buber.model.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements EntityBuilder {

    @Override
    public Client build(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        String email = resultSet.getString(4);
        String password = resultSet.getString(5);
        String phone = resultSet.getString(6);
        Integer banScores = resultSet.getInt(7);
        String stringEnabled = resultSet.getString(8);
        Boolean enabled;
        if("1".equals(stringEnabled)) {
            enabled = true;
        } else {
            enabled = false;
        }

        Client client = new Client();

        client.setId(id);
        client.setName(name);
        client.setSurname(surname);
        client.setEmail(email);
        client.setPassword(password);
        client.setPhone(phone);
        client.setBanScores(banScores);
        client.setEnabled(enabled);

        return client;
    }
}
