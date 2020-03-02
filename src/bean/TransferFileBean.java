package bean;

public class TransferFileBean {
	private String fileName;
	private String status;
	private String source;
	private String target;
	
	public TransferFileBean() {
		super();
	}
	public TransferFileBean(String fileName, String status, String source, String target) {
		super();
		this.fileName = fileName;
		this.status = status;
		this.source = source + "\\" + fileName;
		this.target = target;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public String toString() {
		return "TransferFileBean [fileName=" + fileName + ", status=" + status + ", source=" + source + ", target="
				+ target + "]";
	}
	

}
