package commands;

import database.DataBaseLogic;
import models.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SortStudentsCommand extends Command{
    public SortStudentsCommand(String name) {
        super(name);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, DataBaseLogic dataBaseLogic) {
        List<Student> students = dataBaseLogic.sortStudents();
        request.getSession().setAttribute("students", students);
        return students.toString();
    }
}
