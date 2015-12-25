package hello;

import hello.entity.FileList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class UploadAction extends AbstractAction {

    private static final long serialVersionUID = 1L;
    private File file;
    private String contentType;
    private String filename;
    private Integer m_Num;
    private String param;
    private List<FileList> ImageFile = new ArrayList<FileList>();

    public String execute() throws Exception {
        return "success";
    }
	public List<FileList> getImageFile() {
  		return ImageFile;
  	}

	public Integer getM_Num() {
	    return m_Num;
	}

	public void setM_Num(Integer m_Num) {
	    this.m_Num = m_Num;
	}

	public String getParam() {
	    return param;
	}

	public void setParam(String param) {
	    this.param = param;
	}

	// ファイル数取得
    public String GetImageFileNum() throws Exception {
    	setM_Num(8);
        return "success";
    }
	// ファイル数取得
    public String GetImageFileName() throws Exception {
    	FileList fileList;
    	fileList = new FileList();
    	fileList.setM_filename("YAhooooooo!");
    	fileList.setM_contentType("jpg");
    	fileList.setM_Id(0);
    	ImageFile.add(fileList);
        return "success";
    }
}