/* GOOGLE MAPS */

function initMap() {
    getLocation()
    .then(data => {

        let map = new google.maps.Map(document.getElementById("map"), {
            center: { lat: data.lat, lng: data.long },
            zoom: 13,
        });

        const infoWindow = new google.maps.InfoWindow({
            content: "",
            disableAutoPan: true,
        });

        const markers = data.all.map((rest) => {
        const label = rest.name;
        const marker = new google.maps.Marker({
            position: { lat: rest.lat, lng: rest.long },
            map,
        });
        
        marker.addListener("click", () => {
            infoWindow.setContent(label);
            infoWindow.open(map, marker);
        });
        return marker;
    });

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
