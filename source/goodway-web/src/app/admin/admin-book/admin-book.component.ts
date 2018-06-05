import { Product } from './../../models/product';
import { Subscription } from 'rxjs/Subscription';
import { ProductService } from './../../product.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { DataTableResource } from 'angular-4-data-table';
import { Book } from '../../models/book.model';
import { BookService } from '../../book.service';
@Component({
  selector: 'app-admin-book',
  templateUrl: './admin-book.component.html',
  styleUrls: ['./admin-book.component.css']
})
export class AdminBookComponent implements OnInit, OnDestroy {
  books: Book[];
  subscription: Subscription;
  tableResource: DataTableResource<Book>;
  items: Book[] = [];
  itemCount: number;
  constructor(private bookService: BookService) {
    this.subscription = this.bookService.getAll()
      .subscribe(books => {
        this.books = books;
        this.initializeTable(books);
      });
  }

  private initializeTable(books: Book[]) {
    this.tableResource = new DataTableResource(books);
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
      this.books.filter(p => p.name.toLowerCase().includes(query.toLowerCase())) :
      this.books;

    this.initializeTable(filteredProducts);
  }

  ngOnInit() {
  }
  ngOnDestroy() {

  }
}
