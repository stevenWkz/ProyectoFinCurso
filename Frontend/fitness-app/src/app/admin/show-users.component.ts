import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuarios/usuario';
import { UsuarioService } from '../usuarios/usuario.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-show-users',
  templateUrl: './show-users.component.html',
  styleUrls: ['./show-users.component.css']
})
export class ShowUsersComponent implements OnInit {

  usuarios: Usuario[];

  constructor(private usuarioService: UsuarioService, private activitedRoute: ActivatedRoute) { }

  ngOnInit() {

    this.usuarioService.getUsuarios().subscribe(
      usuarios => this.usuarios = usuarios
    );

  
  }

 
  public delete(usuario: Usuario): void {

    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que deseas eliminar al cliente ${usuario.nombre} ${usuario.apellido} ?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.usuarioService.delete(usuario.id).subscribe(

          response => {

            this.usuarios = this.usuarios.filter(cli => cli != usuario)

            Swal.fire(
              'Usuario eliminado!',
              `Usuario ${usuario.nombre} eliminado con éxito.`,
              'success'
            )
          }
        )

      } else if (
        // Read more about handling dismissals
        result.dismiss === Swal.DismissReason.cancel
      ) {
        Swal.fire(
          'Cancelado',
          'Your imaginary file is safe :)',
          'error'
        )
      }
    })
  }





}
