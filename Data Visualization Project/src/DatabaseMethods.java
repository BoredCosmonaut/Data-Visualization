import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseMethods {
    public static void main(String[] args) throws Exception {
        DatabaseConnection connection = new DatabaseConnection();

        //Class with 2 variables
        DatabaseMethodsForTwoVariables twoVariableAccess = new DatabaseMethodsForTwoVariables(connection.connect(), "Sales", "Month", "Total");
        //twoVariableAccess.createTableWith2Variables();
        //twoVariableAccess.addData(34,"June",2333);
        //twoVariableAccess.getData(52);
        //twoVariableAccess.getAllData();
        //twoVariableAccess.updateVariable1(34,"July");
        //twoVariableAccess.updateVariable2(52,45);
        //twoVariableAccess.deleteRow(34);
        //int value = twoVariableAccess.getVariable2ValueWithId(52);
        //String name = twoVariableAccess.getVariable1ValueWithId(52);
        //System.out.println(name + "'s "+ twoVariableAccess.getVariable2() + " " + twoVariableAccess.getTableName()+ "'s" + " is : " + value);
        //HashMap<String,Integer> map = twoVariableAccess.createHashMap();
        //System.out.println(map.get(twoVariableAccess.getVariable1ValueWithId(34)));
        //System.out.println(map.get(twoVariableAccess.getVariable1ValueWithId(52)));
    }
}