<#macro template user>
    <!doctype html>
    <html lang="ru">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/style.css">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
              crossorigin="anonymous">

        <link rel="icon" href="https://sun9-28.userapi.com/impg/GMkj5OS8LFf_3N4qvpuTNJwNTnNwgoiKy-lraw/ayFQMqtqou4.jpg?size=1500x1500&quality=95&sign=5c95795fea6d397bec41911859057cc1&type=album"><!-- 32×32 -->

        <title>#gethatway!</title>
    </head>
    <body>
    <nav class="navbar bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/" style="text-decoration: none; color: #fff">
                #gethatway!
            </a>
            <#if user.email??>
                <div class="dropdown text-end">
                    <a href="/cart">
                        <img src="https://gtiopt.ru/files/storage/basket1.svg" alt="mdo"
                             width="64" height="64">
                    </a>
                </div>
                <div class="dropdown text-end">
                    <a href="/profile">
                        <img src="/static/images/avatar.jpg" alt="mdo"
                             width="64" height="64" class="rounded-circle">
                    </a>
                </div>
            <#else>
                <button class="btn btn-outline-success" type="button"
                        onclick="window.location.href = '/login'">
                    Вход / Регистрация
                </button>
            </#if>
        </div>
    </nav>
    <div class="container mt-4">
        <#nested/>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
            integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
            integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
            crossorigin="anonymous"></script>
    </body>
    </html>
</#macro>