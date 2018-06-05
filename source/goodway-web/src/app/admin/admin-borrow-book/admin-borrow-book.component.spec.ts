import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBorrowBookComponent } from './admin-borrow-book.component';

describe('AdminBorrowBookComponent', () => {
  let component: AdminBorrowBookComponent;
  let fixture: ComponentFixture<AdminBorrowBookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminBorrowBookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBorrowBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
