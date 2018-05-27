import { Component, OnInit } from '@angular/core';
import { BorrowService } from '../../borrow.service';

@Component({
  selector: 'app-admin-borrow-book',
  templateUrl: './admin-borrow-book.component.html',
  styleUrls: ['./admin-borrow-book.component.css']
})
export class AdminBorrowBookComponent implements OnInit {

  constructor(private borrowService:BorrowService) { }

  ngOnInit() {
  }

}
