package hello;

import hello.entity.FileList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadSampleAction extends ActionSupport  {
    HttpServletRequest request;
    public HttpServletResponse response;
	private String name;

      private File[] file;
      private String[] contentType;
      private String[] filename;

      private List<FileList> files = new ArrayList<FileList>();

      public List<FileList> getFiles() {
  		return files;
  	}

      public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUpload(File[] file) {
         this.file = file;
      }

      public void setUploadContentType(String[] contentType) {
         this.contentType = contentType;
      }

      public void setUploadFileName(String[] filename) {
         this.filename = filename;
      }

      public String execute() {
         //

         return SUCCESS;
      }
      public String FileUplo() throws Exception {

    	  //String x = file.getName();
    	  //x = file.getPath();
    	  for (int i = 0; i < file.length; i++) {
    		  FileList fileData = new FileList();
    		  fileData.setM_Id(i);
    		  files.add(fileData);
    	  }
    	  String name = filename[0];
    	  HttpServletRequest request = ServletActionContext.getRequest();
    	  ServletContext con = ServletActionContext.getServletContext();
    	  String path = "D:/servlet-sample/upload/data";
    	  File newdir = new File(path + "/id0");
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
  	  File newdir2 = new File(path + "/id0/"+filename[0]);
  	  //File file = new File("D:/servlet-sample/upload/data/id0/test.txt");
  	 // FileWriter filewriter = new FileWriter(file);
  	 // filewriter.write("こんにちは");
  	 // filewriter.close();

  	FileUtils.copyFile(file[0],newdir2);


    	  return SUCCESS;

      }
}
