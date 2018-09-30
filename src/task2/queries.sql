#Задание: 1
#Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол. Вывести: model, speed и hd
SELECT model, speed, hd FROM pcs WHERE price < 500;
#Задание: 2
#Найдите производителей принтеров. Вывести: maker
SELECT distinct maker FROM products WHERE type='printer';
#Задание: 3
#Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает 1000 дол.
SELECT model, ram, screen FROM laptops WHERE price > 1000;
#Задание: 4
#Найдите все записи таблицы Printer для цветных принтеров.
SELECT * FROM printers WHERE color='y';
#Задание: 5
#Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену менее 600 дол.
SELECT model, speed, hd FROM pcs WHERE price < 600 AND (cd='12x' OR cd='24x');
# Задание: 6
# Укажите производителя и скорость для тех ПК-блокнотов, которые имеют жесткий диск объемом не менее 10 Гбайт.
SELECT p.maker, speed FROM pcs JOIN products p on pcs.model = p.model WHERE hd > 10;
# Задание: 7
# Найдите номера моделей и цены всех продуктов (любого типа), выпущенных производителем A (латинская буква).
SELECT * FROM (SELECT model, price FROM pcs
               UNION SELECT model, price FROM laptops
               UNION SELECT model, price FROM printers) AS a
WHERE a.model IN (SELECT model FROM products WHERE maker LIKE 'A%');
# Задание: 8
# Найдите производителя, выпускающего ПК, но не ПК-блокноты.
SELECT DISTINCT maker FROM products WHERE type='PC' AND maker NOT IN(SELECT maker FROM products WHERE type='laptop');
# Задание: 9
# Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker
SELECT distinct maker FROM products JOIN pcs p on products.model = p.model WHERE speed >= 450;
# Задание: 10
# Найдите принтеры, имеющие самую высокую цену. Вывести: model, price
SELECT model, price FROM printers JOIN (SELECT MAX(price) AS max_price FROM printers) AS mp WHERE price = mp.max_price;
# Задание: 11
# Найдите среднюю скорость ПК.
SELECT AVG(speed) AS average_speed FROM pcs;
# Задание: 12
# Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол.
SELECT AVG(speed) AS average_speed FROM laptops WHERE price > 1000;
# Задание: 13
# Найдите среднюю скорость ПК, выпущенных производителем A.
SELECT AVG(speed) AS average_speed FROM pcs join products p on pcs.model = p.model WHERE maker LIKE 'A%';
# Задание: 14
# Для каждого значения скорости найдите среднюю стоимость ПК с такой же скоростью процессора. Вывести: скорость, средняя цена
SELECT speed, AVG(price) FROM pcs GROUP BY speed HAVING AVG(price);
# Задание: 15
# Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD
# SELECT hdd, COUNT(hdd) FROM pc GROUP BY hdd HAVING COUNT(*)>1;
SELECT hd FROM pcs GROUP BY hd HAVING COUNT(*)>1;
# Задание: 16
# Найдите пары моделей PC, имеющих одинаковые скорость и RAM. В результате каждая пара указывается только один раз, т.е. (i,j), но не (j,i), Порядок вывода: модель с большим номером, модель с меньшим номером, скорость и RAM
SELECT p1.model, p2.model, p1.speed, p1.ram
          FROM pcs p1, pcs p2
          WHERE p1.speed = p2.speed
          AND p1.ram = p2.ram
          AND p1.model > p2.model;
# Задание: 17
# Найдите модели ПК-блокнотов, скорость которых меньше скорости любого из ПК.
# Вывести: type, model, speed
SELECT p.type, l.model, l.speed FROM laptops l JOIN products p on l.model = p.model WHERE l.speed  < (SELECT MIN(speed) FROM pcs);
# Задание: 18
# Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price
SELECT p.maker, price FROM printers pr
      JOIN (SELECT MIN(price) AS min_price FROM printers WHERE color='y') AS mp
      JOIN products p ON pr.model = p.model
      WHERE price = mp.min_price
      GROUP BY maker;
# Задание: 19
# Для каждого производителя найдите средний размер экрана выпускаемых им ПК-блокнотов. Вывести: maker, средний размер экрана.
SELECT maker, AVG(screen) AS average_screen FROM laptops l JOIN products p ON p.model = l.model GROUP BY maker;
# Задание: 20
# Найдите производителей, выпускающих по меньшей мере три различных модели ПК. Вывести: Maker, число моделей
SELECT maker, COUNT(model) AS quantity_models FROM products WHERE type='PC' GROUP BY maker HAVING COUNT(DISTINCT model) >= 3;
# Задание: 21
# Найдите максимальную цену ПК, выпускаемых каждым производителем. Вывести: maker, максимальная цена.
SELECT p.maker, MAX(price) AS max_price FROM pcs JOIN products p on pcs.model = p.model GROUP BY p.maker;
# Задание: 22
# Для каждого значения скорости ПК, превышающего 600 МГц, определите среднюю цену ПК с такой же скоростью. Вывести: speed, средняя цена.
SELECT speed, AVG(price) AS average_price FROM pcs WHERE speed > 600 GROUP BY speed;
# Задание: 23
# Найдите производителей, которые производили бы как ПК со скоростью не менее 750 МГц, так и ПК-блокноты со скоростью не менее 750 МГц. Вывести: Maker
SELECT * FROM (
          SELECT distinct maker FROM laptops l
          JOIN products prod on l.model = prod.model
          WHERE l.speed >= 750
            AND prod.maker NOT IN(SELECT maker FROM (SELECT * FROM laptops UNION SELECT * FROM pcs) l
                                  JOIN products p on l.model = p.model WHERE speed < 750)
            AND prod.maker IN(SELECT p2.maker from pcs join products p2 on pcs.model = p2.model)

          UNION SELECT distinct maker FROM pcs p
          JOIN products prod1 on prod1.model = p.model
          WHERE p.speed >= 750
            AND prod1.maker NOT IN(SELECT maker FROM (SELECT * FROM laptops UNION SELECT * FROM pcs) l
                                  JOIN products p on l.model = p.model WHERE speed < 750)
            AND prod1.maker IN(SELECT p2.maker from laptops join products p2 on laptops.model = p2.model)) AS a;

# Задание: 24
# Перечислите номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в базе данных продукции.
SELECT model FROM ( SELECT model, price FROM pcs
                    UNION SELECT model, price FROM laptops
                    UNION SELECT model, price FROM printers) AS a
WHERE price = (SELECT MAX(price) FROM (SELECT price FROM pcs
                     UNION SELECT price FROM laptops
                     UNION SELECT price FROM printers) b);
# Задание: 25
# Найдите производителей принтеров, которые производят ПК с наименьшим объемом RAM и с самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM. Вывести: Maker
SELECT maker FROM products
        WHERE model IN (SELECT model FROM pcs
                   WHERE (SELECT MIN(ram) FROM pcs
                          WHERE (SELECT MAX(speed) FROM pcs))
                     AND maker IN ( SELECT maker FROM products WHERE type='printer'));