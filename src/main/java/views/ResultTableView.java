package views;

import models.Student;

import java.util.List;
import java.util.stream.Collectors;

public class ResultTableView implements View<List<Student>> {
    @Override
    public String build(List<Student> model) {
        StringBuilder sb = new StringBuilder("<table border = \"2\" >");
        sb.append("<tr>")
                .append("<td>id</td>\n")
                .append("<td>name</td>\n")
                .append("<td>surname</td>\n")
                .append("<td>age</td>\n")
                .append("</tr>")
                .append(
                        model.stream()
                                .map(e -> new ResultView().build(e))
                                .map(e -> "<tr>" + e + "</tr>")
                                .collect(Collectors.joining())
                )
                .append("</table>");
        return sb.toString();
    }

}
