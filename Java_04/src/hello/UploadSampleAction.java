package hello;

import hello.entity.FileList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

public class UploadSampleAction extends ActionSupport  {

	private String name;
	private File[] file;
    private String[] contentType;
    private String[] filename;
    private static int count = 0;
    private String fontest;
    private List<FileList> files = new ArrayList<FileList>();


      /**
	 * fontestを取得します。
	 * @return fontest
	 */
	public String getFontest() {
	    return fontest;
	}

	/**
	 * fontestを設定します。
	 * @param fontest fontest
	 */
	public void setFontest(String fontest) {
	    this.fontest = fontest;
	}

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

  	private static void count(File[] list) {
		for (File f : list) {
			if (f.isDirectory()) {
				count(f.listFiles());
			} else if (f.isFile()) {
				System.out.println(f.getName());
				count++;
			}
		}
	}
      // ファイルコピー
      public String FileUplo() throws Exception {

    	  //String x = file.getName();
    	  //x = file.getPath();
    	  for (int i = 0; i < file.length; i++) {
    		  FileList fileData = new FileList();
    		  fileData.setM_Id(i);
    		  files.add(fileData);
    	  }

    	  // ファイル保管庫 存在しない場合、フォルダ作成
    	  // ファイル保管庫の中に指定IDのフォルダ存在しない場合、フォルダ作成
    	  // ファイルコピー
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

  	  //
  	  FileUtils.copyFile(file[0],newdir2);

  	  // ファイル数取得
  	  File dir = new File(path + "/id0");
  	  //count(dir.listFiles());
  	  System.out.println(count);


    	  return SUCCESS;

      }
}
