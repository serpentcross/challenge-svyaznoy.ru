-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Окт 10 2016 г., 20:53
-- Версия сервера: 5.6.21
-- Версия PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `svyaznoy`
--

-- --------------------------------------------------------

--
-- Структура таблицы `message`
--

CREATE TABLE IF NOT EXISTS `message` (
`id` bigint(20) NOT NULL,
  `phnum` varchar(11) NOT NULL,
  `msgdate` bigint(20) NOT NULL,
  `status` varchar(30) NOT NULL,
  `msgtxt` varchar(500) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `message`
--

INSERT INTO `message` (`id`, `phnum`, `msgdate`, `status`, `msgtxt`) VALUES
(1, '79012312321', 1460073140, 'Отправлено', 'Привет!'),
(2, '79012312321', 1460073170, 'Не отправлено', 'Привет!'),
(3, '79058383838', 1467922719, 'Отправлено', 'sdf'),
(4, '79058383838', 1467922721, 'Отправлено', 'sdf'),
(5, '79058383838', 1467922723, 'Отправлено', 'sdf'),
(6, '79058383838', 1467922724, 'Не отправлено', 'sdf'),
(7, '79058383838', 1467922731, 'Не отправлено', 'sdf'),
(8, '79034343434', 1467923018, 'Не отправлено', 'fddff'),
(9, '79034343434', 1467923021, 'Отправлено', 'fddff'),
(10, '79031445322', 1475429081, 'Отправлено', 'bbcxb'),
(11, '79031445322', 1475429103, 'Отправлено', '45546jbkjkjhkjhjkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj'),
(12, '79031445322', 1475429104, 'Не отправлено', '45546jbkjkjhkjhjkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `message`
--
ALTER TABLE `message`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `message`
--
ALTER TABLE `message`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
