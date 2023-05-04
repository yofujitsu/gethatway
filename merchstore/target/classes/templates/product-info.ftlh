<#import "blocks/template.ftlh" as t>
<@t.template user>
    <p><h3 style="text-align: center">${product.title}</h3></p>
    <div class="row" style="justify-content: space-around">
        <div id="carouselExample" class="carousel slide" style="width: 50%">
            <div class="carousel-inner">
                <#list images as image>
                    <div class="carousel-item <#if image.previewImage>active</#if>">
                        <img src="/images/${image.id}" class="d-block w-35" alt="..." style="height: 700px">
                    </div>
                </#list>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <div class="card <#if product.size == "S">border-success mb-3</#if>" style="width: 5%; height: 20%">
            <div class="card-body">
                <p class="card-text"><b>S</b></p>
            </div>
        </div>
        <div class="card <#if product.size == "M">border-success mb-3</#if>" style="width: 5%; height: 20%">
            <div class="card-body">
                <p class="card-text"><b>M</b></p>
            </div>
        </div>
        <div class="card <#if product.size == "L">border-success mb-3</#if>" style="width: 5%; height: 20%">
            <div class="card-body">
                <p class="card-text"><b>L</b></p>
            </div>
        </div>
        <div class="card <#if product.size == "XL">border-success mb-3</#if>" style="width: 6%; height: 20%">
            <div class="card-body">
                <p class="card-text"><b>XL</b></p>
            </div>
        </div>
        <div class="card <#if product.size == "XXL">border-success mb-3</#if>" style="width: 7%; height: 20%">
            <div class="card-body">
                <p class="card-text"><b>XXL</b></p>
            </div>
        </div>
        <div class="card border-success" style="width: 20%; height: 20%">
            <div class="card-body">
                <h5 class="card-title">${product.price} ₽ <s>${product.price + product.price*0.5 - 0.5} ₽</s></h5>
                <form action="/cart/add/${product.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-outline-success">Добавить в корзину</button>
                </form>
                <p class="card-text">Доставим через <b>1-2</b> дня.</p>
            </div>
        </div>
        <br>
        ${product.description}
    </div>
    <hr>
    <p style="text-align: center">#gethatway! 2023</p>

</@t.template>
