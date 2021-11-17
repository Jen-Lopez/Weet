const searchForm = document.getElementById("search");
const loaderImg = document.getElementById("spinner");
const results = document.getElementById("results");

const redirectToDetails = (id) => {
    window.location.href=`/getRestaurant?id=${id}`
}

const loader = () => {
    loaderImg.style.display = "block";
    results.style.display = "none";
}

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        else {
            loader();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
