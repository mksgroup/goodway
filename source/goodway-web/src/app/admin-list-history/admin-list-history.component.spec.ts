import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminListHistoryComponent } from './admin-list-history.component';

describe('AdminListHistoryComponent', () => {
  let component: AdminListHistoryComponent;
  let fixture: ComponentFixture<AdminListHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminListHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminListHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
