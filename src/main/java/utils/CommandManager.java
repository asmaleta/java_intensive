package utils;

import commands.*;

import java.util.HashMap;


public class CommandManager {

    private HashMap<String, Command> commands;


    public CommandManager() {
        this.commands = new HashMap<>();
        init();
    }

    private void init (){
        registerCommand(new UpdateStudentCommand("update"));
        registerCommand(new AddStudentCommand("add"));
        registerCommand(new ShowStudentCommand("show"));
        registerCommand(new DeleteStudentCommand("delete"));
        registerCommand(new SortStudentsCommand("sort"));
    }

    public Command getCommandByName(String name) {
        return commands.get(name);
    }

    private void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }
}
