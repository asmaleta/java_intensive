package commands;

import database.DataBaseLogic;
import models.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ShowStudentCommand extends Command {
    public ShowStudentCommand(String name) {
        super(name);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response, DataBaseLogic dataBaseLogic) {
        List<Student> students = dataBaseLogic.getStudents();
        req.getSession().setAttribute("students", students);
        return students.toString();
    }
}
