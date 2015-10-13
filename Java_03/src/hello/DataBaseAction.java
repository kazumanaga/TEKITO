package hello;

import hello.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
		return users;
	}
    private String testcode;
    private String testNumber;
    private String UserName;
    private String UserPass;
    private String DispName;

    public String getTestcode() {
        return testcode;
    }

    public void setTestcode(String testcode) {
        this.testcode = testcode;
    }

    public String getTestNumber() {
        return testNumber;
    }
    public void setTestNumber(String testNumber) {
        this.testNumber = testNumber;
    }
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getUserPass() {
        return UserPass;
    }

    public void setUserPass(String UserPass) {
        this.UserPass = UserPass;
    }
    public String getDispName() {
        return DispName;
    }

    public void setDispName(String DispName) {
        this.DispName = DispName;
    }

    public String execute() throws Exception {

        return "success";
    }

    public String DataBaseAdd() throws Exception {

    	System.out.printf(testcode);
    	try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.printf("ドライバのロードに成功しましたA");
          }catch (ClassNotFoundException e){
          	System.out.printf("ドライバのロードに失敗しました1");
          }catch (Exception e){
          	System.out.printf("ドライバのロードに失敗しました2");
          }
      	Connection conn = null;
      	String url = "jdbc:mysql://localhost/kdk";
      	String username = "root";
      	String password = "pb1313zero";
      	try{
      	  conn = DriverManager.getConnection(url, username, password);
      	  Statement stmt = (Statement) conn.createStatement();
      	  String sql;
      	  //追加
    	  sql = "insert into m_user (v_user_id, v_user_pass, v_name_ki) values ('"+UserName+"', '"+UserPass+"', '"+DispName+"')";
      	  //sql = "insert into m_user (v_user_id, v_user_pass,v_name_ki) values ('z', 'zzz','zzzz')";
          int num = stmt.executeUpdate(sql);

      	  // データベースに対する処理

      	}catch (SQLException e){
      	  // 例外処理
      		System.out.printf("NOOOOOO1");
      	}finally{
      	  try{
      	    if (conn != null){
      	    	//データーベース終了
      	      conn.close();
      	    }
      	  }catch (SQLException e){
      	    // 例外処理
      		System.out.printf("NOOOOOO2");
      	  }
      	}
    	return SUCCESS;
    }

    public String DataBaseEdit() throws Exception {

    	System.out.printf(testcode);
    	try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.printf("ドライバのロードに成功しましたU");
          }catch (ClassNotFoundException e){
          	System.out.printf("ドライバのロードに失敗しました1");
          }catch (Exception e){
          	System.out.printf("ドライバのロードに失敗しました2");
          }
      	Connection conn = null;
      	String url = "jdbc:mysql://localhost/kdk";
      	String username = "root";
      	String password = "pb1313zero";
      	try{
      	  conn = DriverManager.getConnection(url, username, password);
      	  Statement stmt = (Statement) conn.createStatement();
      	  String sql;
      	System.out.printf(UserName);
      	  //追加
      	 sql = "update m_user set v_user_pass='"+ UserName + "',v_user_pass='"+ UserPass +"',v_name_ki='"+ DispName +"' where v_user_id = '"+testcode+"'";
          int num = stmt.executeUpdate(sql);

      	  // データベースに対する処理

      	}catch (SQLException e){
      	  // 例外処理
      		System.out.printf("NOOOOOO1");
      	}finally{
      	  try{
      	    if (conn != null){
      	    	//データーベース終了
      	      conn.close();
      	    }
      	  }catch (SQLException e){
      	    // 例外処理
      		System.out.printf("NOOOOOO2");
      	  }
      	}
    	return SUCCESS;
    }

    public String DataBaseDelete() throws Exception {

    	System.out.printf(testcode);
    	try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.printf("ドライバのロードに成功しましたD");
          }catch (ClassNotFoundException e){
          	System.out.printf("ドライバのロードに失敗しました1");
          }catch (Exception e){
          	System.out.printf("ドライバのロードに失敗しました2");
          }
      	Connection conn = null;
      	String url = "jdbc:mysql://localhost/kdk";
      	String username = "root";
      	String password = "pb1313zero";
      	try{
      	  conn = DriverManager.getConnection(url, username, password);
      	  Statement stmt = (Statement) conn.createStatement();
      	  String sql;
      	  //削除
      	  sql = "delete from m_user where v_user_id = '"+testcode+"'";
      	  //ResultSet rs = stmt.executeQuery(sql);
      	  int result = stmt.executeUpdate(sql);
      	  System.out.println("更新件数は" + result + "です。");

      	  // データベースに対する処理

      	}catch (SQLException e){
      	  // 例外処理
      		System.out.printf("NOOOOOO1");
      	}finally{
      	  try{
      	    if (conn != null){
      	    	//データーベース終了
      	      conn.close();
      	    }
      	  }catch (SQLException e){
      	    // 例外処理
      		System.out.printf("NOOOOOO2");
      	  }
      	}


          return SUCCESS;
    }

}
