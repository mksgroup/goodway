import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/take';
import { BookService } from '../../book.service';

@Component({
  selector: 'app-admin-book-new',
  templateUrl: './admin-book-new.component.html',
  styleUrls: ['./admin-book-new.component.css']
})
export class AdminBookNewComponent implements OnInit {
  id;
  book = {};
  constructor(private bookService: BookService,
    private router: Router,
    private route: ActivatedRoute) {
    console.log("AdminBookNewComponent");
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) this.bookService.get(this.id).take(1).subscribe(book => this.book = book);
  }

  save(book) {
    if (this.id) {
      this.bookService.update(this.id, book);
    } else {
      // book.
      book.status = "YES";
      book.createDate = new Date().getTime();
      this.bookService.create(book);
    }
    // else 

    this.router.navigate(['admin/books']);
  }

  delete() {
    if (!confirm('Are you sure you want to delete this vehicle?')) return;

    this.bookService.delete(this.id);
    this.router.navigate(['admin/books']);
  }

  ngOnInit() {
  }

}
