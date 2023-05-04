<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Shop now</h1><hr>
    <form method="get" action="/apparel">
        <div class="row">
                <div class="form-group col-md-2">
                    <label for="searchType">Категория</label>
                    <select id="searchType" class="form-control" name="searchType">
                        <option value="">Выбрать</option>
                        <option value="T-shirt">Футболка</option>
                        <option value="Longsleeve">Толстовка</option>
                        <option value="Hoodie">Худи</option>
                        <option value="Shoes">Кроссовки</option>
                        <option value="Pants">Брюки</option>
                    </select>
                </div>
                <br>
                <div class="form-group col-md-10">
                    <label for="searchWord">Поиск товаров</label>
                    <input type="text" name="searchWord" id="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control">
                    <br>
                </div>
            <button style="width: 100%" type="submit"class="btn btn-outline-success">Найти</button>
        </div>
    </form>
    <hr>
    <form method="get" action="/apparel">

    </form>
    <hr>
    <div class="row row-cols-1 row-cols-md-4 g-4">
            <#list products as product>
                <div class="col">
                    <a href="/product/${product.id}" class="product-link">
                        <div class="card text-black bg-white my-1 product">
                            <img src="/images/${product.previewImageId}"
                                 class="card-img-top">
                            <div class="card-body">
                                <h5 class="card-title">${product.title}</h5>
                                <p class="card-text">${product.price} ₽</p>
                                <p class="card-text">${product.category} | ${product.size}</p>
                            </div>
                            <div class="card-footer">
                                <#if user.email??>
                                    <form action="/cart/add/${product.id}" method="post">
                                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                        <button class="btn btn-outline-success" style=" width: 100%">Добавить в корзину</button>
                                    </form>
                                    <#else>
                                            <form action="/login" method="get" >
                                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                                <button class="btn btn-outline-success" style="width: 100%">Авторизируйтесь для покупок</button>
                                            </form>
                                </#if>
                            </div>
                        </div>
                    </a>
                </div>
            <#else>
                <h1 style="text-align: center">Не найдено</h1>
            </#list>
    </div>
</@t.template>

