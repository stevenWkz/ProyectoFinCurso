import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import Swal from 'sweetalert2';
import { AuthService } from './auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from './usuario.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    usuario: Usuario;

  constructor(private authService: AuthService, private router: Router, private usuarioService: UsuarioService,
    private activatedRouter: ActivatedRoute) {

    this.usuario = new Usuario();


  }

  ngOnInit() {



  }


  public cargarCliente(): void{
    this.activatedRouter.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.usuarioService.getUsario(id).subscribe( (usuario) => this.usuario = usuario)
      }
    })
  }


  public login(): void {
    
    if (this.usuario.username == null || this.usuario.password == null) {
      Swal.fire('Error Login', 'Username o password vacías!', 'error');
      return;
    }

    this.authService.login(this.usuario).subscribe(response => {
     

      this.authService.guardarUsuario(response.access_token);

      this.authService.guardarToken(response.access_token);

      this.usuario = this.authService.usuario;
      

   
      if (this.authService.isAdmin() != true) {

        this.router.navigate([`perfil/${this.usuario.id}`]);

      } else {

        this.router.navigate(['/showUsers']);

      }



      Swal.fire('Login', `Hola ${this.usuario.username}, has iniciado sesión con éxito!`, 'success');
    }, err => {
      if (err.status == 400) {
        Swal.fire('Error Login', 'Usuario o clave incorrectas!', 'error');
      }
    }
    );
  }

}

