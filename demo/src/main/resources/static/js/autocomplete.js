
/* GOOGLE AUTOCOMPLETE */

function autocomplete() {
    const input = document.getElementById("autocomplete");
    const coordsLat = document.getElementById("coordsLat");
    const coordsLong = document.getElementById("coordsLong");
    const gcity = document.getElementById("g-city");
    const gstate = document.getElementById("g-state");
    const gnbhood = document.getElementById("g-nbhood");
    const gzipcode = document.getElementById("g-zip");

    const options = {
        componentRestrictions: { country: "us" },
        fields: ["address_components", "geometry", "name"],
    };

    let autocomplete;
    autocomplete = new google.maps.places.Autocomplete(input, options);

    autocomplete.addListener("place_changed", () => {
        const place = autocomplete.getPlace();
        coordsLat.value = place.geometry.location.lat();
        coordsLong.value = place.geometry.location.lng();  
        
        // Extract city, state
        let city = "";
        let neighborhood = "";
        let state = "";
        let zip = "";

        const addressComponents = place.address_components;

        for (let i = 0; i < addressComponents.length; i++) {
            for (let j = 0; j < addressComponents[i].types.length; j++) {
                if (addressComponents[i].types[j] == "neighborhood") {
                    neighborhood = addressComponents[i].long_name;
                    break;
                }
                if (addressComponents[i].types[j] == "administrative_area_level_1") {
                    state = addressComponents[i].short_name;
                    break;
                }
                if (addressComponents[i].types[j] == "postal_code") {
                    zip = addressComponents[i].short_name;
                    break;
                }
                if (addressComponents[i].types[j] == "locality") {
                    city = addressComponents[i].short_name;
                    break;
                }
            }
        }

        console.log(`NEIGHBORHOOD: ${neighborhood} CITY: ${city}  STATE: ${state}  ZIP CODE: ${zip}`)
        gcity.value = city;
        gstate.value = state;
        gnbhood.value = neighborhood;
        gzipcode.value = zip;
    });
}

google.maps.event.addDomListener(window, 'load', autocomplete);
