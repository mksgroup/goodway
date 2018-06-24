import { Book } from './book.model';
export class Borrow {
    $key: string;
    bookId: string;
    nameOfBook: string;
    account: string;
    borrowDate: Date;
    createDate: string;
    returnDateString: string;
    returnDate: string;
    status: string;
    book: Book;
}