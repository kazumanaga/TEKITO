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
    private Integer UserNo;
    private String testNumber;
    private String UserName;
    private String UserPass;
    private String DispName;

    public Integer getUserNo() {
        return UserNo;
    }

    public void setUserNo(Integer userNo) {
        this.UserNo = userNo;
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

    // 追加
    public String DataBaseAdd() throws Exception {

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
      	String password = "root";
      	try{
      	  conn = DriverManager.getConnection(url, username, password);
      	  Statement stmt = (Statement) conn.createStatement();
      	  String sql;
      	  //追加
    	  sql = "insert into m_user (v_user_no,v_user_id, v_user_pass, v_user_kanjiName) values ('"+UserNo+"','"+UserName+"', '"+UserPass+"', '"+DispName+"')";
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
    // 更新
    public String DataBaseEdit() throws Exception {

    	try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.printf("ドライバのロードに成功しましたU");
          }catch (ClassNotFoundException e){

          }catch (Exception e){

          }
      	Connection conn = null;
      	String url = "jdbc:mysql://localhost/kdk";
      	String username = "root";
      	String password = "root";
      	try{
      	  conn = DriverManager.getConnection(url, username, password);
      	  Statement stmt = (Statement) conn.createStatement();
      	  String sql;
      	  //追加
      	 sql = "update m_user set v_user_id='"+ UserName + "',v_user_pass='"+ UserPass +"',v_user_kanjiName='"+ DispName +"' where v_user_no = '"+UserNo+"'";
          int num = stmt.executeUpdate(sql);

      	  // データベースに対する処理

      	}catch (SQLException e){
      	  // 例外処理
      	}finally{
      	  try{
      	    if (conn != null){
      	    	//データーベース終了
      	      conn.close();
      	    }
      	  }catch (SQLException e){
      	    // 例外処理
      	  }
      	}
    	return SUCCESS;
    }
    // 削除
    public String DataBaseDelete() throws Exception {

    	try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.printf("ドライバのロードに成功しましたD");
          }catch (ClassNotFoundException e){

          }catch (Exception e){

          }
      	Connection conn = null;
      	String url = "jdbc:mysql://localhost/kdk";
      	String username = "root";
      	String password = "root";
      	try{
      	  conn = DriverManager.getConnection(url, username, password);
      	  Statement stmt = (Statement) conn.createStatement();
      	  String sql;
      	  //削除
      	  sql = "delete from m_user where v_user_no = '"+UserNo+"'";
      	  //ResultSet rs = stmt.executeQuery(sql);
      	  int result = stmt.executeUpdate(sql);

      	  // データベースに対する処理

      	}catch (SQLException e){
      	  // 例外処理
      	}finally{
      	  try{
      	    if (conn != null){
      	    	//データーベース終了
      	      conn.close();
      	    }
      	  }catch (SQLException e){
      	    // 例外処理
      	  }
      	}

          return SUCCESS;
    }

}
