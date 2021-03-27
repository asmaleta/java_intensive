package commands;

import database.DataBaseLogic;
import models.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateStudentCommand extends Command {
    public UpdateStudentCommand(String name) {
        super(name);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DataBaseLogic dataBaseLogic) {
        if (dataBaseLogic.updateStudent(new Student(Integer.parseInt(request.getParameter("id")),
                                                    request.getParameter("name"),
                                                        request.getParameter("surname"),
                                                            Integer.parseInt(request.getParameter("age")))))
        return "id = " + request.getParameter("id") ;
        else
            return "Error delete from DB";
    }
}
