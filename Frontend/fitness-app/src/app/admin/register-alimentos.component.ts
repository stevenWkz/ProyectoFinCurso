import { Component, OnInit } from '@angular/core';
import { Alimento } from './alimento';
import { AlimentoService } from './alimento.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register-alimentos',
  templateUrl: './register-alimentos.component.html',
  styleUrls: ['./register-alimentos.component.css']
})
export class RegisterAlimentosComponent implements OnInit {
  private errores: string[];
  private alimento: Alimento = new Alimento();


  
  constructor(private alimentoService: AlimentoService, private activitedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {

  }

  public createAlimento(): void{

    this.alimentoService.createAlimento(this.alimento)
    .subscribe(json => { 
     this.router.navigate(['/showAlimentos'])
     Swal.fire('Alimento registrado',`${this.alimento.nombre}`, 'success')
    },
    err => {
      this.errores = err.error.errors as string[];
      console.error('Código del error desde el backend' + err.status);
      console.error(err.error.errors);
    }
    );
 
   }

}
