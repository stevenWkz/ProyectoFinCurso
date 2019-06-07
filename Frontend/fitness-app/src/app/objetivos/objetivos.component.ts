import { Component, OnInit } from '@angular/core';
import { Objetivo } from './objetivo';
import { ObjetivoService } from './objetivo.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { AuthService } from '../usuarios/auth.service';
import { UsuarioService } from '../usuarios/usuario.service';


@Component({
  selector: 'app-objetivos',
  templateUrl: './objetivos.component.html',
  styleUrls: ['./objetivos.component.css']
})
export class ObjetivosComponent implements OnInit {

   objetivo: Objetivo = new Objetivo();

   objetivoExiste: Objetivo = new Objetivo();

  private errores: string[];



  constructor(private objetivoService: ObjetivoService, private usuarioService: UsuarioService, private router: Router,
    private activatedRouter: ActivatedRoute, private authService: AuthService) { 



    }

  ngOnInit() {

    //Muestra en el form del objetivo a los usuarios que tienes su objetivo
    this.usuarioService.objetivoDatosUser(this.authService.usuario.id).subscribe(objetivoExiste => this.objetivoExiste = objetivoExiste);

    /*this.cargarObjetivo();

    console.log(this.cargarObjetivo())*/

  }

 

  public cargarObjetivo(): void{
    this.activatedRouter.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.objetivoService.getObjetivo(id).subscribe( (objetivo) => this.objetivo = objetivo)
      }
    })
  }




  public insertarId(altura, edad, nivel, peso, sexo, usuario): void {

    this.objetivoService.insertarId(altura, edad, nivel, peso, sexo, usuario).subscribe(

      any => {

        this.router.navigate([`perfil/${this.authService.usuario.id}`])
        Swal.fire('Objetivo agregado', `${this.authService.usuario.nombre} has actulizado tu perfil!`, 'success')

      },
      err => {


        Swal.fire('Error', 'No se ha podido registrar el objetivo!', 'error');
        this.errores = err.error.errors as string[];

        console.error('Código del error desde el backend' + err.status);

      }
    )


  }


  public update(): void{

    this.objetivoService.update(this.objetivo).subscribe(

      usuario => {
        this.router.navigate(['/showUsers'])
        Swal.fire('Objetivo Actualizado', `Usuario ${this.authService.usuario.nombre} actulizado con éxito!`, 'success')
      },
   err => {
    
    Swal.fire('Error', 'No se ha podido actulizar el objetivo!', 'error');
    this.errores = err.error.errors as string[];
     console.error('Código del error desde el backend' + err.status);
     console.error(err.error.errors);
   }   
    )


  }

}
