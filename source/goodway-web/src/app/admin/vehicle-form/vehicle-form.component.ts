import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { ProductService } from './../../product.service';
import { CategoryService } from './../../category.service';
import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/take';
import { VehicleService } from '../../vehicle.service';

@Component({
  selector: 'app-vehicle-form',
  templateUrl: './vehicle-form.component.html',
  styleUrls: ['./vehicle-form.component.css']
})
export class VehicleFormComponent implements OnInit {
  id;
  vehicle = {};

  constructor(private router: Router,
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private vehicleService: VehicleService,
    private productService: ProductService) {


    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) this.productService.get(this.id).take(1).subscribe(ve => this.vehicle = ve);
  }

  save(vehicle) {
    if (this.id) this.vehicleService.update(this.id, vehicle);
    else this.vehicleService.create(vehicle);

    this.router.navigate(['admin/vehicles']);
  }

  delete() {
    if (!confirm('Are you sure you want to delete this vehicle?')) return;

    this.vehicleService.delete(this.id);
    this.router.navigate(['admin/vehicles']);
  }

  ngOnInit() {
  }

}
