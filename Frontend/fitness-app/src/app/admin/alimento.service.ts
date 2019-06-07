import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Alimento } from './alimento';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { AuthService } from '../usuarios/auth.service';
import { Usuario } from '../usuarios/usuario';

@Injectable({
  providedIn: 'root'
})
export class AlimentoService {

  private urlEndPoint: string = "http://localhost:8080/alimentos";

  private httppHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }



  private agregarAuthorizationHeader() {

    let token = this.authService.token;

    if (token != null) {

      return this.httppHeaders.append('Authorization', 'Bearer ' + token);
    }

    return this.httppHeaders;

  }

  private isNoAutorizado(e): boolean {

    if (e.status == 401 || e.status == 403) {

      this.router.navigate['/login']
      return true;

    }

    return false;


  }

  public getAlimento(id): Observable<Alimento> {



    return this.http.get<Alimento>(`${this.urlEndPoint}/alimento/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(

      catchError(e => {

        if (this.isNoAutorizado(e)) {

          return throwError(e);

        }

        this.router.navigate(['/showAlimentos']);

        console.error(e.error.mensaje)

        Swal.fire('Error al editarrr', e.error.mensaje, 'error');

        return throwError(e);

      })
    );

  }



  //Servicio obtener usuarios
  public getAlimentos(): Observable<Alimento[]> {
    return this.http.get<Alimento[]>(this.urlEndPoint + '/allAlimentos', { headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );
  }


  //Servicio de crear objetivo
  public createAlimento(alimento: Alimento): Observable<any> {

    return this.http.post<any>(`${this.urlEndPoint}/registerAlimento`, alimento, { headers: this.agregarAuthorizationHeader() }).pipe(

      catchError(e => {

        if (e.status == 400) {

          return throwError(e);

        }

        this.router.navigate(['/showAlimentos']);

        console.error(e.error.mensaje)

        Swal.fire(e.error.mensaje, 'No se puede registrar el alimento', 'error');

        return throwError(e);

      })

    );

  }


  public update(alimento: Alimento): Observable<Alimento> {

    return this.http.put(`${this.urlEndPoint}/alimento/${alimento.id}`, alimento, { headers: this.agregarAuthorizationHeader() }).pipe(

      map((response: any) => response.usuario as Alimento),

      catchError(e => {

        if (this.isNoAutorizado(e)) {

          return throwError(e);

        }

        if (e.status == 400) {

          return throwError(e);
        }

        this.router.navigate(['/showAlimentos']);

        console.error(e.error.mensaje)

        Swal.fire(e.error.mensaje, e.error.mensaje, 'error');

        return throwError(e);

      })

    );

  }




  //Servicio eliminar un alimento
  public delete(id: number): Observable<Alimento> {

    return this.http.delete<Alimento>(`${this.urlEndPoint}/alimento/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(


      catchError(e => {

        if (this.isNoAutorizado(e)) {

          return throwError(e);

        }

        this.router.navigate(['/showAlimentos']);

        console.error(e.error.mensaje)

        Swal.fire(e.error.mensaje, e.error.mensaje, 'error');

        return throwError(e);

      })

    )
  }

}

