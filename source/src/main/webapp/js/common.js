document.addEventListener('DOMContentLoaded', function () {
  const openBtn = document.querySelector('#menu');
  const closeBtn = document.querySelector('.menu_box_close');
  const bgOverlay = document.querySelector('.menu_boxWrapBg');
  const menuBox = document.querySelector('.menu_boxWrapWrap');

  if (openBtn) {
    openBtn.addEventListener('click', function () {
      menuBox.classList.add('active');
    });
  }

  if (closeBtn) {
    closeBtn.addEventListener('click', function () {
      menuBox.classList.remove('active');
    });
  }

  if (bgOverlay) {
    bgOverlay.addEventListener('click', function () {
      menuBox.classList.remove('active');
    });
  }
});