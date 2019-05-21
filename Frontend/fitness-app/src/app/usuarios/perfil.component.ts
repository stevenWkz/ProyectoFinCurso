import { Component, OnInit } from '@angular/core';
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Usuario } from './usuario';
import { Alimento } from '../admin/alimento';
import { AuthService } from './auth.service';


@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  private usuario: Usuario = new Usuario();

  alimentos: Alimento[];

  alimentos2: Alimento[];

  constructor(private usuarioService: UsuarioService, private router: Router,
    private activatedRouter: ActivatedRoute, private authServuce: AuthService) { }

  ngOnInit() {

    this.cargarCliente();

    this.usuarioService.getAlimentos().subscribe(alimentos => this.alimentos  = alimentos);

    this.usuarioService.getAlimentosUsuarios().subscribe(alimentos2 => this.alimentos2  = alimentos2);


  }

  public cargarCliente(): void{
    this.activatedRouter.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.usuarioService.getUsario(id).subscribe( (usuario) => this.usuario = usuario)
      }
    })
  }



}
