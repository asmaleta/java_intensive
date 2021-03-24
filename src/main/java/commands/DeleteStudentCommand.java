package commands;

import database.DataBaseLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteStudentCommand extends Command{
    public DeleteStudentCommand(String name) {
        super(name);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DataBaseLogic dataBaseLogic) {
        dataBaseLogic.deleteStudent(Integer.parseInt(request.getParameter("id")));
        return "id = " + request.getParameter("id");
    }
}
