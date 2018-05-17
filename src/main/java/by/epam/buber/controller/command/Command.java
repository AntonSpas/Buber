package by.epam.buber.controller.command;

import by.epam.buber.controller.CommandResult;
import by.epam.buber.util.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandResult execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException;
}
