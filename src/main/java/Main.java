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
        querry.addUserToGroup(2, 1);
        querry.addUserToGroup(1, 2);
        querry.addUserToGroup(1, 3);
        querry.addTask("task1", "opis1");
        querry.addTaskForUser(1, 1);
        querry.deleteUser(1);
    }
}
