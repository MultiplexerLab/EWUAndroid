package ewu.lict.database;

/**
 * Created by Faculty on 9/23/2017.
 */

public class TableAttributes {

    public static final String TABLE_NAME = "student";
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_PASSWORD = "password";
    public static final String STUDENT_PHONENO = "phoneNo";
    public static final String STUDENT_CGPA = "cgpa";

    public String tableCreation(){
        String query = "CREATE TABLE "+TABLE_NAME+"( "+STUDENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                STUDENT_NAME+" TEXT, "+STUDENT_PASSWORD+" TEXT, "+STUDENT_PHONENO+" TEXT, "+
                STUDENT_CGPA+" double)";
        return query;
    }

}
