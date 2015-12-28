package hello;

import hello.entity.FileList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
public class UploadAction extends AbstractAction {

    private static final long serialVersionUID = 1L;
    private File[] fileOs;
    private String[] contentType;
    private String[] filename;
    private Integer m_Num;
    private Integer m_fileNum;
    private String param;
    private List<FileList> ImageFile = new ArrayList<FileList>();
    private static int s_count = 0;

    public String execute() throws Exception {
        return "success";
    }


	public void setFileOs(File[] fileOs) {
	    this.fileOs = fileOs;
	}

	public String[] getFileOsContentType() {
	    return contentType;
	}

	public void setFileOsContentType(String[] contentType) {
	    this.contentType = contentType;
	}

	public String[] getFileOsFileName() {
	    return filename;
	}

	public void setFileOsFileName(String[] filename) {
	    this.filename = filename;
	}

	public Integer getM_Num() {
	    return m_Num;
	}

	public void setM_Num(Integer m_Num) {
	    this.m_Num = m_Num;
	}
	/**
	 * m_fileNumを取得します。
	 * @return m_fileNum
	 */
	public Integer getM_fileNum() {
	    return m_fileNum;
	}


	/**
	 * m_fileNumを設定します。
	 * @param m_fileNum m_fileNum
	 */
	public void setM_fileNum(Integer m_fileNum) {
	    this.m_fileNum = m_fileNum;
	}


	public String getParam() {
	    return param;
	}

	public void setParam(String param) {
	    this.param = param;
	}
	public List<FileList> getImageFile() {
		return ImageFile;
	}

	public void setImageFile(List<FileList> ImageFile) {
	    this.ImageFile = ImageFile;
	}
  	private static void count(File[] list) {
		for (File f : list) {
			if (f.isDirectory()) {
				count(f.listFiles());
			} else if (f.isFile()) {
				System.out.println(f.getName());
				s_count++;
			}
		}
	}
  	 private static void delete(File f)
     {
         /*
          * ファイルまたはディレクトリが存在しない場合は何もしない
          */
         if(f.exists() == false) {
             return;
         }

         if(f.isFile()) {
             /*
              * ファイルの場合は削除する
              */
             f.delete();

         } else if(f.isDirectory()){

             /*
              * ディレクトリの場合は、すべてのファイルを削除する
              */

             /*
              * 対象ディレクトリ内のファイルおよびディレクトリの一覧を取得
              */
             File[] files = f.listFiles();

             /*
              * ファイルおよびディレクトリをすべて削除
              */
             for(int i=0; i<files.length; i++) {
                 /*
                  * 自身をコールし、再帰的に削除する
                  */
                 delete( files[i] );
             }
             /*
              * 自ディレクトリを削除する
              */
             f.delete();
         }
     }



