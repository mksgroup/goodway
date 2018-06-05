import { Product } from './../../models/product';
import { Subscription } from 'rxjs/Subscription';
import { ProductService } from './../../product.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { DataTableResource } from 'angular-4-data-table';
import { CategoryService } from '../../category.service';
import { vehicle } from '../../models/vehicle';
import { VehicleService } from '../../vehicle.service';
@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit, OnDestroy {
  vehicles: vehicle[];
  subscription: Subscription;
  tableResource: DataTableResource<vehicle>;
  items: vehicle[] = [];
  itemCount: number;
  constructor(private productService: ProductService,
    private vehicleService: VehicleService,
    private categoryService: CategoryService) {


      this.subscription = this.vehicleService.getAll()
      .subscribe(vehicles => {
        this.vehicles = vehicles;
        this.initializeTable(vehicles);
      });
  }

  private initializeTable(vehicles: vehicle[]) {
    this.tableResource = new DataTableResource(vehicles);
    this.tableResource.query({ offset: 0 })
      .then(items => this.items = items);
    this.tableResource.count()
      .then(count => this.itemCount = count);
  }

  reloadItems(params) {
    if (!this.tableResource) return;

    this.tableResource.query(params)
      .then(items => this.items = items);    
  }

  filter(query: string) { 
    let filteredProducts = (query) ?
      this.vehicles.filter(p => p.name.toLowerCase().includes(query.toLowerCase())) :
      this.vehicles;

    this.initializeTable(filteredProducts);
  }

  ngOnInit() {
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
