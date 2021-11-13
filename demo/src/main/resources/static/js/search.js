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

searchForm.addEventListener("submit", loader);
