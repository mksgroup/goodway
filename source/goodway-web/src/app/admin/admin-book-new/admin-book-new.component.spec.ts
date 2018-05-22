import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBookNewComponent } from './admin-book-new.component';

describe('AdminBookNewComponent', () => {
  let component: AdminBookNewComponent;
  let fixture: ComponentFixture<AdminBookNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminBookNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminBookNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
