<img src="https://raw.githubusercontent.com/ad-riaz/SurfDeviceDresser/main/SurfDeviceDresser.jpg" alt="SurfDeviceDresser Logo">

Это консольное приложение для генерации обложек на девайсы студии Surf.

## Постановка проблемы
Для того чтобы качественно протестировать мобильные приложения, нужно иметь большой парк мобильных устройств. Чем их больше и чем они разнообразнее, тем больше конфигураций можно покрыть и, соответственно, качественнее протестировать приложение. Наличие большое парка устройств особенно полезно, когда много сотрудников и проектов.

Все девайсы отличаются своими характеристиками и физическими параметрами. И когда перед тобой, как перед тестировщиком, лежит несколько десятков девайсов (некоторые из которых могут быть одной модели и даже цвета), то тяжело быстро сообразить, какой именно девайс среди представленных нужен тебе для тестирования.

Для решения этой проблемы в студии Surf был использован подход с маркировкой каждого мобильного устройства. На спинку каждого девайса клеится стикер с его названием и версией операционной системы. Подобная информация дублируется и на экране в виде обоев.

Однако, со временем возник ряд проблем:
1. С развитием процессов тестирования в компании этой информации иногда становится не достаточно. Например, при нахождении багов на верстку требуется указывать разрешение и диагональ экрана.
2. Когда сотрудники берут устройства с собой для удаленной работы, может возникнуть путаница в том, какой именно девайс они забрали, если в студии есть несколько устройств одинаковой модели. Это может исправить уникальный идентификатор устройства.
3. Чем больше количество устройств используется в компании, тем труднозатратнее девайс-холдеру становится их поддерживать. Мало того, что ему необходимо следить за их техническим состоянием, обновлять версию ОС по необходимости, так если и после обновления необходимо обновлять обои для устройства. Сейчас девайс-холдер делает это руками в графическом редакторе.


## Цель создания приложения
Целью создания этого приложения было помочь девайс-холдеру в поддержке устройств и автоматизировать процесс создания обоев при обновлении старых девайсов и появлении новых.

## Требования к окружению
- JDK 17
- Apache Maven 3.9.7 


## Подготовка к работе
Все дальнейшие действия производить из-под корпоративной аккаунта в сервисах Google!

### Требования к таблице
Информация для генерации обложек для устройств берется из таблицы в Google Spreadsheets.  

Первая строка таблицы всегда обязана содержать заголовки столбцов!

Таблица должна иметь как минимум 7 столбцов в строго определенном порядке:  
- Производитель (Automatic, имя вендора)
- Устройство (Plain text, полное название устройства без имени вендора)
- Версия ОС (Automatic, версия операционной системы)
- Как указывать в поле Device (Plain text, короткое название девайса, другими словами уникальный идентификатор)
- Ширина экрана (Automatic, физическая ширина экрана в пикселях)
- Высота экрана (Automatic, физическая высота экрана в пикселях)
- Диагональ экрана (Number, диагональ экрана в дюймах)

Если столбцы будут перепутаны местами, то возникнут ошибки и сбои в работе программы.

В последующих столбцах может содержаться остальная необходимая информация (название оболочки, серийный номер, MAC-адрес и т.д.).

Таблица должна иметь строго три листа данных с четко определенными названиями:
- Android
- iOS
- iPadOS

Если в этих названиях будут допущены ошибки, то программа может завершить свою работу аварийно, или обложки для определенной ОС не будут сгенерированы.

### Настройка доступов к данным в таблице
Для доступа к таблице с данными данное приложение использует API Google. Прежде чем обращаться с ней, необходимо правильно настроить проект в Google Cloud.

