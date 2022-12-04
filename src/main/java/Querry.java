import java.sql.*;

public class Querry {
    DataBaseConnection base;
    Connection connection;

    Querry(){
        try {
            base = new DataBaseConnection();
        } catch (SQLException e) {throw new RuntimeException(e);}
    }

    void checkConnection() throws SQLException{
        base.reconnect();
        connection=base.getConnection();
    }

    void disconnect() throws SQLException{
        base.closeConnection();
    }

    boolean isUserExist(String user) throws SQLException{   //1. Czy taka nazwa uzytkownika jest już w bazie?
        checkConnection();
        String statement = "SELECT login from user where login = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, user);
        ResultSet result = querry.executeQuery();
        if(!result.isBeforeFirst()){    //jeżeli zapytanie nie znajdzie rekordu i zwróci pusty wiersz
            disconnect();
            return false;
        }
        else {
            disconnect();
            return true;
        }
    }

    boolean isUserExist(int user) throws SQLException{   //1. Czy taki uzytkownik jest już w bazie?
        checkConnection();
        String statement = "SELECT ID_USER from user where ID_USER = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, user);
        ResultSet result = querry.executeQuery();
        if(!result.isBeforeFirst()){    //jeżeli zapytanie nie znajdzie rekordu i zwróci pusty wiersz
            disconnect();
            return false;
        }
        else {
            disconnect();
            return true;
        }
    }

    boolean isGroupExist(String name) throws SQLException {
        checkConnection();
        String statement = "SELECT * FROM groups where NAME = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, name);
        ResultSet result = querry.executeQuery();
        if(!result.isBeforeFirst()){
            disconnect();
            return false;
        }
        disconnect();
        return true;
    }

    boolean isGroupExist(int groupID) throws SQLException {
        checkConnection();
        String statement = "SELECT * FROM groups where ID_GROUP = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, groupID);
        ResultSet result = querry.executeQuery();
        if(!result.isBeforeFirst()){
            disconnect();
            return false;
        }
        disconnect();
        return true;
    }

    boolean isGroupCodeExist(String groupCode) throws SQLException {
        checkConnection();
        String statement = "SELECT * FROM groups where GROUP_CODE = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, groupCode);
        ResultSet result = querry.executeQuery();
        if(!result.isBeforeFirst()){
            disconnect();
            return false;
        }
        disconnect();
        return true;
    }

    boolean isMailUsing(String userMail) throws SQLException{
        checkConnection();
        String statement = "SELECT email from user where email = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, userMail);
        ResultSet result = querry.executeQuery();
        if(!result.isBeforeFirst()){    //jeżeli zapytanie nie znajdzie rekordu i zwróci pusty wiersz
            disconnect();
            return false;
        }
        else {
            disconnect();
            return true;
        }
    }

