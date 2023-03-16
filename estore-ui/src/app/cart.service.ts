import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, tap, catchError } from 'rxjs';
import { CartItem } from './cartItem';
import { MessageService } from './message.service';
import { Product } from './product';
import { LoginComponent } from './login/login.component';
import { Account } from './account';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  cart: any
  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }


private cartUrl = `http://localhost:8080/accounts`;  // URL to web api

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 *
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}
httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


  /** GET cart items by id. Will 404 if id not found */
  getItems(id: number): Observable<CartItem[]> {
    const url = `${this.cartUrl}/${id}`;
    return this.http.get<CartItem[]>(url).pipe(
      tap(_ => this.log(`fetched cart for id ${id}`)),
      catchError(this.handleError<CartItem[]>(`getProduct id=${id}`))
    );
  }
    
  /** Log a ProductService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`ProductService: ${message}`);
}
}
