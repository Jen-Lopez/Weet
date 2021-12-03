// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
    'use strict';
    window.addEventListener('load', function() {
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener('submit', function(event) {
          let choice;
          const dropdown = document.getElementById("form-search-restrictions");
          if(dropdown) {
            choice = dropdown.querySelector("input[name = 'restriction']:checked");
            
            if (!choice) {
              event.preventDefault();
              event.stopPropagation();
              const alert = document.getElementById("restriction-alert");
              alert.classList.add("showAlert");

              setTimeout(function() {
                alert.classList.remove("showAlert");
              }, 2200);

            }
          }

          if (form.checkValidity() === false || !choice) {
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
  