import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentCRUD {

    // INSERT
    public static void insertStudent(String name, String email, int age) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO student(name, email, age) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, age);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Inserted Successfully");
            } else {
                System.out.println("Student Insert Failed");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ
    public static void viewStudents() {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM student";
            ResultSet rs = con.createStatement().executeQuery(sql);

            System.out.println("\n--- STUDENT LIST ---");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println(
                        rs.getInt("id") + "  " +
                        rs.getString("name") + "  " +
                        rs.getString("email") + "  " +
                        rs.getInt("age")
                );
            }

            if (!found) {
                System.out.println("No Students Found");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public static void updateStudent(int id, int age) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE student SET age=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, age);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Updated Successfully");
            } else {
                System.out.println("Error: Student ID Not Found");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteStudent(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM student WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Deleted Successfully");
            } else {
                System.out.println("Error: Student ID Not Found");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}