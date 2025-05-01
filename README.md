Правильная последовательность команд с остановкой и последующим запуском:

### **Шаг 1: Настройка и запуск инфраструктуры**

#### **1.1. Запуск контейнеров (первый раз)**
```bash
docker-compose up -d
```

#### **1.2. Проверка состояния**
```bash
docker-compose ps
```
Ожидаем статус `Up` у всех сервисов

#### **1.3. Остановка контейнеров (без удаления)**
```bash
docker-compose stop
```
Контейнеры останутся на системе, но будут остановлены

#### **1.4. Повторный запуск (после остановки)**
```bash
docker-compose start
```
Или полный перезапуск:
```bash
docker-compose up -d
```

#### **1.5. Полное удаление (когда точно нужно)**
```bash
docker-compose down
```
Удалит контейнеры, но сохранит данные в volumes

#### **1.6. Полная очистка (с удалением данных)**
```bash
docker-compose down -v
```

### 1.7. Пересобрать образ после мавен сборки
```bash
docker-compose up -d --build
```

### Посмотреть логи Kafka и отфильтровать их на ошибки:

### 1. Посмотреть логи Kafka (все сообщения)
```bash
docker-compose logs kafka
```

### 2. Отфильтровать логи на ошибки (основные варианты):

#### Вариант 1 - только ошибки:
```bash
docker-compose logs kafka | grep -i "error\|exception\|warn\|fail"
```

#### Вариант 2 - с контекстом (20 строк после ошибки):
```bash
docker-compose logs kafka | grep -A 20 -i "error\|exception\|warn\|fail"
```

#### Вариант 3 - конкретно ошибки запуска Kafka:
```bash
docker-compose logs kafka | grep -A 30 "KafkaServer id=1"
```

### 3. Если нужно посмотреть логи в реальном времени:
```bash
docker-compose logs -f kafka
```

### 4. Для проверки подключения к Zookeeper:
```bash
docker-compose logs kafka | grep -i "zookeeper"
```
### 5. Проверить список топиков в Kafka:
```bash
docker-compose exec kafka /opt/bitnami/kafka/bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```
---

### **Важно:**
1. `docker-compose stop` - только останавливает контейнеры (можно запустить через `start`)
2. `docker-compose down` - останавливает И удаляет контейнеры (но данные в volumes сохраняются)
3. Для повседневной работы используйте `stop`/`start`
