###Online Store. Tsel Anton

**Entity** — запросы к БД  
**DTO** — сущности, приходящие из БД и отправляющиеся в БД  
**DAO** — сущности с которыми работает программа и которые используются для View   
**View** — классы ввода-вывода через консоль  
**SessionController** — обработчик сессии, собственно, прослойка между View в Service (singleton)    
**Service** — бизнес-логика  
**ConnectionPool** — пул соединений с БД. Коллекция соединений, в данной реализации будет singleton, т.к. нагрузка ведь будет небольшая :)  
**Convector** — Конвертация из model в dto и обратно, а так же другие конвекторы