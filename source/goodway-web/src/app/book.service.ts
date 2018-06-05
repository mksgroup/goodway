import { AngularFireDatabase } from 'angularfire2/database';
import { Injectable } from '@angular/core';

@Injectable()
export class BookService {

  constructor(private db: AngularFireDatabase) { }

  create(book) { 
    return this.db.list('/books').push(book);
  }

  getAll() {
    return this.db.list('/books');
  }
  
  get(bookId) { 
    return this.db.object('/books/' + bookId);
  }

  update(bookId, book) { 
    return this.db.object('/books/' + bookId).update(book);
  }

  delete(bookId) { 
    return this.db.object('/books/' + bookId).remove();
  }
}
