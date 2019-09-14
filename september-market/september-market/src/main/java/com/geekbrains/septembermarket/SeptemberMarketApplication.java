package com.geekbrains.septembermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeptemberMarketApplication {
	// Домашнее задание:
	// 1. Сделать добавление товаров (желательно минимальными усилиями)
	// 2. Допилить постраничную навигацию
	// 3. Сделать выбор размера страницы (10 тов/стр, 25 тов/стр, ...)
	// *. Оптимизировать код контроллера с фильтром

	public static void main(String[] args) {
		SpringApplication.run(SeptemberMarketApplication.class, args);
	}
}
