import { Component, OnInit } from '@angular/core';
import { Objetivo } from './objetivo';
import { ObjetivoService } from './objetivo.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { Usuario } from '../usuarios/usuario';
import { AuthService } from '../usuarios/auth.service';


@Component({
  selector: 'app-objetivos',
  templateUrl: './objetivos.component.html',
  styleUrls: ['./objetivos.component.css']
})
export class ObjetivosComponent implements OnInit {

  private objetivo: Objetivo = new Objetivo;

  private errores: string[];


  
  constructor(private objetivoService : ObjetivoService, private router: Router,
    private activatedRouter: ActivatedRoute, private authService: AuthService) { }

  ngOnInit() {

  
   
  }



  public createObjetivo(): void{

    this.objetivoService.createObjetivo(this.objetivo)
    .subscribe(json => { 
     this.router.navigate(['/perfil'])
     Swal.fire('Ha sido registrado tu objetivo ', 'success')
    },
    err => {
      this.errores = err.error.errors as string[];
      console.error('Código del error desde el backend' + err.status);
      console.error(err.error.errors);
    }
    );
 
   }

}
