package hello;

import hello.entity.User;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class UploadAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    HttpServletRequest request;
    public HttpServletResponse response;

    private File file;
    private String contentType;
    private String filename;


    public String execute() throws Exception {

    	//  セッションのユーザ情報取得
        //User user = getCurrentUser();

        //String realPath = ServletActionContext.getServletContext().getRealPath("/" + user.getId());

       // File mdir = new File(realPath);
       // mdir.mkdir();
//
//        try {
//
//            File filePath = new File(mdir,filename);
//            FileUtils.copyFile(file,filePath);
//            File img;
//            //セッションにパスを格納
//            //img.setUpload(filePath);
//
//            // エラーがあった場合は、スタックトレースを出力
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
        return "success";
    }


        private User getCurrentUser() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


		public void setUpload(File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        public void setUploadFileName(String filename) {
            this.filename = filename;
        }

        public String getFilename() {
            return filename;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }
        public String doUpload() throws Exception {
			File newdir = new File("C:\\Users\\AT11-長岡\\Desktop\\TEKITO\\Java_04\\WebContent\\TEMP\\Images\\id0");

        	if (newdir.mkdirs()){
        	      System.out.println("ディレクトリの作成に成功しました");
        	    }else{
        	      System.out.println("ディレクトリの作成に失敗しました");
        	    }
        	if (newdir.exists()) {
                System.out.println("ディレクトリは存在します。");
            } else {
                System.out.println("ディレクトリは存在しません。");
            }
        	return SUCCESS;
        }
      public String MakeDirectory() throws Exception
      {

    	  HttpServletRequest request = ServletActionContext.getRequest();
    	  System.out.println((String)request.getParameter("id")); // post/getパラメターの出力

    	  ServletContext con = ServletActionContext.getServletContext();
    	  con.getRealPath("."); // 物理パスの取得
    	  System.out.println((String)con.getRealPath(".")); // post/getパラメターの出力

    	  String path = con.getRealPath("/TEMP/Images");
    	  File newdir = new File(path + "/id1");
      	if (newdir.mkdirs()){
  	      System.out.println("ディレクトリの作成に成功しました");
  	    }else{
  	      System.out.println("ディレクトリの作成に失敗しました");
  	    }
	  	if (newdir.exists())
	  	{
	          System.out.println("ディレクトリは存在します。");
	    } else {
	      System.out.println("ディレクトリは存在しません。");
	    }
    	  return SUCCESS;
      }


}