    boolean isUserInGroup(int userID, int groupID) throws SQLException {
        checkConnection();
        String statement = "SELECT ID_USER FROM groups_users WHERE ID_USER = ? AND ID_GROUP = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, userID);
        querry.setInt(2, groupID);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){    //jeżeli zapytanie nie znajdzie rekordu i zwróci pusty wiersz
            disconnect();
            return true;
        }
        else {
            disconnect();
            return false;
        }
    }

    int returnUserID(String userLogin) throws SQLException{
        checkConnection();
        String statement = "SELECT ID_USER from user where LOGIN = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, userLogin);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){
            result.next();
            int id = result.getInt(1);
            disconnect();
            return id;
        }
        System.out.println("Nie znaleziono podanego usera.");
        disconnect();
        return -1;
    }

    int returnGroupID(String groupName) throws SQLException{
        checkConnection();
        String statement = "SELECT ID_GROUP from groups where NAME = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, groupName);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){
            result.next();
            int id = result.getInt(1);
            disconnect();
            return id;
        }
        System.out.println("Nie znaleziono podanej grupy.");
        disconnect();
        return -1;
    }

        // 2.Zapis użytkownika do bazy przy rejestracji wraz z kodem
    void addUser(String login, String password, String email, String name, String surname, String color,int token) throws SQLException{
        checkConnection();
        if(!isUserExist(login) && !isMailUsing(email)) {
            checkConnection();
            String statement = "INSERT INTO user VALUES (default, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement querry = connection.prepareStatement(statement);
            System.out.println("return: " + Statement.RETURN_GENERATED_KEYS);
            querry.setString(1, login);
            querry.setString(2, password);
            querry.setString(3, email);
            querry.setString(4, name);
            querry.setString(5, surname);
            querry.setString(6, color);
            querry.setInt(7, token);
            querry.executeUpdate();
        }
        else if(isUserExist(login)){
            System.out.println("Uzytkownik o podanym loginie juz istnieje.");
        }
        else if(isMailUsing(email)){
            System.out.println("Podany email jest juz wykorzystany.");
        }
        disconnect();
    }

    //3. Sprawdzenie czy kod dla użytkownika poprawmy
    boolean isIdForUserCorrect(int id, String userLogin) throws SQLException{
        checkConnection();
        String statement = "SELECT ID_USER from user where LOGIN = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, userLogin);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){ //jeżeli rekord został odnaleziony w bazie to porównaj id
            result.next();
            if(result.getInt(1) == id){
                disconnect();
                return true;
            }
        } else{System.out.println("Podany uzytkownik nie istnieje.");}  //nie znaleziono usera z takim loginem
        disconnect();
        return false;
    }


    //5. Dodanie użytkownika do grupy.
    void addUserToGroup(int userID, int groupID) throws SQLException {
        if(!isUserInGroup(userID, groupID)){
            checkConnection();
            String statement = "INSERT INTO groups_users VALUES (?, ?)";
            PreparedStatement querry = connection.prepareStatement(statement);
            querry.setInt(1, userID);
            querry.setInt(2, groupID);
            querry.executeUpdate();
        }
        else{System.out.println("Ten uzytkownik jest juz przypisany do podanej grupy.");}
        disconnect();
    }

    // dodanie nowej grupy
    void addGroup(String name, String groupCode) throws SQLException{
        checkConnection();
        if(!isGroupExist(name) && !isGroupCodeExist(groupCode)) {
            checkConnection();
            String statement = "INSERT INTO groups VALUES (default, ?, ?)";
            PreparedStatement querry = connection.prepareStatement(statement);
            querry.setString(1, name);
            querry.setString(2, groupCode);
            querry.executeUpdate();
        }
        else if(isGroupExist(name)){
            System.out.println("Grupa o takiej nazwie juz istnieje.");
        }
        else{
            System.out.println("Podany kod grupy jest już w użyciu.");
        }
        disconnect();
    }

    //6  Usunięcie z grupy
    void deleteUserFromGroup(int userID, int groupID) throws SQLException {
        checkConnection();
        if(isUserInGroup(userID, groupID)){
            checkConnection();
            String statement = "DELETE FROM groups_users WHERE ID_USER = ? AND ID_GROUP = ?";
            PreparedStatement querry = connection.prepareStatement(statement);
            querry.setInt(1, userID);
            querry.setInt(2, groupID);
            querry.executeUpdate();
        }
        else {System.out.println("Wybrany użytkownik nie jest członkiem takiej grupy.");}
        disconnect();
    }

    //7. Zapis tokenu resetu hasła
    void setAuthenticationToken(int userID, int token) throws SQLException{
        checkConnection();
        String statement = "UPDATE user SET AUTHENTICATION_TOKEN = ? WHERE ID_USER = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, token);
        querry.setInt(2, userID);
        querry.executeUpdate();
        disconnect();
    }

    // zwróć zapisany token
    int returnAuthenticationToken(int userID) throws SQLException{
        checkConnection();
        String statement = "SELECT AUTHENTICATION_TOKEN FROM user WHERE ID_USER = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, userID);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){ //jeżeli znaleziono wynik w bazie
            result.next();
            int token = result.getInt(1);
            disconnect();
            return token;
        }
        System.out.println("Nie znaleziono zapisanego tokenu");
        disconnect();
        return -1;  //nie ma wygenerowanego tokenu - zwróć "-1"
    }


    //8. Usunięcie użytkownika
    void deleteUser(int userID) throws SQLException{
        checkConnection();
        if(isUserExist(userID)){
            checkConnection();

            String statementG = "DELETE FROM groups_users WHERE ID_USER = ?";
            PreparedStatement querryG = connection.prepareStatement(statementG);
            querryG.setInt(1, userID);
            querryG.executeUpdate();

            String statementT = "DELETE FROM tasks_users WHERE ID_USER = ?";
            PreparedStatement querryT = connection.prepareStatement(statementT);
            querryT.setInt(1, userID);
            querryT.executeUpdate();

            String statement = "DELETE FROM user WHERE ID_USER = ?";
            PreparedStatement querry = connection.prepareStatement(statement);
            querry.setInt(1, userID);
            querry.executeUpdate();

            disconnect();
        }
        else {System.out.println("Nie znaleziono takiego użytkownika.");}
        disconnect();
    }

    //usuń podaną grupę
    void deleteGroup(int groupID) throws SQLException{
        checkConnection();

        if(isGroupExist(groupID)) {
            checkConnection();
            String statement = "DELETE FROM groups_users WHERE ID_GROUP = ?";
            PreparedStatement querry = connection.prepareStatement(statement);
            querry.setInt(1, groupID);
            querry.executeUpdate();

            String statementG = "DELETE FROM groups WHERE ID_GROUP = ?";
            PreparedStatement querryG = connection.prepareStatement(statementG);
            querryG.setInt(1, groupID);
            querryG.executeUpdate();
        }
        disconnect();
    }

    //usuń wybrane zadanie
    void deleteTask(int taskID) throws SQLException{
        checkConnection();
        if(isTaskExist(taskID)) {
            checkConnection();
            String statement = "DELETE FROM tasks_users WHERE ID_TASK = ?";
            PreparedStatement querry = connection.prepareStatement(statement);
            querry.setInt(1, taskID);
            querry.executeUpdate();

            String statementG = "DELETE FROM tasks WHERE ID_TASK = ?";
            PreparedStatement querryG = connection.prepareStatement(statementG);
            querryG.setInt(1, taskID);
            querryG.executeUpdate();
        }
        disconnect();
    }

    // Sprawdź czy task jest już w bazie
    boolean isTaskExist(String name) throws SQLException{
        checkConnection();
        String statement = "SELECT ID_TASK FROM task WHERE NAME = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, name);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){
            disconnect();
            return true;
        }
        disconnect();
        return false;
    }
    boolean isTaskExist(int taskID) throws SQLException{
        checkConnection();
        String statement = "SELECT ID_TASK FROM task WHERE ID_TASK = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, taskID);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){
            disconnect();
            return true;
        }
        disconnect();
        return false;
    }


    // Utworzenie nowego taska
    void addTask(String name, String description) throws SQLException{
        checkConnection();
        if(!isTaskExist(name)) {
            checkConnection();
            String statement = "INSERT INTO task VALUES (default, ?, ?, CURRENT_DATE, CURRENT_DATE)";
            PreparedStatement querry = connection.prepareStatement(statement);
            querry.setString(1, name);
            querry.setString(2, description);
            querry.executeUpdate();
        }
        else {System.out.println("Zadanie o podanej nazwie juz istnieje.");}
        disconnect();
    }

    boolean isUserInTask(int userID, int taskID) throws SQLException{
        checkConnection();
        String statement = "SELECT ID_USER FROM tasks_users WHERE ID_USER = ? AND ID_TASK = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, userID);
        querry.setInt(2, taskID);
        ResultSet result = querry.executeQuery();
        if(!result.isBeforeFirst()){    //jeżeli zapytanie nie znajdzie rekordu i zwróci pusty wiersz
            disconnect();
            return false;
        }
        else {
            disconnect();
            return true;
        }
    }

    String returnTaskDescription(int idTask) throws SQLException{
        checkConnection();
        String statement = "SELECT DESCRIPTION from task WHERE ID_TASK = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, idTask);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){
            result.next();
            String desctiption = result.getString(1);
            disconnect();
            return desctiption;
        } else {System.out.println("Nie odnaleziono zadania");}
        disconnect();
        return "";
    }

    //9. Dodanie wpisu do taska
    void addTaskDescription(int taskID, String description) throws SQLException{
        checkConnection();
        String statement = "UPDATE task SET DESCRIPTION = ? WHERE ID_TASK = ? ";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, description);
        querry.setInt(2, taskID);
        querry.executeUpdate();
        disconnect();
    }

    //ustaw wybrany kolor podanemu użytkownikowi
    void setUserColor(int userID, String color) throws SQLException{
        checkConnection();
        String statement = "UPDATE user SET COLOR = ? WHERE ID_USER = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setString(1, color);
        querry.setInt(2, userID);
        querry.executeUpdate();
        disconnect();
    }

    //zwróć aktualny kolor użytkownika
    String returnUserColor(int userID) throws SQLException{
        checkConnection();
        String statement = "SELECT COLOR from user WHERE ID_USER = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, userID);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){ //jeśli wynik zapytanie nie jest pusty
            result.next();
            String kolor = result.getString(1);
            disconnect();
            return kolor;
        }
        else{System.out.println("Nie znaleziono koloru dla takiego użytkownika");}
        disconnect();
        return "";
    }

    //dodaj użytkownika do taska
    void addTaskForUser(int userID, int taskID) throws SQLException{
        if(!isUserInTask(userID, taskID)){
            checkConnection();
            String statement = "INSERT INTO tasks_users VALUES (?, ?, ?)";
            PreparedStatement querry = connection.prepareStatement(statement);
            querry.setInt(1, userID);
            querry.setInt(2, taskID);
            querry.setInt(3, 0); //domyślnie 0 jako niewyświetlone zadanie
            querry.executeUpdate();
        }
        else{System.out.println("Użytkownik jest już przypisany do tego zadania");}
        disconnect();
    }

    //ustaw czy task był wyświetlony przez usera czy nie
    void setDisplay(int userID, int taskID, boolean isDisplaed) throws SQLException{
        checkConnection();
        String statement = "UPDATE tasks_users SET IS_DISPLAY = ? WHERE ID_USER = ? AND ID_TASK = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(2, userID);
        querry.setInt(3, taskID);
        if(isDisplaed){
            querry.setInt(1, 1);    //jeśli true ustaw jako wyświetlone
        }
        else {
            querry.setInt(1,0);     // w przeciwnym razie ustaw 0 jako niewyświetlone
        }
        disconnect();
    }

    //zwraca czy dany user wyświetlił task
    boolean isDisplay(int userID, int taskID) throws SQLException{
        checkConnection();
        String statement = "SELECT IS_DISPLAY FROM task_users WHERE ID_USER = ? AND ID_TASK = ?";
        PreparedStatement querry = connection.prepareStatement(statement);
        querry.setInt(1, userID);
        querry.setInt(2, taskID);
        ResultSet result = querry.executeQuery();
        if(result.isBeforeFirst()){
            result.next();
            if(result.getInt(1) == 1){
                disconnect();
                return true;
            }
            else {
                disconnect();
                return false;
            }
        }
        disconnect();
        System.out.println("Nie znaleziono rekordu!");
        return false;
    }



}
