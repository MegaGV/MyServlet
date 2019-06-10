package practice5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server {

    private static Connection getConnection()  throws Exception{
        String driverName = "com.mysql.jdbc.Driver";

        String url = "jdbc:mysql://localhost:3306/myservlet";
        String userName = "root";
        String password = "123456";

        Class.forName(driverName);

        return DriverManager.getConnection(url, userName, password);
    }
    private static Statement getStatement() throws Exception{
        Connection  connection = getConnection();
        return connection.createStatement();
    }

    private static String sort = "id";

    public static void main(String[] args) throws IOException {
        while(true) {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("wait client");
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

            String message = bufferedReader.readLine();


            switch(message){
                case "getTeachers":{
                    int page = Integer.valueOf(bufferedReader.readLine());
                    int num_every_page = Integer.valueOf(bufferedReader.readLine());
                    try {
                        Statement stmt = getStatement();
                        String sql = "select * from teachers order by " + sort + " limit "  + (page-1)*num_every_page + "," + num_every_page;
                        ResultSet resultSet = stmt.executeQuery(sql);
                        while(resultSet.next()) {
                            printWriter.println(resultSet.getString("id"));
                            printWriter.println(resultSet.getString("name"));
                            printWriter.println(resultSet.getString("college"));
                            printWriter.println(resultSet.getString("major"));
                            printWriter.println(resultSet.getInt("birthday"));
                            printWriter.println(resultSet.getInt("salary"));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
                case "findTeacherById": {
                    String id = bufferedReader.readLine();
                    try {
                        Statement stmt = getStatement();
                        String sql = "select * from teachers where id=" + id;
                        ResultSet resultSet = stmt.executeQuery(sql);
                        if (resultSet.next()) {
                            printWriter.println(resultSet.getString("id"));
                            printWriter.println(resultSet.getString("name"));
                            printWriter.println(resultSet.getString("college"));
                            printWriter.println(resultSet.getString("major"));
                            printWriter.println(resultSet.getInt("birthday"));
                            printWriter.println(resultSet.getInt("salary"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

                case "saveTeacher": {
                    String id = bufferedReader.readLine();
                    String name = bufferedReader.readLine();
                    String college = bufferedReader.readLine();
                    String major = bufferedReader.readLine();
                    String birthday = bufferedReader.readLine();
                    String salary = bufferedReader.readLine();
                    try {
                        Statement stmt = getStatement();
                        String sql = "update teachers set name=\"" + name + "\", college=\"" + college + "\", major=\"" +
                                major + "\", birthday=" + birthday + ", salary=" + salary +
                                " where id=" + id + "";
                        stmt.execute(sql);

                        System.out.println(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

                case "addTeacher": {
                    String id = bufferedReader.readLine();
                    String name = bufferedReader.readLine();
                    String college = bufferedReader.readLine();
                    String major = bufferedReader.readLine();
                    String birthday = bufferedReader.readLine();
                    String salary = bufferedReader.readLine();
                    try {
                        Statement stmt = getStatement();
                        String sql = "insert into teachers(id, name, college, major, birthday, salary) values(\"" +
                                id + "\", \"" + name + "\", \"" + college + "\", \"" +
                                major + "\", " + birthday + ", " + salary + ")";
                        stmt.execute(sql);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

                case "deleteTeacher": {
                    String del = "";
                    del = bufferedReader.readLine();
                    try {
                        while (!del.equals("")) {
                            Statement stmt = getStatement();
                            String sql = "delete from teachers where id=\"" + del + "\"";
                            stmt.execute(sql);

                            del = bufferedReader.readLine();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

                case "deleteAll": {
                    try {
                        Statement stmt = getStatement();
                        String sql = "delete from teachers";
                        stmt.execute(sql);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

                case "sort": {
                    String newsort = bufferedReader.readLine();
                    sort = newsort;
                }
                break;

                case "getAllID": {
                    try {
                        Statement stmt = getStatement();
                        String sql = "select id from teachers ";
                        ResultSet resultSet = stmt.executeQuery(sql);
                        while (resultSet.next())
                            printWriter.println(resultSet.getString(1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;

                case "size": {
                    try {
                        Statement stmt = getStatement();
                        String sql = "select COUNT(*) from teachers ";
                        ResultSet resultSet = stmt.executeQuery(sql);
                        if (resultSet.next())
                            printWriter.println(resultSet.getInt(1));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    printWriter.println(-1);
                }
                break;

                default:
                    break;
            }

        }

    }
}
