package hello;
import hello.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class GetUsersAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public String errmsg;
    public String userId;
    public String password;
    public String box;


    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
		return users;
	}


	public String execute() throws Exception {
        this.sessionMap.put("userId", null);
        this.userId = "Struts2";


        return "success";
    }

    public String jsonSample() {
    	User user = new User();

    	user.setId(0);
    	user.setUserId("1");
    	user.setUserPass("2");
    	user.setDisplayName("3");

    	users.add(user);

    	user = new User();

    	user.setId(1);
    	user.setUserId("1");
    	user.setUserPass("2");
    	user.setDisplayName("3");

    	users.add(user);

    	return SUCCESS;
    }

    public String DBConection() throws Exception {

        String msg = "";
        User user;
        int count =0;
        String user_id;;
        String user_pass;
        String user_disp;

        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          System.out.printf("ドライバのロードに成功しましたGU");
        }catch (ClassNotFoundException e){
        	System.out.printf("ドライバのロードに失敗しました1");
        }catch (Exception e){
        	System.out.printf("ドライバのロードに失敗しました2");
        }
    	errmsg = "AAAAAAAAAA";
    	Connection conn = null;
    	String url = "jdbc:mysql://localhost/kdk";
    	String username = "root";
    	String password = "pb1313zero";
    	errmsg = "B2";
    	try{
    	  conn = DriverManager.getConnection(url, username, password);
    	  Statement stmt = (Statement) conn.createStatement();
    	  //更新
    	  String sql = "update m_user set v_user_pass='aaa' where v_user_id = 'a'";
    	  //追加
    	  //sql = "insert into m_user (v_user_id, v_user_pass) values ('b', 'bbb')";
    	  //削除
    	  //sql = "delete from m_user where v_user_id = 'b'";
          int num = stmt.executeUpdate(sql);
    	  sql = "SELECT * FROM m_user";
    	  ResultSet rs = stmt.executeQuery(sql);
    	  while(rs.next())
    	  {
    		user = new User();
		  	user_id = rs.getString("v_user_id");
		  	user_pass = rs.getString("v_user_pass");
		  	user_disp = rs.getString("v_name_ki");
		    user.setId(count);
	    	user.setUserId(user_id);
	    	user.setUserPass(user_pass);
	    	user.setDisplayName(user_disp);
	    	System.out.printf(user_id);
	    	users.add(user);
	    	count++;
    	  }
    	  errmsg = "BBBBBBBBBBBBBBB";
    	  // データベースに対する処理

    	}catch (SQLException e){
    	  // 例外処理
    		System.out.printf("setuzokuNO1");
    	}finally{
    	  try{
    	    if (conn != null){
    	    	//データーベース終了
    	      conn.close();
    	      System.out.printf("setuzokuOK");
    	      errmsg = "ログイン失敗しました";
    	    }
    	  }catch (SQLException e){
    	    // 例外処理
    		  System.out.printf("setuzokuNO2");
    		  errmsg = "DDDDDDDDDDDDDD";
    	  }
    	}


        return SUCCESS;
    }
}