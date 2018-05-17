package by.epam.buber.controller.builders;

import by.epam.buber.model.Entity;
import by.epam.buber.util.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface EntityBuilder {
    Entity build (HttpServletRequest request) throws ServiceException;
}
