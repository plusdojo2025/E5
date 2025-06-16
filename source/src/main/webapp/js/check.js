/* ダイアログ */
const form= document.querySelector("form");
const modal = document.getElementById("confirmModal");
const yesBtn = document.getElementById("confirmYes");
const noBtn = document.getElementById("confirmNo");
   
form.addEventListener("submit", function(e) {
  e.preventDefault();
  modal.style.display = "block";
});
   
yesBtn.addEventListener("click", function() {
  modal.style.display = "none";
  HTMLFormElement.prototype.submit.call(form);
});
    
noBtn.addEventListener("click", function() {
 modal.style.display = "none";
});