var websocket;
var user;

function login() {
    websocket = new WebSocket("ws://127.0.0.1:8080/exchange/quote/" + document.formLogin.user.value);
    document.getElementById("user").innerHTML = document.formLogin.user.value;

    websocket.onmessage = function (event) {
        console.log(event.data);
        var quote = JSON.parse(event.data);
        document.getElementById("price-quote").innerHTML = quote.price;
        document.getElementById("user-quote").innerHTML = quote.user;
    };

    return false;
}

function isConnected() {
    return websocket != null && websocket.readyState == WebSocket.OPEN;
}

function update() {
    var price = document.formQuote.price.value;
    var json = JSON.stringify({
        "price": price
    });
    websocket.send(json);
    
    return false;
}

function toggleStatus() {
    connected = isConnected();

    var elements = document.getElementsByClassName("connected");
    for (var i = 0; i < elements.length; i++) {
        elements[i].style.display = connected ? "block" : "none";
    }
    var elements = document.getElementsByClassName("disconnected");
    for (var i = 0; i < elements.length; i++) {
        elements[i].style.display = !connected ? "block" : "none";
    }    

    // document.getElementById("login").style.display = !connected ? "block" : "none";
    // document.getElementById("quote").style.display = !connected ? "block" : "none";
}

setInterval(function () {
    toggleStatus();
}, 1000);

toggleStatus();