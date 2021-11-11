const getRestDetails = (restID) => {
    // do get request to '/details' endpoint. Return the data.
    console.log("MADE A REQUEST TO GET " + restID);
    getRequest("GET", "/restaurant");
}

function getRequest(method, endpoint) {
    let req = new XMLHttpRequest();
    req.open(method, endpoint)
    req.send();
}
