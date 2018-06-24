import { BorrowService } from './../borrow.service';
import { Subscription } from 'rxjs/Subscription';
import { Borrow } from './../models/borrow.model';
import { Component, OnInit } from '@angular/core';
import { DataTableResource } from 'angular-4-data-table';

@Component({
  selector: 'app-admin-list-history',
  templateUrl: './admin-list-history.component.html',
  styleUrls: ['./admin-list-history.component.css']
})
export class AdminListHistoryComponent implements OnInit {
  books: Borrow[];
  subscription: Subscription;
  tableResource: DataTableResource<Borrow>;
  items: Borrow[] = [];
  itemCount: number;
  constructor(private borrowService: BorrowService) { 
    this.subscription = this.borrowService.getAll()
      .subscribe(books => {
        this.books = books;
        this.books.forEach(book => {
          console.log("name book:" + book.book.createDate);
          book.nameOfBook = book.book.name;
          var time: number = parseInt(book.createDate);
          book.borrowDate = new Date(time);
          if(book.returnDateString != '') {
            book.returnDate = new Date(parseInt(book.returnDateString)).toDateString();
          } else {
            book.returnDate = 'Not Return';
          }
          
        });
        this.initializeTable(books);
      });
  }

  private initializeTable(books: Borrow[]) {
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
      this.books.filter(p => p.account.toLowerCase().includes(query.toLowerCase())) :
      this.books;

    this.initializeTable(filteredProducts);
  }

  ngOnInit() {
  }

}
