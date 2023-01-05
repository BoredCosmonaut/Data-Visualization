import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseMethodsForTwoVariables {
    private Connection connection;
    private String tableName, variable1, variable2;
    ArrayList<String> tableNames = new ArrayList<>();
    public int id,index;


    //Variable1 is always string rest is Integer

    public DatabaseMethodsForTwoVariables(Connection connection, String tableName, String variable1, String variable2) {
        this.connection = connection;
        this.tableName = tableName;
        this.variable1 = variable1;
        this.variable2 = variable2;
    }


     // METHODS FOR  A TABLE WITH 2 VARIABLES / ATTRIBUTE ( 1 STRING 1 INT)
    //Creates a table
    public void createTableWith2Variables() {
        // Query for creating a table
        String query = "CREATE TABLE IF NOT EXISTS " + getTableName() + " (data_id   INTEGER PRIMARY KEY,  "  + getVariable1() + " VARCHAR(255), " + getVariable2() + " INTEGER)"; // Creating a table with our variable
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(query); // Executes the query
            tableNames.add(getTableName());
            index++;
            id++;
            //storeName();
            //System.out.println("Table has been created");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Inserts data into the table
    public long  addData(int ID,String input1, int input2) {
        String query = "INSERT INTO " + getTableName() + "(data_id," + getVariable1() + "," + getVariable2() + ")" + " VALUES(?,?,?)"; // Query for inserting data
        long id = 0; // Variable to return the id of data
        try(PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,ID); // Sets the first question mark to data_id
            statement.setString(2,input1); // Sets the second question mark to input1
            statement.setInt(3,input2); // Sets the third question mark to the input2
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) { // Checks if any rows are affected
                try(ResultSet set = statement.getGeneratedKeys()) {
                    if (set.next()) { // Gets the affected row
                        id = set.getLong(1);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       // System.out.println("The data has been entered into the database with the id of " + id);
        return id; // Returns the ID
    }


    //Gets the specified info of the data that has the given id
    public void getData(int ID) {
        String query = "Select data_id ," + getVariable1() + "," + getVariable2() + " FROM " + getTableName() + " WHERE data_id = ?"; //Query
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,ID);
            ResultSet resultSet = statement.executeQuery();
            displayDataForTwoVariables(resultSet); //Calls the method while sending the result set as a parameter
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Gets all the data thats stored in the table
    public void getAllData() {
        String query = "SELECT data_id, " + getVariable1() + ", " + getVariable2() + " FROM " + getTableName(); //Query
        try(Statement statement = connection.createStatement();
                ResultSet set = statement.executeQuery(query)) {
            displayDataForTwoVariables(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Method used to print data
    private void displayDataForTwoVariables(ResultSet rs) throws  SQLException {
        while (rs.next()) {
           // System.out.println("_______________________________________");
           // System.out.println(rs.getString("data_id") + "\t" + rs.getString(getVariable1()) +"\t" + rs.getString(getVariable2()));
        }
       // System.out.println("_______________________________________");
    }

    //Deleting a data from the table
    public void deleteRow(int id) {
        String query = "DELETE FROM " + getTableName() + " WHERE data_id = ?"; // Query
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,id);
            int affectedRows = statement.executeUpdate(); // Gets the number of affected rows
            if (affectedRows > 0) { // Checks the affected rows
               // System.out.println("Data has been removed from the table");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //Variable update methods

    //Updates the first variable
    public void updateVariable1(int id, String newVariable1Data) {
        String query = "Update " + getTableName() + " SET " + getVariable1() + " = ? WHERE data_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,newVariable1Data);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       // System.out.println("Variable is successfully updated");
    }

    //Updates the second variable
    public void updateVariable2(int id, int newVariable2Data) {
        String query = "Update " + getTableName() + " SET " + getVariable2() + " = ? WHERE data_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,newVariable2Data);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       // System.out.println("Variable is successfully updated");
    }


    //Creates a hash map, variable1 is the key
    public HashMap<String,Integer> createHashMap() {
        String query = "SELECT " + getVariable1() + ", " + getVariable2() + " FROM " + getTableName();
        HashMap<String,Integer> returnMap = new HashMap<>();
        try(Statement statement = connection.createStatement();
                ResultSet set = statement.executeQuery(query)) {
            while (set.next()) {
                returnMap.put(set.getString(getVariable1()), set.getInt(getVariable2()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return returnMap;
    }




    //Getting string type variable1
    public String getVariable1ValueWithId(int id) {
        String query = "SELECT "+ getVariable1() + " FROM " + getTableName() + " WHERE data_id = ?";
        String data = "";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                data = set.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }


    //Getting variable2 as a integer
    public int getVariable2ValueWithId(int id) {
        String query = "SELECT "+ getVariable2() + " FROM " + getTableName() + " WHERE data_id = ?";
        int data = 0;
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                data = set.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }


    //Storing the table names;
    //public void storeName(String name) {
    //String query = "INSERT INTO tablenamefortwo (data_id, name, index) VALUES(?,?,?)";
    //int index = getLastIndex() + 1;
    //try(PreparedStatement statement = connection.prepareStatement(query)) {
    //statement.setInt(1,index);
    //statement.setString(2, name);
    //statement.setInt(3, index);
    //statement.executeUpdate();
    //} 
    //catch (Exception e) {
			
    //}
    //}
    
    //public String getTableName(int ID) {
    //String query = "Select name  FROM tablenamefortwo WHERE data_id = ?"; //Query
    //String name = " ";
    //try(PreparedStatement statement = connection.prepareStatement(query)){
    //statement.setInt(1,ID);
    //ResultSet resultSet = statement.executeQuery();
    //while(resultSet.next()) {
    //name = resultSet.getString(1);
    //}
    //} catch (SQLException e) {
    //throw new RuntimeException(e);
    //}
    //return name;
    //}
    
    //public int getLastIndex() {
    //String query = "SELECT index FROM tablenamefortwo WHERE data_id=(SELECT max(data_id) FROM tablenamefortwo)";
    //int data = 0;
    //try(PreparedStatement statement = connection.prepareStatement(query)) {
    // ResultSet set = statement.executeQuery();
    //while (set.next()) {
    //data = set.getInt(1);
    //}
    //} catch (SQLException e) {
    //throw new RuntimeException(e);
    //}
    //return data;
    //}
    


    //Variable1
    //public void storeVariable1(String name) {
    //String query = "INSERT INTO "+ getTableName() + " (data_id, name, index) VALUES(?,?,?)";
    //int index = getLastIndex() + 1;
    //try(PreparedStatement statement = connection.prepareStatement(query)) {
    //statement.setInt(1,index);
    //statement.setString(2, name);
    //statement.setInt(3, index);
    //statement.executeUpdate();
    //} 
    //catch (Exception e) {
			
    //}
    //}
    
    // public String getVariable1Name(int ID) {
    // String query = "Select name  FROM " + getTableName() + "WHERE data_id = ?"; //Query
    //String name = " ";
    //try(PreparedStatement statement = connection.prepareStatement(query)){
    //statement.setInt(1,ID);
    //ResultSet resultSet = statement.executeQuery();
    // while(resultSet.next()) {
    //name = resultSet.getString(1);
    //}
    //} catch (SQLException e) {
    //throw new RuntimeException(e);
    //}
    //return name;
    //}
    
    // public int getLastIndexForVariable1() {
    //String query = "SELECT index FROM " + getTableName() + "WHERE data_id=(SELECT max(data_id) FROM" + getTableName();
    //int data = 0;
    //try(PreparedStatement statement = connection.prepareStatement(query)) {
    // ResultSet set = statement.executeQuery();
    // while (set.next()) {
    //data = set.getInt(1);
    //}
    //} catch (SQLException e) {
    // throw new RuntimeException(e);
    //}
    //   return data;
    //}


    //Getters
    public String getTableName() {
        return tableName;
    }

    public String getVariable1() {
        return variable1;
    }

    public String getVariable2() {
        return variable2;
    }
    public void setTableName(String s) {
        this.tableName = s;
    }

    public void setVariable1(String s) {
    	this.variable1 = s;
    }

    public void setVariable2(String s) {
    	this.variable2 = s;
    }
}
