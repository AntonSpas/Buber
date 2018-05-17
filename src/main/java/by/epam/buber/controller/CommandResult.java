package by.epam.buber.controller;

public class CommandResult {
    private String view;
    private Action action;

    public CommandResult(String view, Action action) {
        this.view = view;
        this.action = action;
    }

    public String getView() {
        return view;
    }

    public Action getAction() {
        return action;
    }
}
