import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Querry querry = new Querry();
        querry.addUser("krzysiek1", "haslo1234", "mejlik@mail.com", "Krzysztof", "Nowakowski1", "niebieski", 2137);
        querry.addUser("krzysiek2", "haslo1234", "mailk@mail.com", "Krzysztof", "Nowakowski1", "niebieski", 2138);
        querry.addGroup("SUPER Grupa", "SUP");
        querry.addGroup("SUPER Grupa1", "SUP2");
        querry.addGroup("SUPER Grupa3", "SUP3");
        querry.addUserToGroup(querry.returnUserID("krzysiek1"), querry.returnGroupID("SUPER Grupa"));
        querry.addUserToGroup(querry.returnUserID("krzysiek2"), 2);
        querry.addUserToGroup(querry.returnUserID("krzysiek1"), 2);
        querry.addUserToGroup(querry.returnUserID("krzysiek1"), 3);
        querry.addTask("task1", "opis1");
        querry.addTaskForUser(querry.returnUserID("krzysiek1"), 2);
        querry.deleteUser(querry.returnUserID("krzysiek1"));
        querry.addTaskForUser(querry.returnUserID("krzysiek2"), 2);
        System.out.println(querry.isDisplay(5, 2));
        querry.setDisplay(5,2, true);
        System.out.println(querry.isDisplay(5, 2));
        System.out.println(querry.returnUserColor(5));
        querry.setUserColor(5,"czerwony");
        System.out.println(querry.returnUserColor(5));
        querry.setUserColor(5,"niebieski");
        querry.addTaskDescription(2, "OPIS11");
        System.out.println(querry.returnTaskDescription(2));
        //querry.deleteTask(1);
        querry.setPassword(5,2138, "Hasloo2");
        querry.setAuthenticationToken(5, 2139);
        querry.setPassword(5,2138, "Hasloo3");
        querry.setPassword(5,2139, "Hasloo4");
    }
}
