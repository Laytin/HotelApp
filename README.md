
# Хотелось бы озвучить пару моментов

Решена проблема 1+n для `GET /hotels` (через JPQL) и  `GET /hotels/search` (через CriteriaApi, следующий пункт).

---
Для поиска отелей/гостиниц был использован CriteriaApi, что позволяет выполнять поиск по одному или сразу по нескольким параметрам:  `/search?city=minsk&country=Belarus`. Также можно искать по множеству Amenities `/search?amenities=Free_WIFI,Free_PARKING`.

---
Для `/histogram/{param}` в `HistogramDAO` был написан кастомный запрос.

---
Чтобы избежать нагруженности кода, часть аннатоций контроллера была вынесена в интерфейсы. Там же описаны и аннотации к Swagger.

---
Конвертация сущностей в DTO объекты (и обратно) происходит с помощью класса `HotelMapper`, которому с конвертацией помогает [ModelMapper](https://modelmapper.org/).

---
Судя по примеру телу ответа в ТЗ, в роли entity существуют Address, Contacts и ArravalTime. Поэтому в проекте они реализованы как сущности.

Amenities - просто массив/список строк. Также по своей логике, Amenities, или "Теги", будут "стандартизированы" (т.к. мы планируем делать групповую статистику, то очевидно что пользователь не сможет "вручную" вводить имена *Amenities* ), поэтому в проекте они представлены в виде Enum. (enum в бд - зло, поэтому делаем колонку `amenity` типа `varchar`, а не `enum`)

---
При запросе списка всех отелей/гостиниц `GET /hotels` и в поиске `GET /hotels/search` отсутствует пагинация (следуя ТЗ), хотя полученный список может быть достаточно объемным.

---
По ТЗ, список Amenities можно только **заменить**, но не **изменить**. В перспективе я бы добавил возможность удалять\добавлять теги.

---

