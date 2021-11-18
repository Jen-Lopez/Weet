function initMap() {
    getLocation()
    .then(data => {

        let map = new google.maps.Map(document.getElementById("map"), {
            center: { lat: data.location.lat, lng: data.location.long },
            zoom: 10,
        });

        for (let i = 0; i < data.all.length; i++) {
            console.log(JSON.stringify(data.all[i]));
            new google.maps.Marker({
                position: { lat: data.all[i].lat, lng: data.all[i].long },
                map,
                title: `${data.all[i].name}`,
            });
        }

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
