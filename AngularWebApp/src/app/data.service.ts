import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {retry, catchError} from 'rxjs/operators';

import {Flight} from './Flight';

@Injectable({
  providedIn: 'root'
})

export class FlightSearchService {
  private readonly urlRestAPI: string = 'http://localhost:8080/flights/search';
  // Examples:
  // http://localhost:8080/flights/search?flightNumber=2005&origin=IAH&date=2018-01-31
  // http://localhost:8080/flights/all


  constructor(private http: HttpClient) {
  }


  searchFlights(flightNumber: string, origin: string, destination: string, fdate: string) {

    let headers = new HttpHeaders();
    headers = headers.append('Content-Type', 'application/json');

    let params = new HttpParams();
    params = params.append('flightNumber', flightNumber);
    params = params.append('origin', origin);
    params = params.append('destination', destination);
    params = params.append('date', fdate);

    return this.http.get(this.urlRestAPI, {headers, params});


  }


  getUsers() {
    return this.http.get('https://reqres.in/api/users');
  }


// Error handling
  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
// Get client-side error
      errorMessage = error.error.message;
    } else {
// Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }


}
