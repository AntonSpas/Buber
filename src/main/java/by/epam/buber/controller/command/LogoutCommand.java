package by.epam.buber.controller.command;

import by.epam.buber.controller.Action;
import by.epam.buber.controller.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final String HOME = "/";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new CommandResult(HOME, Action.REDIRECT);
    }
}
