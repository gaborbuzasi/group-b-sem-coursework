function TopPopulatedCountries() {
    var number = document.getElementById('txtTopCountries').value;

    window.location.href = '/topn-countries?numberOfCountries=' + number;
}