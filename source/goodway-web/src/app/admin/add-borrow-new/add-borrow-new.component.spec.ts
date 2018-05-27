import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBorrowNewComponent } from './add-borrow-new.component';

describe('AddBorrowNewComponent', () => {
  let component: AddBorrowNewComponent;
  let fixture: ComponentFixture<AddBorrowNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBorrowNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBorrowNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
