function TopPopulatedCountries() {
    var number = document.getElementById('txtTopCountries').value;

    window.location.href = '/top-n-populated-countries?numberOfCountries=' + number;
}

function CountriesInContinent() {
    var continent = document.getElementById('txtCountriesInContinent').value;
    window.location.href = '/all-countries-in-continent?continent=' + continent;
}

function CountriesInRegion() {
    var region = document.getElementById('txtCountriesInRegion').value;
    window.location.href = '/all-countries-in-region?region=' + region;
}


document.getElementById('idOfButton').addEventListener('onclick', function () {
    var paramValue = document.getElementById('idOfInputField').value;
    window.location.href = 'urlOfEndpoint?';

})