	// ファイル数取得
    public String GetImageFileNum() throws Exception {
    	String path = "";
    	String userIdNumber = "id_"+m_Num;

    	if(m_Num == null)
    	{
    		return "success";
    	}
    	// C:/TestTempがあるかチェック
    	path = "C:/TestTemp";
    	File maindir = new File(path);
    	if (maindir.mkdirs()){
	      System.out.println("TestTempディレクトリの作成に成功しました");
	    }else{
	      System.out.println("TestTempディレクトリの作成に失敗しました");
	    }
	  	if (maindir.exists())
	  	{
	          System.out.println("TestTempディレクトリは存在します。");
	    } else {
	      System.out.println("TestTempディレクトリは存在しません。");
	    }

    	// C:/TestTemp/dataがあるかチェック
    	path = "C:/TestTemp/data";
    	File datadir = new File(path);
    	if (datadir.mkdirs()){
	      System.out.println("dataディレクトリの作成に成功しました");
	    }else{
	      System.out.println("dataディレクトリの作成に失敗しました");
	    }
	  	if (datadir.exists())
	  	{
	          System.out.println("dataディレクトリは存在します。");
	    } else {
	      System.out.println("dataディレクトリは存在しません。");
	    }

    	// C:/TestTemp/data/id_xxxがあるかチェック
    	path = "C:/TestTemp/data/"+userIdNumber;
    	File iddir = new File(path);
    	if (iddir.mkdirs()){
	      System.out.println("dataディレクトリの作成に成功しました");
	    }else{
	      System.out.println("dataディレクトリの作成に失敗しました");
	    }
	  	if (iddir.exists())
	  	{
	          System.out.println("dataディレクトリは存在します。");
	    } else {
	      System.out.println("dataディレクトリは存在しません。");
	    }
    	// IDからアップロードディレクトリにアクセス
	  	s_count = 0;
	  	count(iddir.listFiles());
	  	setM_Num(s_count);
        return "success";
    }
	// ファイル名取得
    public String GetImageFileName() throws Exception {
    	String path = "";
    	String userIdNumber = "id_"+m_Num;
    	if(m_Num == null)
    	{
    		return "success";
    	}
    	path = "C:/TestTemp/data/"+userIdNumber;
    	File iddir = new File(path);
    	System.out.println(m_Num);
    	if (iddir.mkdirs()){
	      System.out.println("dataディレクトリの作成に成功しました");
	    }else{
	      System.out.println("dataディレクトリの作成に失敗しました");
	    }
	  	if (iddir.exists())
	  	{
	          System.out.println("dataディレクトリは存在します。");
	    } else {
	      System.out.println("dataディレクトリは存在しません。");
	    }
    	// IDからアップロードディレクトリにアクセス
	  	s_count = 0;
	  	count(iddir.listFiles());
	  	File[] files = iddir.listFiles();
  	  for (int i = 0; i < s_count; i++)
  	  {
      	FileList fileList;
      	fileList = new FileList();
      	fileList.setM_filename(files[i].getName());
      	fileList.setM_contentType("jpg");
      	fileList.setM_Id(i);
      	ImageFile.add(fileList);
  	  }

        return "success";
    }


	public String workTest() throws Exception {
    	String path = "";
    	String userIdNumber = "id_"+m_fileNum;
    	path = "C:/TestTemp/data/"+userIdNumber;
    	File iddir = new File(path);
    	if (iddir.mkdirs()){
	      System.out.println("taディレクトリの作成に成功しました");
	    }else{
	      System.out.println("taディレクトリの作成に失敗しました");
	    }
	  	if (iddir.exists())
	  	{
	          System.out.println("taディレクトリは存在します。");
	    } else {
	      System.out.println("taディレクトリは存在しません。");
	    }
	  	System.out.println(filename[0]);
  	  for (int i = 0; i < fileOs.length; i++) {
  		File dir = new File(path+"/"+filename[i]);
  		FileUtils.copyFile(fileOs[i],dir);
  	  }

        return "success";
    }
	public String DeleteFile() throws Exception {
    	String path = "";
    	String userIdNumber = "id_"+m_Num;
    	path = "C:/TestTemp/data/"+userIdNumber+"/"+param;
    	File iddir = new File(path);

	  	if (iddir.exists())
	  	{
	          System.out.println("ファイルは存在します。");
	          iddir.delete();
	    } else {
	      System.out.println("ファイルは存在しません。");
	    }
        return "success";
    }

	public String DeleteDirectory() throws Exception {
    	String path = "";
    	String userIdNumber = "id_"+m_Num;
    	path = "C:/TestTemp/data/"+userIdNumber+"/";
    	File iddir = new File(path);
    	delete(iddir);

	  	if (iddir.exists())
	  	{
	          System.out.println("AAAAAAAAAAAAAAAAAAAAAAディレクトリは存在します。");
	          iddir.delete();
	    } else {
	      System.out.println("ディレクトリは存在しません。");
	    }
        return "success";
    }
}