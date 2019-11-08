//package tables;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import database.ConnectionManager;
//import beans.Employee;
//
//public class StaffManager {
//
//    private static Connection conn = ConnectionManager.getInstance().getConnection();
//
//    public static void displayAllRows() throws SQLException {
//
//        String sql = "SELECT * FROM Staff";
//        try (
//                Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(sql);) {
//
//            System.out.println("Staff Table:");
//            while (rs.next()) {
//                StringBuilder bf = new StringBuilder();
//                bf.append(rs.getInt("id")).append(": ");
//                bf.append(rs.getString("staffId")).append(": ");
//                bf.append(rs.getString("firstName")).append(", ");
//                bf.append(rs.getString("lastName")).append(", ");
//                bf.append(rs.getString("email")).append(", ");
//                bf.append(rs.getString("userName")).append(", ");
//                bf.append(rs.getString("password"));
//                bf.append(rs.getString("imagePath")).append(", ");
//                System.out.println(bf.toString());
//            }
//        }
//    }
//
//    public static Employee searchStaff(String username, String password) throws SQLException {
//
//        String sql = "SELECT * FROM Staff WHERE userName =" + username + " AND password =" + password;
//        try (
//                Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(sql);) {
//
//            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
//            Employee staff = getStaffFromResultSet(rs);
//            //Return employee object
//            return staff;
//        } catch (SQLException e) {
//            System.out.println("SELECT * FROM Staff WHERE userName =" + username + " AND password =" + password + e);
//            //Return exception
//            throw e;
//        }
//    }
//
//    //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
//    private static Employee getStaffFromResultSet(ResultSet rs) throws SQLException {
//        Employee staff = null;
//        if (rs.next()) {
//            staff = new Employee();
//            staff.setId(rs.getInt("id"));
//            staff.setStaffId(rs.getString("staffId"));
//            staff.setFirstName(rs.getString("firstName"));
//            staff.setLastName(rs.getString("lastName"));
//            staff.setEmail(rs.getString("email"));
//            staff.setUserName(rs.getString("userName"));
//            staff.setPassword(rs.getString("password"));
//            staff.setImagePath(rs.getString("imagePath"));
//        }
//        return staff;
//    }
//
//    public static Employee getRow(int id) throws SQLException {
//
//        String sql = "SELECT * FROM Staff WHERE id = ?";
//        ResultSet rs = null;
//
//        try (
//                PreparedStatement stmt = conn.prepareStatement(sql);) {
//            stmt.setInt(1, id);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                Employee bean = new Employee();
//                bean.setId(id);
//                bean.setUserName(rs.getString("userName"));
//                bean.setPassword(rs.getString("password"));
//
//                return bean;
//
//            } else {
//                return null;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return null;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//        }
//
//    }
//
//    public static Employee validate(String username, String password) throws SQLException {
//
//        String sql = "SELECT * FROM Staff WHERE userName = ? AND password = ?";
//        ResultSet rs = null;
//        try (
//                PreparedStatement stmt = conn.prepareStatement(sql);) {
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                Employee bean = new Employee();
//
//                bean.setUserName(rs.getString("userName"));
//                bean.setPassword(rs.getString("password"));
//                bean.setEmail(rs.getString("email"));
//                bean.setFirstName(rs.getString("firstName"));
//                bean.setLastName(rs.getString("lastName"));
//                bean.setStaffId(rs.getString("staffId"));
//                bean.setImagePath(rs.getString("imagePath"));
//
//                return bean;
//            } else {
//                return null;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return null;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//        }
//
//    }
//
//    public static boolean insert(Employee bean) throws Exception {
//
//        String sql = "INSERT into Staff (userName, password) "
//                + "VALUES (?, ?)";
//        ResultSet keys = null;
//        try (
//                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
//
//            stmt.setString(1, bean.getUserName());
//            stmt.setString(2, bean.getPassword());
//            int affected = stmt.executeUpdate();
//
//            if (affected == 1) {
//                keys = stmt.getGeneratedKeys();
//                keys.next();
//                int newKey = keys.getInt(1);
//                bean.setId(newKey);
//            } else {
//                System.err.println("No rows affected");
//                return false;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return false;
//        } finally {
//            if (keys != null) {
//                keys.close();
//            }
//        }
//        return true;
//    }
//
//    public static boolean update(Employee bean) throws Exception {
//
//        String sql
//                = "UPDATE Staff SET "
//                + "userName = ?, password = ? "
//                + "WHERE staffId = ?";
//        try (
//                PreparedStatement stmt = conn.prepareStatement(sql);) {
//
//            stmt.setString(1, bean.getUserName());
//            stmt.setString(2, bean.getPassword());
//            stmt.setInt(3, bean.getId());
//
//            int affected = stmt.executeUpdate();
//            if (affected == 1) {
//                return true;
//            } else {
//                return false;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return false;
//        }
//
//    }
//
//}
