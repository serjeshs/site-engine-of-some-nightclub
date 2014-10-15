package by.q64.promo.utils.mail;

public class EmailFactory {
	
	public static EmailAccount getPromo() {
		EmailAccount emailAccount = new EmailAccount();
		emailAccount.setEMAIL("afishasend@ladyka.tk");
		emailAccount.setLOGIN("afishasend@ladyka.tk");
		emailAccount.setPASSWORD("qweqwe");
		emailAccount.setSERVER("smtp.yandex.ru");
		emailAccount.setSMTPPORT("587");
		return emailAccount;
	}
}
