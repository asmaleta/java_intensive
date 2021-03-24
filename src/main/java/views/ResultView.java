package views;


import models.Student;

public class ResultView implements View <Student> {
    @Override
    public String build(Student model) {
        StringBuilder sb = new StringBuilder();
        sb.append(wrap(model.getId()));
        sb.append(wrap(model.getName()));
        sb.append(wrap(model.getSurname()));
        sb.append(wrap(model.getAge()));
        return sb.toString();
    }

    private String wrap(Object s) {
        return "<td>" + s.toString() + "</td>\n";
    }
}
