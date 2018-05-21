import { ShoppingCartService } from './../shopping-cart.service';
import { ElementRef, Component, OnInit, ViewChild, NgZone } from '@angular/core';
import { } from '@types/googlemaps';
import { FormControl } from '@angular/forms';
import { } from 'googlemaps';
import { MapsAPILoader } from '@agm/core';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
  cart$;
  title: string = 'My first AGM project';
  lat: number = 51.678418;
  lng: number = 7.809007;
  public searchControl: FormControl;
  public zoom: number;
  public searchText = "Viet Nam"
  @ViewChild("search")
  public searchElementRef: ElementRef;

  constructor(private shoppingCartService: ShoppingCartService,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) {
    //create search FormControl
    this.searchControl = new FormControl();

    //set current position
    this.setCurrentPosition();

    //load Places Autocomplete
    this.mapsAPILoader.load().then(() => {
      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement, {
        types: ["address"]
      });
      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();

          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          //set latitude, longitude and zoom
          this.lat = place.geometry.location.lat();
          this.lng = place.geometry.location.lng();
          this.zoom = 12;
        });
      });
    });
  }

  async ngOnInit() {
    this.cart$ = await this.shoppingCartService.getCart();

    this.zoom = 4;
    this.lat = 39.8282;
    this.lng = -98.5795;




  }
  private setCurrentPosition() {
    if ("geolocation" in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.lat = position.coords.latitude;
        this.lng = position.coords.longitude;
        this.zoom = 12;
      });
    }
  }
  clearCart() {
    this.shoppingCartService.clearCart();
  }
}
