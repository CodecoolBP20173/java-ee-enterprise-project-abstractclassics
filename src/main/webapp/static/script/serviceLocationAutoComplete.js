let input = document.getElementById('city');
let autocomplete = new google.maps.places.Autocomplete(input,{types: ['(cities)']});
google.maps.event.addListener(autocomplete, 'place_changed', function(){
    let place = autocomplete.getPlace();
});