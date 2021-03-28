package servlets;

import commands.Command;
import database.DataBaseConnection;
import database.DataBaseLogic;
import utils.CommandManager;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/index")
public class ControllerServlet extends HttpServlet {
    private CommandManager commandManager;
    private DataBaseLogic dataBaseLogic;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Command command = commandManager.getCommandByName("show");
        resp.getWriter().print(command.execute(req,resp,dataBaseLogic));
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("command");
        Command command = commandManager.getCommandByName(name);
        resp.getWriter().print(command.execute(req,resp,dataBaseLogic));
        if(!name.equals("sort")) {
            command = commandManager.getCommandByName("show");
            command.execute(req, resp, dataBaseLogic);
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }


    @Override
    public void init() {
        commandManager = new CommandManager();
        dataBaseLogic = new DataBaseLogic();
    }

    @Override
    public void destroy()  {
        try {
            DataBaseConnection.close();
        }catch (SQLException e){
            System.out.println("Exception close connection");
        }

    }
}
