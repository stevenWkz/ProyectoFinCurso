import { Component, OnInit } from '@angular/core';
import { Alimento } from './alimento';
import { AlimentoService } from './alimento.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-show-alimentos',
  templateUrl: './show-alimentos.component.html',
  styleUrls: ['./show-alimentos.component.css']
})
export class ShowAlimentosComponent implements OnInit {


  alimentos: Alimento[];

  private alimento: Alimento = new Alimento();

  constructor(private alimentoService: AlimentoService, private activitedRoute: ActivatedRoute) { }

  ngOnInit() {

    this.alimentoService.getAlimentos().subscribe(
      alimentos => this.alimentos = alimentos
    );
  }


  public delete(alimento: Alimento): void {

    Swal.fire({
      title: 'Estas seguro?',
      text: `¿Seguro que deseas eliminar el alimento ${alimento.nombre} ?`,
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this.alimentoService.delete(alimento.id).subscribe(

          response => {

            this.alimentos = this.alimentos.filter(cli => cli != alimento)

            Swal.fire(
              'Alimento eliminado!',
              `Alimento ${alimento.nombre} eliminado con éxito.`,
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
