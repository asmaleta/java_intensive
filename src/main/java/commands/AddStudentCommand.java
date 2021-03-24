package commands;

import database.DataBaseLogic;
import models.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddStudentCommand extends Command{
    public AddStudentCommand(String name) {
        super(name);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DataBaseLogic dataBaseLogic) {

        return "id = " + dataBaseLogic.addStudent(new Student(null,
                request.getParameter("name"),
                request.getParameter("surname"),
                Integer.parseInt(request.getParameter("age")))).toString();
    }
}
