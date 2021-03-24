package commands;

import database.DataBaseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateStudentCommand extends Command {
    public UpdateStudentCommand(String name) {
        super(name);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DataBaseLogic dataBaseLogic) {
        return this.getName();
    }
}
