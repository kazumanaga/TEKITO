package hello;

import hello.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoginAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public String state;
    public String errmsg;
    public String pattern;
    public String userId0;
    public String password;

    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
		return users;
	}

    public String execute() throws Exception {
    	//this.state = "out";
        this.sessionMap.put("userId0", null);
        this.sessionMap.put("password", null);
        this.userId0 = "";
        this.password = "";

        return "success";
    }

    public String loginCheck() throws Exception {

    	String msg = "";
        User user;
        int count =0;
        String user_id;;
        String user_pass;
        String user_disp;

        try {
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          System.out.printf("ドライバのロードに成功しましたLA");
        }catch (ClassNotFoundException e){

        }catch (Exception e){
        }

    	Connection conn = null;
    	String url = "jdbc:mysql://localhost/kdk";
    	String username = "root";
    	String password = "root";
    	errmsg = "B2";
    	try{
    	  conn = DriverManager.getConnection(url, username, password);
    	  Statement stmt = (Statement) conn.createStatement();
    	  //更新
    	  String sql = "SELECT * FROM m_user";

    	  ResultSet rs = stmt.executeQuery(sql);
    	  while(rs.next())
    	  {
    		user = new User();
		  	user_id = rs.getString("v_user_id");
		  	user_pass = rs.getString("v_user_pass");
		  	user_disp = rs.getString("v_user_kanjiName");
	    	user.setUserId(user_id);
	    	user.setUserPass(user_pass);
	    	user.setDisplayName(user_disp);
	    	users.add(user);
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

    	      errmsg = "ログイン";
    	    }
    	  }catch (SQLException e){
    	    // 例外処理
    	  }
    	}

    	for(int i=0;i<users.size();i++)
    	{
    		user = users.get(i);

    		if(this.userId0.equals(user.getUserId()) && this.password.equals(user.getUserPass()))
    		{
    			this.errmsg = "ログイン成功しました";
    			this.pattern = "ok";
    			this.state = "in";

    			return SUCCESS;
    		}

    	}
    	this.errmsg = "ログイン失敗しました!!";
    	this.pattern = "err";
    	this.state = null;
    	return ERROR;
    }

	public String loginOut() throws Exception {

		this.errmsg = "ログアウトしました";
		this.pattern = "out";
		this.state = null;
		return SUCCESS;
    }


	public String test() throws Exception {

        return "success";
    }
}
