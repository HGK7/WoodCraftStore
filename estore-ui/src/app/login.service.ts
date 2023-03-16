import { Injectable } from '@angular/core';
import { Account } from './account';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loginUrl = 'http://localhost:8080/accounts';  // URL to web api

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
    //** GET accounts from the server */
getAccounts(): Observable<Account[]> {

  return this.http.get<Account[]>(this.loginUrl)
    .pipe(
      tap(_ => this.log('fetched Accounts')),
      catchError(this.handleError<Account[]>('getAccounts', []))
    );

}
  /** GET account by id. Will 404 if id not found */
  getAccount(id: number): Observable<Account> {
    const url = `${this.loginUrl}/${id}`;
    return this.http.get<Account>(url).pipe(
      tap(_ => this.log(`fetched Account with id ${id}`)),
      catchError(this.handleError<Account>(`getAccount id=${id}`))
    );
  
  }

  /** Log a AccountService message with the MessageService */
private log(message: string) {
    this.messageService.add(`LoginService: ${message}`);
}
httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
/** POST: add a new account to the server */
addAccount(account: Account): Observable<Account> {
  return this.http.post<Account>(this.loginUrl, account, this.httpOptions).pipe(
    tap((newAccount: Account) => this.log(`added account w/ id=${newAccount.id}`)),
    catchError(this.handleError<Account>('addAccount'))
  );
}

/* GET accounts whose name contains search term */
searchAccounts(term: string): Observable<Account[]> {
  if (!term.trim()) {
    // if not search term, return empty accounts array.
    return of([]);
  }
  return this.http.get<Account[]>(`${this.loginUrl}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found account "${term}", press login again to confirm`) :
       this.log(`no accounts matching "${term}", press login again to create an account and login`)),
    catchError(this.handleError<Account[]>('searchAccounts', []))
  );
}
/** PUT: update the account on the server */
updateAccount(account: Account): Observable<any> {
  return this.http.put(this.loginUrl, account as Account, this.httpOptions).pipe(
    tap(_ => this.log(`updated account with id ${account.id}`)),
    catchError(this.handleError<any>('updateAccount'))
  );
}

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

}


