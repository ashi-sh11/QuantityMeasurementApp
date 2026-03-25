import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DemoJdbc {
    public static void main(String[] args) throws Exception {
        /* Import the package
           Load and Register the package
           Create a connections
           Create a statement
           Execute the statement
           Process the result
           */
        String sql ="SELECT * FROM \"Students\"";
        String sql1 ="insert into \"Students\" values(5,'Shreya',99)";
        String sql2 ="update \"Students\" set id =1 where id =2 ";
        String sql3 ="delete from \"Students\" where id =1 ";

        String url ="jdbc:postgresql://localhost:5432/DemoJDBC";
        String uName ="postgres";
        String pass = "xyz";
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url,uName,pass);
        System.out.println("Connections Established ! ");

//        Statement st = con.createStatement();
//        ResultSet rs  = st.executeQuery(sql);
        // this will give you one name of all the students
//        System.out.println(rs.next());
//        while (rs.next()){
//            String name  = rs.getString("name");
//            System.out.println(name);
//        }
//        while (rs.next()){
//            int id  = rs.getInt("id");
//            String name  = rs.getString("name");
//            String marks  = rs.getString("marks");
//            System.out.println(id+" " + name+" "+marks);
//        }

        //Inserting the values
//        Statement st = con.createStatement();
//        boolean flag = st.execute(sql1);//this will give the count or if we use select then resultset so it always gives false when the query is excuted
//        System.out.println(flag);
//        st.execute(sql2); // update query
//        st.execute(sql3); //delete query


        //Prepared statements
            //PreparedStatement JDBC me ek class hai jo SQL query ko safe aur efficient tarike se run karne ke liye use hoti hai.
            //Yaha ? ka matlab hai value baad me aayegi.

        //Why PreparedStatement is used
        //
        //1 SQL Injection se safe
        //2 Fast hota hai (query ek baar compile hoti hai)
        //3 Dynamic values easily add ho jati hain

        String query = "SELECT * FROM Students WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, 2);

        ResultSet rs = ps.executeQuery();

        con.close();



        System.out.println("Connections closed ");
    }
}
