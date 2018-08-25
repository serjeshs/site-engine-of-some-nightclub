package by.ladyka.bepaid.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDateTime;

/**
 * https://docs.bepaid.by/ru/products#create-product
 */
@Getter
@Setter
public class ProductsRequestDto {
	/**
	 * название продукта
	 */
	private String name;
	/**
	 * описание продукта
	 */
	private String description;
	/**
	 * валюта
	 */
	private String currency;
	/**
	 * стоимость продукта в минимальных единицах
	 */
	private String amount;

	/**
	 * (необязательный) количество продуктов в наличии.
	 * Параметр не передается, если infinite установлен в true
	 */
	private String quantity;

	/**
	 * (необязательный) устанавливается true, если кол-во продуктов неограниченно.
	 * Например, если это виртуальный продукт
	 */
	private Boolean infinite;

	/**
	 * массив, который может содержать значения
	 * first_name, last_name, country, state, city,
	 * address, zip, phone, birth_date, email.
	 * Поля, указанные в массиве, отобразятся на странице платежа и будут обязательны для заполнения
	 */
	@JsonAlias("visible_fields")
	private String[] visibleFields;

	/**
	 *  true или false. Продукт будет тестовым, если значение true
	 */
	private Boolean test;

	/**
	 * true или false. Устанавливается в true, если время оплаты не ограничено.
	 */
	private Boolean immortal;

	/**
	 * (необязательный) дата и время, до которого может быть оплачен продукт.
	 * Формат: ISO 8601 вида YYYY-MM-DDThh:mm:ssTZD,
	 * где YYYY – год (например, 2019),
	 * MM – месяц (например, 02),
	 * DD – день (например, 09),
	 * hh – часы (например, 18),
	 * mm – минуты (например, 20),
	 * ss – секунды (например, 45),
	 * TZD – временная зона (+hh:mm или –hh:mm)
	 */
	@JsonAlias("expired_at")
	@JsonFormat(pattern = "YYYY-MM-DDThh:mm:ssTZD")
	private LocalDateTime expiredAt;

	/**
	 *  URL, на который будет перенаправлен клиент после завершения оплаты.
	 *  Если параметр не определен, клиент будет перенаправлен на URL магазина,
	 *  зарегистрированного с bePaid
	 */
	@JsonAlias("return_url")
	private URL returnUrl;

	/**
	 *  	Идентификатор магазина
	 */
	@JsonAlias("shop_id")
	private Long shopId;

	/**
	 * язык страницы оплаты.
	 * Допустимые значения:
	 * en - Английский
	 * es - Испанский
	 * tr - Турецкий
	 * de - Немецкий
	 * it - Итальянский
	 * ru - Русский
	 * zh - Китайский
	 * fr - Французский
	 * da - Датский
	 * sv - Шведский
	 * no - Норвежский
	 * fi - Финский
	 * pl - Польский
	 * ja - Японский
	 * be - Белорусский
	 */
	private Language language;

	/**
	 * тип транзакции. По умолчанию - payment. Допустимые значения:
	 *
	 * payment - одновременное сочетание авторизации и списания средств
	 * authorization - проверка банковской карты и резервирование суммы платежа
	 */
	@JsonAlias("transaction_type")
	private TransactionType transactionType;
}
