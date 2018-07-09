/**
 * Refer: https://developers.google.com/maps/documentation/javascript/adding-a-google-map
 */

var map;
var isLoadedMap = false;
/**
 * Init map.
 * This method is called by embbed script:  <script async defer th:src="@{https://maps.googleapis.com/maps/api/js?key=} + ${map_key} + @{&callback=initMap}">
 * @param divMapId
 */
function initMap() {
	console.log("Initializing map...");
	var hcm = new google.maps.LatLng(10.854416599999999, 106.8001511);
	var geocoder = new google.maps.Geocoder();

	map = new google.maps.Map(document.getElementById('map'), {
		zoom : 14,
		center : hcm
	});

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			initialLocation = new google.maps.LatLng(position.coords.latitude,
					position.coords.longitude);
			map.setCenter(initialLocation);
			console.log("Current posotion:" + initialLocation);
		});
	}

	isLoadedMap = true;
}