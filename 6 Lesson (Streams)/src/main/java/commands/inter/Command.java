package commands.inter;

public interface Command {
    void doCommand(String args);
    String getArgs();
}
