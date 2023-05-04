<#import "blocks/template.ftlh" as t>
<@t.template user>
    <#if user.isAdmin() == false>
        <h1 style="text-align: center">Доступ для обычных пользователей запрещен</h1>
        <hr>
    </#if>
    <#if user.isAdmin()>
        <a class="btn btn-success mt-3 mb-3" style="text-align: center; width: 100%"
           data-bs-toggle="collapse" href="#collapseExample" role="button"
           aria-expanded="false" aria-controls="collapseExample">
            Добавить новый товар
        </a>
        <div class="collapse mb-3" id="collapseExample" style="">
            <form action="/product/create" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="productName">Название товара</label>
                    <input type="text" required class="form-control"
                           id="productName" name="title">
                </div>
                <div class="form-group">
                    <label for="price">Цена товара в рублях</label>
                    <div class="input-group mb-3">
                        <input type="number" required class="form-control" id="price" name="price">
                        <div class="input-group-append">
                            <span class="input-group-text">₽</span>
                        </div>
                    </div>
                </div>
                <div class="input-group mb-3">
                    <label class="input-group-text" for="searchType">Категория</label>
                    <select id="searchType" required name="category" class="form-select">
                        <option selected>Выбрать</option>
                        <option value="T-shirt">Футболка</option>
                        <option value="Longsleeve">Толстовка</option>
                        <option value="Hoodie">Худи</option>
                        <option value="Shoes">Кроссовки</option>
                        <option value="Pants">Брюки</option>
                    </select>
                </div>
                <div class="input-group mb-3">
                    <label class="input-group-text" for="searchSize">Размер</label>
                    <select required class="form-select"  name="size" id="searchSize">
                        <option selected>Выбрать</option>
                        <option value="S">S</option>
                        <option value="M">M</option>
                        <option value="L">L</option>
                        <option value="XL">XL</option>
                        <option value="XXL">XXL</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="description">Описание товара</label>
                    <textarea required class="form-control" id="description" name="description"></textarea>
                </div>
                <div class="form-group">
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Загрузка первой фотографии</label>
                        <input required class="form-control" type="file" id="formFile" name="file1">
                    </div>
                </div>
                <div class="form-group">
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Загрузка второй фотографии</label>
                        <input class="form-control" type="file" id="formFile" name="file2">
                    </div>
                </div>
                <div class="form-group">
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Загрузка третьей фотографии</label>
                        <input class="form-control" type="file" id="formFile" name="file3">
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>

                <button type="submit" style="width: 100%" class="btn btn-success">Опубликовать</button>
            </form>
        </div>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <#list products as product>
                <div class="col">
                    <a href="/product/${product.id}" class="product-link">
                        <div class="card text-black bg-white my-3 product">
                            <div class="card-top">
                                <form action="/product/delete/${product.id}" method="post">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <button type="submit" class="btn btn-danger m-3">
                                        Удалить
                                    </button>
                                </form>
                            </div>
                            <img src="/images/${product.previewImageId}"
                                 class="card-img-top">
                            <div class="card-body">
                                <h5 class="card-title">${product.title}</h5>
                                <p class="card-text">${product.price} ₽</p>
                            </div>
                            <div class="card-footer">
                                ${product.category} | ${product.size}
                            </div>
                        </div>
                    </a>
                </div>
            <#else>
                <h1 style="text-align: center">Не найдено</h1>
            </#list>
        </div>
    </#if>
</@t.template>