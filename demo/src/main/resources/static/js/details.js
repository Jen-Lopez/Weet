const menuButton = document.getElementById("full-menu");
const menuList = document.getElementById("menuListContainer");

const showMenu = () => {
    console.log("open menu");
    menuList.classList.toggle("showMenu");
}

menuButton.addEventListener("click", showMenu);
