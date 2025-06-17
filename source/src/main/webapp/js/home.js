'use strict'
document.addEventListener('DOMContentLoaded', function () {
    const itemsMenu = document.querySelector('.items_menu');
    const items = document.querySelector('.items');

    // メニューが開かれているか、いないかによって（条件式）displayの表示をnone（非表示）にしたりflex（表示）にしたりする
    itemsMenu.addEventListener('click', function () {
    items.style.display = items.style.display === 'flex' ? 'none' : 'flex';
    });
});