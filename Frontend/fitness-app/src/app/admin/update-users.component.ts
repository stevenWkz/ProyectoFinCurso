import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuarios/usuario';
import { UsuarioService } from '../usuarios/usuario.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-users',
  templateUrl: './update-users.component.html',
  styleUrls: ['./update-users.component.css']
})
export class UpdateUsersComponent implements OnInit {

  private usuario: Usuario = new Usuario();
  
  private errores: string[];

  constructor(private usuarioService: UsuarioService, private router: Router,
    private activatedRouter: ActivatedRoute) { }

  ngOnInit() {
    this.cargarCliente();

  }

  public cargarCliente(): void{
    this.activatedRouter.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.usuarioService.getUsario(id).subscribe( (usuario) => this.usuario = usuario)
      }
    })
  }

  public update(): void{

    this.usuarioService.update(this.usuario).subscribe(

      usuario => {
        this.router.navigate(['/showUsers'])
        Swal.fire('Cliente Actualizado', `Cliente ${this.usuario.nombre} actulizado con éxito!`, 'success')
      },
   err => {
    
    this.errores = err.error.errors as string[];
     console.error('Código del error desde el backend' + err.status);
     console.error(err.error.errors);
   }   
    )


  }

}
