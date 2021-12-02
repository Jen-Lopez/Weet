const DRinput = document.getElementById("dietaryRestriction-search");

const DRresultsContainer = document.getElementById("results-dr");
const DRsaveButton = document.getElementById("save-dietaryRestriction");

const restrictions = ['Vegan', 'Vegetarian'];

DRinput.addEventListener("input", (e) => {
    let suggestions = [];

    if (e.target.value) {
        suggestions = restrictions.filter(restriction => restriction.toLowerCase().includes((e.target.value).toLowerCase()));
        suggestions = suggestions.map(restriction => `<li class = "dropdown-item">${restriction}</li>`)
    }

    showRestrictions(suggestions);

    let allListItems = document.querySelectorAll(".dropdown-item");
    for (let i = 0; i < allListItems.length; i++) {
        allListItems[i].setAttribute("onclick", "selectDR(this)");
    }

    if (!restrictions.includes(e.target.value)) {
        DRsaveButton.disabled = true;
    }
});

function showRestrictions(suggestions) {
    const content = suggestions.length == 0 ? '' : suggestions.join('');
    DRresultsContainer.innerHTML = content;
} 

function selectDR(item) {
    let data = item.textContent;
    DRinput.value = data;
    DRresultsContainer.innerHTML = '';
    DRsaveButton.disabled = false;
}
