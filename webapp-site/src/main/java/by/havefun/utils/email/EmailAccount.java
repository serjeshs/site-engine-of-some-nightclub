package by.havefun.utils.email;

public class EmailAccount {
	
	private String EMAIL;
	private String LOGIN;
	private String PASSWORD;
	private String SERVER;
	private String EmailManager;
	private String SMTPPORT;
	
	
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getLOGIN() {
		return LOGIN;
	}
	public void setLOGIN(String lOGIN) {
		LOGIN = lOGIN;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getSERVER() {
		return SERVER;
	}
	public void setSERVER(String sERVER) {
		SERVER = sERVER;
	}
	
	public String getEmailManager() {
		return EmailManager;
	}
	public void setEmailManager(String emailManager) {
		EmailManager = emailManager;
	}
	public String getSMTPPORT() {
		return SMTPPORT;
	}
	public void setSMTPPORT(String sMTPPORT) {
		SMTPPORT = sMTPPORT;
	}
}
