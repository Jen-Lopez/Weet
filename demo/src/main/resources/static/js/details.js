const menuButton = document.getElementById("full-menu");
const menuList = document.getElementById("menuListContainer");

const showMenu = () => {
    console.log("open menu");
    menuList.classList.toggle("showMenu");
}

$(function () {
    $('[data-toggle="popover"]').popover({ 
        trigger: 'focus',
        content: function() {
            let description = $(this)[0].dataset.description;
            if (description) {
                return $(this)[0].dataset.description;
            }
            return "Description not available."
        },
        title: function() {
            return $(this)[0].dataset.title;
        },
    })
})

menuButton.addEventListener("click", showMenu);
