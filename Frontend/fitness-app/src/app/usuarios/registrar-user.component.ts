import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import  Swal  from 'sweetalert2';

@Component({
  selector: 'app-registrar-user',
  templateUrl: './registrar-user.component.html',
  styleUrls: ['./registrar-user.component.css']
})
export class RegistrarUserComponent implements OnInit {

  private usuario: Usuario = new Usuario();

  private errores: string[];

  constructor( private usuarioService : UsuarioService, private router: Router,
    private activatedRouter: ActivatedRoute) { }

  ngOnInit() {

    this.cargarUsuarios();

  }


  public cargarUsuarios(): void{

    this.activatedRouter.params.subscribe(params =>{

      let id = params['id']

      if(id ){

        this.usuarioService.getUsario(id).subscribe( (usuario) => this.usuario = usuario)

      }
    })


  }

  public create(): void{

   this.usuarioService.create(this.usuario)
   .subscribe(json => {
    this.router.navigate(['/login']) 
    Swal.fire('Nuevo Usuario',`${this.usuario.nombre}`, 'success')
   },
   err => {
     this.errores = err.error.errors as string[];
     console.error('Código del error desde el backend' + err.status);
     console.error(err.error.errors);
   }
   );

  }

  public update(): void{

    this.usuarioService.update(this.usuario).subscribe(

      cliente => {
        this.router.navigate(['/usuarios'])
        Swal.fire('Usuario Actualizado', `Usuario ${this.usuario.nombre} actulizado con éxito!`, 'success')
      },
   err => {
    
    this.errores = err.error.errors as string[];
     console.error('Código del error desde el backend' + err.status);
     console.error(err.error.errors);
   }   
    )


  }


}
