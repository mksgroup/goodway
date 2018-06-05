import { ShoppingCartService } from './shopping-cart.service';
import { ProductService } from './product.service';
import { CategoryService } from './category.service';
import { AdminAuthGuard } from './admin-auth-guard.service';
import { UserService } from './user.service';
import { AuthGuard } from './auth-guard.service';
import { AuthService } from './auth.service';
import { environment } from './../environments/environment';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AngularFireModule } from 'angularfire2'; 
import { AngularFireDatabaseModule } from 'angularfire2/database'; 
import { AngularFireAuthModule } from 'angularfire2/auth'; 
import { RouterModule } from '@angular/router'; 
import { NgbModule } from '@ng-bootstrap/ng-bootstrap'; 
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; 
import { CustomFormsModule } from 'ng2-validation'; 
import { DataTableModule } from 'angular-4-data-table';

import { AppComponent } from './app.component';
import { BsNavbarComponent } from './bs-navbar/bs-navbar.component';
import { HomeComponent } from './home/home.component';
import { ProductsComponent } from './products/products.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { CheckOutComponent } from './check-out/check-out.component';
import { OrderSuccessComponent } from './order-success/order-success.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { AdminProductsComponent } from './admin/admin-products/admin-products.component';
import { AdminOrdersComponent } from './admin/admin-orders/admin-orders.component';
import { LoginComponent } from './login/login.component';
import { ProductFormComponent } from './admin/product-form/product-form.component';
import { ProductFilterComponent } from './products/product-filter/product-filter.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { ProductQuantityComponent } from './product-quantity/product-quantity.component';

import { AgmCoreModule } from '@agm/core';
import { VehicleComponent } from './admin/vehicle/vehicle.component';
import { VehicleFormComponent } from './admin/vehicle-form/vehicle-form.component';
import { VehicleService } from './vehicle.service';
import { AdminBookComponent } from './admin/admin-book/admin-book.component';
import { BookService } from './book.service';
import { AdminBookNewComponent } from './admin/admin-book-new/admin-book-new.component';
import { AdminBorrowBookComponent } from './admin/admin-borrow-book/admin-borrow-book.component';
import { BorrowService } from './borrow.service';
import { AddBorrowNewComponent } from './admin/add-borrow-new/add-borrow-new.component';


@NgModule({
  declarations: [
    AppComponent,
    BsNavbarComponent,
    HomeComponent,
    ProductsComponent,
    ShoppingCartComponent,
    CheckOutComponent,
    OrderSuccessComponent,
    MyOrdersComponent,
    AdminProductsComponent,
    AdminOrdersComponent,
    LoginComponent,
    ProductFormComponent,
    ProductFilterComponent,
    ProductCardComponent,
    ProductQuantityComponent,
    VehicleComponent,
    VehicleFormComponent,
    AdminBookComponent,
    AdminBookNewComponent,
    AdminBorrowBookComponent,
    AddBorrowNewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    CustomFormsModule,
    DataTableModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule,
    AngularFireAuthModule,
    NgbModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAu8tp2khp7j6aqkoOIO8sXXUkX3oebnro', 
      libraries: ["places"]
    }),
    RouterModule.forRoot([
      { path: '', component: ProductsComponent },
      { path: 'products', component: ProductsComponent },
      { path: 'shopping-cart', component: ShoppingCartComponent },
      { path: 'login', component: LoginComponent },

      { path: 'check-out', component: CheckOutComponent  },
      { path: 'order-success', component: OrderSuccessComponent },
      { path: 'my/orders', component: MyOrdersComponent },
     
      { 
        path: 'admin/products/new', 
        component: ProductFormComponent
        // canActivate: [AuthGuard, AdminAuthGuard] 
      },
      { 
        path: 'admin/products/:id', 
        component: ProductFormComponent
        // canActivate: [AuthGuard, AdminAuthGuard] 
      },
      { 
        path: 'admin/products', 
        component: AdminProductsComponent
        // canActivate: [AuthGuard, AdminAuthGuard] 
      },
      { 
        path: 'admin/orders', 
        component: AdminOrdersComponent
        // canActivate: [AuthGuard, AdminAuthGuard] 
      },
      { 
        path: 'admin/vehicles', 
        component: VehicleComponent
        // canActivate: [AuthGuard, AdminAuthGuard] 
      },
      { 
        path: 'admin/vehicles/:id', 
        component: VehicleFormComponent
        // canActivate: [AuthGuard, AdminAuthGuard] 
      },
      { 
        path: 'admin/vehicles/new', 
        component: VehicleFormComponent
        // canActivate: [AuthGuard, AdminAuthGuard] 
      },
      { 
        path: 'admin/books', 
        component: AdminBookComponent
        // canActivate: [AuthGuard, AdminAuthGuard] 
      },
      { 
        path: 'admin/books/new', 
        component: AdminBookNewComponent
        // canActivate: [AuthGuard, AdminAuthGuard] 
      },
      {
        path: 'admin/borrow/new/:id', 
        component: AddBorrowNewComponent
      }
      
    ])    
  ],
  providers: [
    AuthService,
    AuthGuard,
    AdminAuthGuard,
    UserService,
    CategoryService,
    ProductService,
    VehicleService,
    BookService,
    BorrowService,
    ShoppingCartService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
