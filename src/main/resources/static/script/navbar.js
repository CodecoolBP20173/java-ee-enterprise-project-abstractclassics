$(document).ready(function () {
    console.log("fuck you");
    var myURL=window.location.pathname;
    var navbuttons = document.getElementsByTagName('a');
    $(".menu-icon").on("click", function () {
        $("nav ul").toggleClass("showing");
    });

    console.log(myURL);
    for (var i = 0; i < navbuttons.length; i++) {
        var navButton = navbuttons[i];
        var navButtonUrl = navButton.getAttribute('href');
        console.log(navButtonUrl);
        if (myURL === navButtonUrl) {
            navButton.parentNode.classList.add("currentUrl");
        }

    }
});