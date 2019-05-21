import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Objetivo } from './objetivo';

@Injectable({
  providedIn: 'root'
})
export class ObjetivoService {

  private urlEndPoint: string = "http://localhost:8080/objetivos";

  private httppHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router: Router) { }



  //Servicio de crear objetivo
  public createObjetivo(objetivo: Objetivo): Observable<any> {

    return this.http.post<any>(`${this.urlEndPoint}/registerObjetivo`, objetivo, { headers: this.httppHeaders }).pipe(

      catchError(e => {

      

        if (e.status == 400) {

          return throwError(e);

        }

        this.router.navigate(['/login']);

        console.error(e.error.mensaje)

        Swal.fire(e.error.mensaje, 'No se puede registrar el objetivo', 'error');

        return throwError(e);

      })

    );

  }
}
