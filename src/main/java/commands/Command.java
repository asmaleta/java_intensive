package commands;

import database.DataBaseLogic;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Data
public abstract class Command {
    private String name;

    public Command(String name) {
        this.name = name;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response, DataBaseLogic dataBaseLogic){
        return name;
    };



}
