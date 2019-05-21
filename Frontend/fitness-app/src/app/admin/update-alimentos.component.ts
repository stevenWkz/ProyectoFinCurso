import { Component, OnInit } from '@angular/core';
import { Alimento } from './alimento';
import { UsuarioService } from '../usuarios/usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AlimentoService } from './alimento.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-alimentos',
  templateUrl: './update-alimentos.component.html',
  styleUrls: ['./update-alimentos.component.css']
})
export class UpdateAlimentosComponent implements OnInit {


  private alimento: Alimento = new Alimento();
  
  private errores: string[];


  constructor(private alimentoService: AlimentoService, private router: Router,
    private activatedRouter: ActivatedRoute) { }

  ngOnInit() {

    this.cargarAlimento();

  }

  public cargarAlimento(): void{
    this.activatedRouter.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.alimentoService.getAlimento(id).subscribe( (alimento) => this.alimento = alimento)
      }
    })
  }

  public update(): void{

    this.alimentoService.update(this.alimento).subscribe(

      usuario => {
        this.router.navigate(['/showAlimentos'])
        Swal.fire('Alimento Actualizado', `Alimento ${this.alimento.nombre} actulizado con éxito!`, 'success')
      },
   err => {
    
    this.errores = err.error.errors as string[];
     console.error('Código del error desde el backend' + err.status);
     console.error(err.error.errors);
   }   
    )


  }

}
