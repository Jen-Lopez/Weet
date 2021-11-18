function initMap() {
    getLocation()
    .then(coords => {
        let map = new google.maps.Map(document.getElementById("map"), {
            center: { lat: coords.lat, lng: coords.long },
            zoom: 12,
            });
        // new google.maps.Marker({
        //     position: { lat: -25.363, lng: 131.044 },
        //     map,
        //     title: "Hello World!",
        //     });
    })
}

async function getLocation () {
    return fetch('/getMap')
        .then(response => response.json())
        .then(data => {
            return data;
        })
        .catch(error => console.warn(error));
}



google.maps.event.addDomListener(window, 'load', initMap);
