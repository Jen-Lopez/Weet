const select = document.querySelectorAll("#form-search-restrictions .restriction-input");

const clearSelection = () => {
    select.forEach(elem => {
        const dropItem = elem.querySelector(".dropdown-item");
        dropItem.classList.remove("active");
    });
}

select.forEach(elem => {
    const dropItem = elem.querySelector(".dropdown-item");

    dropItem.addEventListener("click", () => {
        clearSelection();
        const input = elem.querySelector("input[name = 'restriction']");
        dropItem.classList.add("active");
        input.checked = true;
    })
  
})
