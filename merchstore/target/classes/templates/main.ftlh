<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мы - #gethatway! Style | Y2K | Streetwear</h1><hr>
    <h2 style="text-align: center">Новый бренд молодежной одежды, основанный в 2023 году.</h2><hr>
    <div id="carouselExampleIndicators" class="carousel slide">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://images.wallpaperscraft.ru/image/single/devushka_trava_gorod_213102_1280x720.jpg" class="d-block w-100">
                <div class="carousel-caption d-none d-md-block">
                    <a href="/apparel"><button type="button" class="btn btn-outline-success btn-lg">Каталог</button></a>
                </div>
            </div>
            <div class="carousel-item">
                <img src="https://images.wallpaperscraft.ru/image/single/devushka_listia_vetki_875709_1280x720.jpg" class="d-block w-100">
                <div class="carousel-caption d-none d-md-block">
                    <a href="/register"><button type="button" class="btn btn-outline-success btn-lg">Регистрация</button></a>
                </div>
            </div>
            <div class="carousel-item">
                <img src="https://images.wallpaperscraft.ru/image/single/devushki_hvost_veter_17096_1280x720.jpg" class="d-block w-100">
                <div class="carousel-caption d-none d-md-block">
                    <a href="/apparel?searchType=T-Shirt"><button type="button" class="btn btn-outline-success btn-lg">Распродажа</button></a>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
    <hr>
</@t.template>