1. Создать новый проект в Google Cloud (https://console.cloud.google.com/projectcreate?hl=ru)
2. В консоли Google Cloud включите API Google Sheets (https://console.cloud.google.com/flows/enableapi?apiid=sheets.googleapis.com&hl=ru)
3. Если вы используете новый проект Google Cloud для выполнения этого краткого руководства, настройте экран согласия OAuth и добавьте себя в качестве тестового пользователя. Если вы уже выполнили этот шаг для своего облачного проекта, перейдите к следующему разделу
4. В консоли Google Cloud выберите menu > API и службы > Экран согласия OAuth (https://console.cloud.google.com/apis/credentials/consent?hl=ru)
5. В качестве типа пользователя выберите «Внутренний» , затем нажмите «Создать»
6. Заполните форму регистрации приложения, затем нажмите « Сохранить и продолжить». На данный момент вы можете пропустить добавление областей и нажать «Сохранить и продолжить». В будущем, когда вы создадите приложение для использования за пределами вашей организации Google Workspace, вам необходимо изменить тип пользователя на Внешний, а затем добавить области авторизации, необходимые вашему приложению
8. Чтобы аутентифицировать конечных пользователей и получить доступ к пользовательским данным в вашем приложении, вам необходимо создать один или несколько идентификаторов клиентов OAuth 2.0. Для этого В консоли Google Cloud выберите menu > API и службы > Учетные данные (https://console.cloud.google.com/apis/credentials?hl=ru)
9. Нажмите «Создать учетные данные» > «Идентификатор клиента OAuth»
10. Нажмите Тип приложения > Приложение для ПК
11. В поле Имя введите имя учетных данных. Это имя отображается только в консоли Google Cloud
12. Нажмите Создать. Появится экран создания клиента OAuth, показывающий ваш новый идентификатор клиента и секрет клиента
13. Нажмите ОК. Вновь созданные учетные данные появятся в разделе «Идентификаторы клиентов OAuth 2.0»
14. Сохраните загруженный файл JSON как credentials.json и переместите его в свой рабочий каталог

### Развертывание приложения
Для того, чтобы развернуть приложение, воспользуйтесь алгоритмом ниже:

1. Склонировать проект в каталог на компьютере
2. Открыть этот каталог в IDE
3. Добавить конфигурацию запуска, указав в качестве главного класса класс App


### Добавление и настройка конфигураций
Для правильной работы приложения требуется добавить и настроить два файла конфигураций:

- application.properties (лежит в корне проекта)
- credentials.json (должен быть скачан ранее – см. п.14 в "Настройка доступов к данным в таблице")

Перед запуском приложения нужно подредактировать файл application.properties.  
Файл должен содержать переменную credentialsJsonPath со значением, которое содержит в себе полный путь до файла с кредами клиента (полный путь до credentials.json).  
Помимо этого в application.properties добавьте переменную spreadsheetId, которая должна содержать значение с id таблицы, откуда будут подтягиваться данные. Этот id можно найти в поисковой строке открытой гугл-таблицы.

### Запуск приложения
Теперь приложение готово к первому запуску.

Если никаких ошибок в процессе компиляции проекта не возникло, то на рабочем столе компьютера появится папка "SurfDeviceDresser", содержащая фоновые изображения для всех устройств из таблицы.

### Параметры проекта в application.properties
- backgroundPath – полный путь до фонового изображения для заставки (если оставить пустым, то будет использована дефолтная картинка)
- logoPath – полный путь до логотипа (если оставить пустым, то будет использована дефолтная картинка)
- logoWidthPercentage – какую ширину от заставки будет занимать логотип в процентном соотношении (по умолчанию = 80)
- yPosOnCanvas – Какой будет отступ логотипа от верха заставки в пикселях (по умолчанию 100)
- font – полный путь до файла шрифта с расширением .ttf (если оставить пустым, по умолчанию будет использован дефолтный шрифт)
- fontColor – цвет текста на заставке в формате RGB. Пример: 255,0,0 (если оставить пустым, по умолчанию будет использован белый)
- fontSize – размер шрифта (целое число, по умолчанию устанавливается значение 80)
- autoFontSizingEnabled – флаг, который переключает автоматическую установку размера шрифта в зависимости от размера устройства (по умолчанию устанавливается в состояние false)
- gapBetweenLines – Расстояние между строками текста. Также может быть адаптивным и может зависеть от флага autoFontSizingEnabled. (По умолчанию gapBetweenLines устанавливается равным 40)
- spreadsheetId – (обязательный) id гугл таблицы, из которой будут подтягиваться данные
- credentialsJsonPath – (обязательный) полный путь до файла с кредами для доступа программы к Google API
