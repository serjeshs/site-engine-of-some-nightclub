package by.ladyka.bepaid.dto;

/**
 * https://docs.bepaid.by/ru/gateway/statuses
 */
public enum GatewayStatus {
	/**
	 * транзакция была обработана успешно
	 */
	successful,
	/**
	 * транзакция была обработана и отклонена шлюзом
	 */
	failed,
	/**
	 * транзакция не завершена, требуется участие торговца
	 */
	incomplete,
	/**
	 * время обработки транзакции истекло
	 */
	expired
}
