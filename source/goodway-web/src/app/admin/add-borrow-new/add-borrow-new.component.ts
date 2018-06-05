import { Component, OnInit } from '@angular/core';
import { BorrowService } from '../../borrow.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { BookService } from '../../book.service';

@Component({
  selector: 'app-add-borrow-new',
  templateUrl: './add-borrow-new.component.html',
  styleUrls: ['./add-borrow-new.component.css']
})
export class AddBorrowNewComponent implements OnInit {
  bookId: string;
  book = {};

  constructor(private borrowService:BorrowService,
    private bookService: BookService,
    private router: Router, 
    private route: ActivatedRoute) { 
    this.bookId = this.route.snapshot.paramMap.get('id');
    console.log("bookId:" + this.bookId);
    this.bookService.get(this.bookId).take(1).subscribe(b => this.book = b);
  }

  ngOnInit() {
  }

}
