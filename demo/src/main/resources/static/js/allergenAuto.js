const input = document.getElementById("allergen-search");
const resultsContainer = document.getElementById("results");
const saveButton = document.getElementById("save-allergen");

const allergens = ['Peanuts', 'Milk', 'Gluten', 'Fish', 'Eggs', 'Tree Nuts', 'Shellfish', 'Soybeans', 'Sesame'];

input.addEventListener("input", (e) => {
    let suggestions = [];

    if (e.target.value) {
        suggestions = allergens.filter(allergen => allergen.toLowerCase().includes((e.target.value).toLowerCase()));
        suggestions = suggestions.map(allergen => `<li class = "dropdown-item">${allergen}</li>`)
    }

    showAllergens(suggestions);

    let allListItems = document.querySelectorAll(".dropdown-item");
    for (let i = 0; i < allListItems.length; i++) {
        allListItems[i].setAttribute("onclick", "select(this)");
    }

    if (!allergens.includes(e.target.value)) {
        saveButton.disabled = true;
    }
});

function showAllergens(suggestions) {
    const content = suggestions.length == 0 ? '' : suggestions.join('');
    resultsContainer.innerHTML = content;
} 

function select(item) {
    let data = item.textContent;
    input.value = data;
    resultsContainer.innerHTML = '';
    saveButton.disabled = false;
}
