package by.q64.promo.utils.mail;

public class EmailFactory {
	
	public static EmailAccount getPromo() {
		EmailAccount emailAccount = new EmailAccount();
		emailAccount.setEMAIL("afishasend@ladyka.tk");
		emailAccount.setLOGIN("afishasend@ladyka.tk");
		emailAccount.setPASSWORD("FUUCK123TheS90");
		emailAccount.setSERVER("smtp.yandex.com");
		emailAccount.setSMTPPORT("587");
		return emailAccount;
	}
}
