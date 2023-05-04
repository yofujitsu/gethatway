<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1>Корзина</h1>
    <#if cart?has_content>
        <table class="table table-hover table-white">
            <thead>
            <tr>
                <th scope="col">Id товара</th>
                <th scope="col"></th>
                <th scope="col">Название товара</th>
                <th scope="col">Количество</th>
                <th scope="col">Цена, руб (шт).</th>
                <th scope="col">Итого, руб.</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <#if cart?has_content>
                <#list cart as cartItem>
                    <tr>
                        <td>${cartItem.productId}</td>
                        <td><img src="/images/${cartItem.curr_product.previewImageId}" style="height: 100px"></td>
                        <td>${cartItem.curr_product.title}</td>
                        <td>${cartItem.quantity}</td>
                        <td>${cartItem.curr_product.price}</td>
                        <td>${cartItem.curr_product.price * cartItem.quantity}</td>
                        <td>
                            <form method="post" action="/cart/remove/${cartItem.productId}">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn btn-danger m-3">Удалить товар из корзины</button>
                            </form>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
        <br>
        <h3>Итого: ${cartPrice} руб.</h3>
        <form action="/cart/order" method="POST">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-success">Оформить заказ</button>
        </form>
        <#else><p>Корзина пуста</p>
    </#if>

</@t.template>