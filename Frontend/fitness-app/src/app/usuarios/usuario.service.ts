import { Injectable } from '@angular/core';
import { Usuario } from './usuario.js';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
import { AuthService } from './auth.service.js';
import { Alimento } from '../admin/alimento.js';




@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private urlEndPoint: string = "http://localhost:8080/users";

  private urlEndPoint2: string = "http://localhost:8080/alimentos";

  private urlEndPoint3: string = "http://localhost:8080/objetivos";

  private httppHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router: Router,
    private authService: AuthService) { }



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


  public insertarAlimentosUser(usuario_id, alimento_id): Observable<any> {

    return this.http.post<any>(`${this.urlEndPoint2}/insertarAlimentos/${usuario_id}/${alimento_id}`, { headers: this.agregarAuthorizationHeader()}).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );

  }





  public getAlimentos(): Observable<Alimento[]> {

    return this.http.get<Alimento[]>(this.urlEndPoint2 + '/allAlimentos', { headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );

  }

  public sumaAlimentos(id): Observable<any>{


    return this.http.get<any>(`${this.urlEndPoint2}/alimentosSUM/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );

  }


  public pesoIdealUser(id): Observable<any>{

    return this.http.get<any>(`${this.urlEndPoint3}/pesoIdealUser/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );

  }


  
  public caloriasDiarias(id): Observable<any>{

    return this.http.get<any>(`${this.urlEndPoint3}/caloriasDiarias/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );

  }


  public objetivoDatosUser(id): Observable<any>{

    return this.http.get<any>(`${this.urlEndPoint3}/objetivoDatosUser/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );

  }


  public getAlimentosUsuarios(id): Observable<Alimento[]> {

    return this.http.get<Alimento[]>(`${this.urlEndPoint2}/alimentosUsuario/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })

    );
  }

  //Servicio obtener usuarios
  public getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.urlEndPoint + '/usuarios', { headers: this.agregarAuthorizationHeader() }).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );
  }


  public datosUsuario(username: string): Observable<any> {
    return this.http.get<any>(`${this.urlEndPoint}/datosUsuario/${username}`, { headers: this.httppHeaders }).pipe(
      catchError(e => {
        this.isNoAutorizado(e);
        return throwError(e);
      })
    );
  }






  //Servicio de crear usuario
  public create(usuario: Usuario): Observable<any> {

    return this.http.post<any>(`${this.urlEndPoint}/register`, usuario, { headers: this.httppHeaders }).pipe(

      catchError(e => {



        if (e.status == 400) {

          return throwError(e);

        }

        this.router.navigate(['/login']);

        console.error(e.error.mensaje)

        Swal.fire(e.error.mensaje, 'No se puede registrar', 'error');

        return throwError(e);

      })

    );

  }


  public getUsario(id): Observable<Usuario> {

    return this.http.get<Usuario>(`${this.urlEndPoint}/usuario/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(

      catchError(e => {

        if (this.isNoAutorizado(e)) {

          return throwError(e);

        }

        this.router.navigate(['/login']);

        console.error(e.error.mensaje)

        Swal.fire('Error al editarrr', e.error.mensaje, 'error');

        return throwError(e);

      })
    );

  }



  //Servicio de actulizar un cliente
  public update(usuario: Usuario): Observable<Usuario> {

    return this.http.put(`${this.urlEndPoint}/usuarios/${usuario.id}`, usuario, { headers: this.agregarAuthorizationHeader() }).pipe(

      map((response: any) => response.usuario as Usuario),

      catchError(e => {

        if (this.isNoAutorizado(e)) {

          return throwError(e);

        }

        if (e.status == 400) {

          return throwError(e);
        }

        this.router.navigate(['/usuarios']);

        console.error(e.error.mensaje)

        Swal.fire(e.error.mensaje, e.error.mensaje, 'error');

        return throwError(e);

      })

    );

  }

  //Servicio eliminar un usuario
  public delete(id: number): Observable<Usuario> {

    return this.http.delete<Usuario>(`${this.urlEndPoint}/usuarios/${id}`, { headers: this.agregarAuthorizationHeader() }).pipe(


      catchError(e => {

        if (this.isNoAutorizado(e)) {

          return throwError(e);

        }

        this.router.navigate(['/usuarios']);

        console.error(e.error.mensaje)

        Swal.fire(e.error.mensaje, e.error.mensaje, 'error');

        return throwError(e);

      })

    )
  }

}


