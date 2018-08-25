package by.ladyka.bepaid.dto;

/**
 * тип транзакции. По умолчанию - payment. Допустимые значения:
 */
public enum TransactionType {
	/**
	 * одновременное сочетание авторизации и списания средств
	 */
	payment,
	/**
	 * проверка банковской карты и резервирование суммы платежа
	 */
	authorization
}
