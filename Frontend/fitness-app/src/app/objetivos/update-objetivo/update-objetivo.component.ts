import { Component, OnInit } from '@angular/core';
import { Objetivo } from '../objetivo';
import { ObjetivoService } from '../objetivo.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/usuarios/auth.service';
import { UsuarioService } from 'src/app/usuarios/usuario.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-objetivo',
  templateUrl: './update-objetivo.component.html',
  styleUrls: ['./update-objetivo.component.css']
})
export class UpdateObjetivoComponent implements OnInit {

  private objetivo: Objetivo = new Objetivo();

  private errores: string[];

  constructor(private objetivoService: ObjetivoService, private usuarioService: UsuarioService, private router: Router,
    private activatedRouter: ActivatedRoute, private authService: AuthService) { }

  ngOnInit() {

    this.usuarioService.objetivoDatosUser(this.authService.usuario.id).subscribe(objetivo => this.objetivo = objetivo);
  }


  public update(): void{

    this.objetivoService.update(this.objetivo).subscribe(

      objetivo => {
        this.router.navigate([`/perfil/${this.authService.usuario.id}`])
        Swal.fire('Objetivo Actualizado', `Usuario ${this.authService.usuario.nombre} actulizado con éxito!`, 'success')
      },
   err => {
    
    this.errores = err.error.errors as string[];
     console.error('Código del error desde el backend' + err.status);
     console.error(err.error.errors);
   }   
    )


  }

}
