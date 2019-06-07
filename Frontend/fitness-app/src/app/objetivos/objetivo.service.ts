import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { Objetivo } from './objetivo';
import { UsuarioService } from '../usuarios/usuario.service';
import { AuthService } from '../usuarios/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ObjetivoService {

  private urlEndPoint: string = "http://localhost:8080/objetivos";

  private httppHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router: Router, usarioService: UsuarioService,private authService: AuthService) { }


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




  public getObjetivo(id): Observable<Objetivo>{

    return this.http.get<Objetivo>(`${this.urlEndPoint}/objetivoShow/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(

      catchError(e => {

        if (this.isNoAutorizado(e)) {

          return throwError(e);

        }

        this.router.navigate(['/objetivo']);

        console.error(e.error.mensaje)

        Swal.fire('Error al editarrr', e.error.mensaje, 'error');

        return throwError(e);

      })
    );

  }


  //servicio para actulizar objetivo
  public update(objetivo: Objetivo): Observable<Objetivo> {

    return this.http.put(`${this.urlEndPoint}/objetivo/${objetivo.id}`, objetivo, { headers: this.agregarAuthorizationHeader() }).pipe(

      map((response: any) => response.usuario as Objetivo),

      catchError(e => {

        if (this.isNoAutorizado(e)) {

          return throwError(e);

        }

        if (e.status == 400) {

          return throwError(e);
        }

        this.router.navigate([`/objetivo`])

        console.error(e.error.mensaje)

        Swal.fire(e.error.mensaje, e.error.mensaje, 'error');

        return throwError(e);

      })

    );

  }


  public insertarId(altura, edad, nivel, peso, sexo, usuario): Observable<any> {

    return this.http.post<any>(`${this.urlEndPoint}/insertarId/${altura}/${edad}/${nivel}/${peso}/${sexo}/${usuario}`, { headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );

  }

}
