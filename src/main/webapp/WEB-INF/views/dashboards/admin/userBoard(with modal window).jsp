<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <%--------------------------------------------------%>
    <!-- стили демо страницы -->
    <link rel="stylesheet" href="/resources/dashboards/assets/css/demo-page.css" rel="stylesheet">
    <!-- стили модального окна -->
    <link rel="stylesheet" href="/resources/dashboards/assets/css/style-modal.css" rel="stylesheet">
</head>

<body>
<div class="container">

    <!-- блок кнопок на вызов модальных окон -->
    <div style="text-align: center;">
        <a href="#win1" class="button button-green">Открыть окно 1</a>
    </div>
    <!-- Модальное окно 1 -->
    <a href="#x" class="overlay" id="win1"></a>
    <div class="popup">
        <a href="http://webformyself.com/aff/dobrovoi/practicerubber"><img src="http://dbmast.ru/wp-content/uploads/2012/11/DVD009.jpg" width="190" height="252" alt="Практический курс резиновой верстки" style="float:left;margin:5px 10px 5px 0; border:0; " /></a>
        <h2>Уважаемые друзья!</h2>
        Обращаю Ваше внимание, что <strong>27</strong> ноября, мои коллеги из команды <strong>WebForMySelf</strong> запустили абсолютно новый обучающий курс:

        <a class="close" title="Закрыть" href="#close"></a>
    </div>
</div>
</body>
</html>
