// Initializing our Auth0Lock
var lock = new Auth0Lock(
        'B9MpKVCGB7CC6LRpHvrkYHwmrtofCSdd',
        'sweetsecret.auth0.com'
        );




// Listening for the authenticated event
lock.on("authenticated", function (authResult) {
    // Use the token in authResult to getUserInfo() and save it to localStorage
    lock.getUserInfo(authResult.accessToken, function (error, profile) {
        if (error) {
            // Handle error
            console.log(error);
            return;
        }
        console.log(authResult.accessToken);
        alert('Sucess!!!!!');
        console.log("Yaay!!");
        app.exit();
    });
});

lock.on('authorization_error', () => {
    console.log("authentication_error");
});
document.getElementById('login').addEventListener('click', function () {
    lock.show();
});
