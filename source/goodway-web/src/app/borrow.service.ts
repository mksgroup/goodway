import { AngularFireDatabase } from 'angularfire2/database';
import { Injectable } from '@angular/core';

@Injectable()
export class BorrowService {

  constructor(private db: AngularFireDatabase) { }

  create(borrow) { 
    return this.db.list('/borrows').push(borrow);
  }

  getAll() {
    return this.db.list('/borrows');
  }
  
  get(borrowId) { 
    return this.db.object('/borrows/' + borrowId);
  }

  update(borrowId, borrow) { 
    return this.db.object('/borrows/' + borrowId).update(borrow);
  }

  delete(borrowId) { 
    return this.db.object('/borrows/' + borrowId).remove();
  }
}
